package com.flab.order.domain.entity;

import lombok.Getter;

import java.time.Instant;

@Getter
public class Payment {
    private Long id;
    private Long orderId;
    private Integer amount;
    private Status status;
    private Instant createdAt;
    private Instant updatedAt;
}
