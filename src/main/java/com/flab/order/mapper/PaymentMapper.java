package com.flab.order.mapper;

import com.flab.order.domain.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    Payment selectPaymentById(Long id);
}
