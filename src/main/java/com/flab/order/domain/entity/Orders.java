package com.flab.order.domain.entity;

import lombok.Getter;

import java.time.Instant;

@Getter
public class Orders {
    private Long id;
    private Long memberId;
    private Status status;
    private Integer totalPrice;
    private Instant createdAt;
    private Instant updatedAt;
}
