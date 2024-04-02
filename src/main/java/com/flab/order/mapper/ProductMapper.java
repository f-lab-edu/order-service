package com.flab.order.mapper;


import com.flab.order.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    Product selectProductById(Long id);
}
