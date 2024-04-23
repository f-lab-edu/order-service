package com.flab.order.service;

import com.flab.order.converter.OrderConverter;
import com.flab.order.domain.dto.OrderResponse;
import com.flab.order.domain.entity.*;
import com.flab.order.domain.vo.CartValidationResult;
import com.flab.order.mapper.OrderDetailMapper;
import com.flab.order.mapper.OrdersMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrdersMapper ordersMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final MemberService memberService;
    private final CartService cartService;
    private final ProductService productService;
    private final PaymentService paymentService;

    public OrderResponse.Success orderCartItems(Long memberId){
        // 로그인 회원 정보 불러오기
        Member member = memberService.getMember(memberId);
        logger.info("회원 정보 조회 완료: 회원 ID = {}", memberId);

        // 장바구니 상품 재고와 가격 확인
        CartValidationResult cartValidationResult = cartService.checkStockAndPrice(member);
        logger.info("장바구니 상품 재고 및 가격 확인 완료: 회원 ID = {}", memberId);

        // 주문서 생성
        Long orderId = createOrder(member, cartValidationResult);
        logger.info("주문서 생성 완료: 회원 ID = {}, 주문 ID = {}", memberId, orderId);

        // 장바구니 비우기
        cartService.emptyCart(memberId);
        logger.info("장바구니 비움: 회원 ID = {}", memberId);

        // 상품 재고 감소
        productService.decreaseStock(cartValidationResult.getCartItemList());
        logger.info("상품 재고 감소 처리: 회원 ID = {}, 주문 ID = {}", memberId, orderId);

        // 회원 잔액 감소
        memberService.decreaseBalance(memberId, cartValidationResult.getTotalPrice());
        logger.info("회원 잔액 감소 처리: 회원 ID = {}", memberId);

        // 결제 처리
        paymentService.processOrderPayment(orderId, cartValidationResult.getTotalPrice());
        logger.info("결제 처리 완료: 회원 ID = {}, 주문 ID = {}", memberId, orderId);

        // 주문 상태 변경: PENDING(대기) -> COMPLETE(완료)
        updateStatusToComplete(orderId);
        logger.info("주문 처리 완료: 회원 ID = {}, 주문 ID = {}", memberId, orderId);

        return OrderConverter.toSuccess(cartValidationResult);
    }

    @Transactional
    public Long createOrder(Member member, CartValidationResult validationResult) {
        Orders newOrder = Orders.createOrder(member.getId(), validationResult.getTotalPrice());
        ordersMapper.save(newOrder);

        List<OrderDetail> newOrderDetailList = validationResult.getCartItemList().stream()
                .map(cart -> OrderDetail.createOrderDetail(newOrder.getId(), cart))
                .collect(Collectors.toList());
        orderDetailMapper.saveAll(newOrderDetailList);

        return newOrder.getId();
    }

    private void updateStatusToComplete(Long orderId){
        ordersMapper.updateStatus(orderId, Status.COMPLETE.name());
    }
}
