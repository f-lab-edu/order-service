package com.flab.order.converter;

import com.flab.order.domain.dto.OrderResponse;
import com.flab.order.domain.entity.Product;
import com.flab.order.domain.entity.Status;
import com.flab.order.domain.vo.CartValidationResult;

import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {
    public static OrderResponse.ProductInfo toProductInfo(Product product) {
        return OrderResponse.ProductInfo.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    public static OrderResponse.Details toDetails(CartValidationResult validationResult) {
        List<OrderResponse.ProductInfo> productInfos = validationResult.getCartItemList().stream()
                .map(cartItem -> toProductInfo(cartItem.getProduct()))
                .collect(Collectors.toList());

        return OrderResponse.Details.builder()
                .productInfoList(productInfos)
                .totalPrice(validationResult.getTotalPrice())
                .build();
    }

    public static OrderResponse.Success toSuccess(CartValidationResult validationResult) {
        OrderResponse.Details details = toDetails(validationResult);

        return OrderResponse.Success.builder()
                .orderStatus(Status.COMPLETE)
                .productDetails(details)
                .build();
    }
}
