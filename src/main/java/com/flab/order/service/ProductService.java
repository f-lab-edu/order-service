package com.flab.order.service;

import com.flab.order.domain.entity.Cart;
import com.flab.order.global.exception.GeneralHandler;
import com.flab.order.global.response.statusEnums.ErrorStatus;
import com.flab.order.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    @Transactional
    public void decreaseStock(List<Cart> cartItemList) {
        if(productMapper.updateAllByStock(cartItemList) != cartItemList.size()){
            throw new GeneralHandler(ErrorStatus.INVALID_STOCK);
        }
    }
}
