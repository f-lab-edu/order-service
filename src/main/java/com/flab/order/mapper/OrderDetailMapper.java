package com.flab.order.mapper;

import com.flab.order.domain.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface OrderDetailMapper {
    Optional<OrderDetail> findByOrderIdAndProductId(@Param("orderId")Long orderId, @Param("productId")Long productId);
}
