package com.flab.order.service;

import com.flab.order.domain.entity.Payment;
import com.flab.order.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentMapper paymentMapper;

    public void processOrderPayment(Long orderId, int totalPrice){
        paymentMapper.save(Payment.createPayment(orderId, totalPrice));
    }
}
