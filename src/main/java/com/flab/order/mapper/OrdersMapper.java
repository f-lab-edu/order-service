package com.flab.order.mapper;

import com.flab.order.domain.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {
    Orders selectOrdersById(Long id);
}
