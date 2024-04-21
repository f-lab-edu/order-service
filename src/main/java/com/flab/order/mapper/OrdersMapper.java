package com.flab.order.mapper;

import com.flab.order.domain.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface OrdersMapper {
    Optional<Orders> findById(Long id);

    void save(Orders order);

    void updateStatus(@Param("orderId")Long id, @Param("status")String status);
}
