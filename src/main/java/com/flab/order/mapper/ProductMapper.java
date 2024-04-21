package com.flab.order.mapper;


import com.flab.order.domain.entity.Cart;
import com.flab.order.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
    Optional<Product> findById(Long id);
    int updateAllByStock(List<Cart> cartItemList);
}
