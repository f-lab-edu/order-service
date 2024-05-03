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

    public static Payment createPayment(Long orderId, int totalPrice) {
        Payment payment = new Payment();
        payment.orderId = orderId;
        payment.amount = totalPrice;
        payment.status = Status.COMPLETE;
        payment.createdAt = Instant.now();
        payment.updatedAt = Instant.now();
        return payment;
    }
}
