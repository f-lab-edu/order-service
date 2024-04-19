package com.flab.order.domain.entity;

import lombok.Getter;

@Getter
public class OrderDetail {
    private Long orderId;
    private Long productId;
    private Integer price;
    private Integer quantity;
}
