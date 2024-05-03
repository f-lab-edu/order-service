package com.flab.order.domain.vo;

import com.flab.order.domain.entity.Cart;
import lombok.Value;

import java.util.List;

@Value
public class CartValidationResult {
    List<Cart> cartItemList;
    int totalPrice;
}
