package com.flab.order.mapper;

import com.flab.order.domain.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface PaymentMapper {
    Optional<Payment> findById(Long id);
}
