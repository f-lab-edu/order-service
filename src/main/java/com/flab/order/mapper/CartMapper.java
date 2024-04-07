package com.flab.order.mapper;

import com.flab.order.domain.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface CartMapper {
    Optional<Cart> selectCart(@Param("memberId") Long memberId, @Param("productId") Long productId);
}
