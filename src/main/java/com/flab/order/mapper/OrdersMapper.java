package com.flab.order.mapper;

import com.flab.order.domain.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface OrdersMapper {
    Optional<Orders> findById(Long id);
}
