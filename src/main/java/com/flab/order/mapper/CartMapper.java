package com.flab.order.mapper;

import com.flab.order.domain.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper {
    Cart selectCart(@Param("memberId") Long memberId, @Param("productId") Long productId);
}
