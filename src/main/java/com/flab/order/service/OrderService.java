package com.flab.order.service;

import com.flab.order.domain.dto.OrderResponse;
import com.flab.order.domain.entity.Cart;
import com.flab.order.domain.entity.Member;
import com.flab.order.domain.entity.OrderDetail;
import com.flab.order.domain.entity.Orders;
import com.flab.order.domain.vo.CartValidationResult;
import com.flab.order.mapper.OrderDetailMapper;
import com.flab.order.mapper.OrdersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersMapper ordersMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final MemberService memberService;
    private final CartService cartService;
    private final ProductService productService;

    public OrderResponse.Success orderCartItems(Long memberId){
        // 로그인 회원 정보 불러오기
        Member member = memberService.getMember(memberId);
        // 장바구니 상품 재고와 가격 확인
        CartValidationResult cartValidationResult = cartService.checkStockAndPrice(member);
        // 주문서 생성
        createOrder(member, cartValidationResult);
        // 장바구니 비우기
        cartService.emptyCart(memberId);
        // 상품 재고 감소
        productService.decreaseStock(cartValidationResult.getCartItemList());


        return null;
    }

    @Transactional
    public void createOrder(Member member, CartValidationResult validationResult) {
        Orders newOrder = Orders.createOrder(member.getId(), validationResult.getTotalPrice());
        ordersMapper.save(newOrder);

        List<OrderDetail> newOrderDetailList = validationResult.getCartItemList().stream()
                .map(cart -> OrderDetail.createOrderDetail(newOrder.getId(), cart))
                .collect(Collectors.toList());
        orderDetailMapper.saveAll(newOrderDetailList);
    }
}
