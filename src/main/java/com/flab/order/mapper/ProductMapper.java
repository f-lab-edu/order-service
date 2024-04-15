package com.flab.order.mapper;


import com.flab.order.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ProductMapper {
    Optional<Product> findById(Long id);
}
