package com.flab.order.mapper;

import com.flab.order.domain.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDetailMapper {
    OrderDetail selectOrderDetail(@Param("orderId")Long orderId, @Param("productId")Long productId);
}
