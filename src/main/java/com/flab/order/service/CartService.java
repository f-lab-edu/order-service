package com.flab.order.service;

import com.flab.order.domain.entity.Cart;
import com.flab.order.domain.entity.Member;
import com.flab.order.domain.vo.CartValidationResult;
import com.flab.order.global.exception.GeneralHandler;
import com.flab.order.global.response.statusEnums.ErrorStatus;
import com.flab.order.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;

    public CartValidationResult checkStockAndPrice(Member member) {
        List<Cart> cartItemList = cartMapper.findByMemberId(member.getId());
        // 장바구니 비어있는지 확인
        if (cartItemList.isEmpty()) {
            throw new GeneralHandler(ErrorStatus.EMPTY_CART);
        }
        // 장바구니에 담긴 수량과 상품 재고 비교
        validateStock(cartItemList);
        // 장바구니 상품 총액과 회원 잔액 비교
        return new CartValidationResult(cartItemList, validateBalance(cartItemList, member.getBalance()));
    }

    private void validateStock(List<Cart> cartItemList) {
        cartItemList.stream()
                .filter(cart -> cart.getProduct().getStock() < cart.getQuantity())
                .findFirst()
                .ifPresent(cart -> {
                    throw new GeneralHandler(ErrorStatus.INVALID_QUANTITY);
                });
    }

    private int calculateTotalPrice(List<Cart> cartItemList) {
        return cartItemList.stream()
                .mapToInt(cart -> cart.getProduct().getPrice() * cart.getQuantity())
                .sum();
    }

    private int validateBalance(List<Cart> cartItemList, int balance) {
        int totalPrice = calculateTotalPrice(cartItemList);
        if (totalPrice > balance) {
            throw new GeneralHandler(ErrorStatus.INVALID_TOTAL_PRICE);
        }
        return totalPrice;
    }

    @Transactional
    public void emptyCart(Long memberId){
        cartMapper.deleteByMemberId(memberId);
    }
}
