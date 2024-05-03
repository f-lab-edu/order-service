package com.flab.order.mapper;

import com.flab.order.domain.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartMapper {
    Optional<Cart> findByMemberIdAndProductId(@Param("memberId") Long memberId, @Param("productId") Long productId);

    List<Cart> findByMemberId(Long memberId);

    void deleteByMemberId(Long memberId);
}
