package com.flab.order.domain.entity;

import lombok.Getter;

import java.time.Instant;

@Getter
public class Cart {
    private Long memberId;
    private Long productId;
    private Integer quantity;
    private Instant createdAt;

    // 연관된 상품 객체
    private Product product;
}
