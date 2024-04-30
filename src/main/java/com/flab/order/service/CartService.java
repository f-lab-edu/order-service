package com.flab.order.service;

import com.flab.order.domain.entity.Cart;
import com.flab.order.domain.entity.Member;
import com.flab.order.domain.vo.CartValidationResult;
import com.flab.order.global.exception.GeneralHandler;
import com.flab.order.global.response.statusEnums.ErrorStatus;
import com.flab.order.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    Logger logger = LoggerFactory.getLogger(CartService.class);

    private final CartMapper cartMapper;

    public CartValidationResult checkStockAndPrice(Member member) {
        List<Cart> cartItemList = cartMapper.findByMemberId(member.getId());
        // 장바구니 비어있는지 확인
        if (cartItemList.isEmpty()) {
            logger.error("장바구니 비어있음: 회원 ID = {}", member.getId());
            throw new GeneralHandler(ErrorStatus.EMPTY_CART);
        }
        // 장바구니에 담긴 수량과 상품 재고 검증
        cartItemList.forEach(Cart::validateStock);

        // 총 가격과 회원의 잔액을 검증
        int totalPrice = calculateTotalPrice(cartItemList);
        validateBalance(totalPrice, member.getBalance());

        // 장바구니 상품 총액과 회원 잔액 비교
        return new CartValidationResult(cartItemList, totalPrice);
    }

    private int calculateTotalPrice(List<Cart> cartItemList) {
        return cartItemList.stream()
                .mapToInt(Cart::calculatePrice)
                .sum();
    }

    private void validateBalance(int totalPrice, int balance) {
        if (totalPrice > balance) {
            throw new GeneralHandler(ErrorStatus.INVALID_TOTAL_PRICE);
        }
    }

    @Transactional
    public void emptyCart(Long memberId){
        cartMapper.deleteByMemberId(memberId);
    }
}
