package com.flab.order.domain.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class Orders {
    private Long id;
    private Long memberId;
    private Status status;
    private Integer totalPrice;
    private Instant createdAt;
    private Instant updatedAt;

    public static Orders createOrder(Long memberId, Integer totalPrice) {
        Orders order = new Orders();
        order.memberId = memberId;
        order.status = Status.PENDING;
        order.totalPrice = totalPrice;
        order.createdAt = Instant.now();
        order.updatedAt = Instant.now();
        return order;
    }
}
