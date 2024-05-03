package com.flab.order.domain.entity;

import com.flab.order.global.exception.GeneralHandler;
import com.flab.order.global.response.statusEnums.ErrorStatus;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;

@Getter
public class Cart {
    Logger logger = LoggerFactory.getLogger(Cart.class);

    private Long memberId;
    private Long productId;
    private Integer quantity;
    private Instant createdAt;

    // 연관된 상품 객체
    private Product product;

    public void validateStock() {
        if (this.product.getStock() < this.quantity) {
            logger.error("상품 재고 부족: 회원 ID = {}, 제품 ID = {}", memberId, productId);
            throw new GeneralHandler(ErrorStatus.INVALID_QUANTITY);
        }
    }

    public int calculatePrice() {
        return this.product.getPrice() * this.quantity;
    }
}
