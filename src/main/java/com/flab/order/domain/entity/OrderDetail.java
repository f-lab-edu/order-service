package com.flab.order.domain.entity;

import lombok.Getter;

@Getter
public class OrderDetail {
    private Long orderId;
    private Long productId;
    private Integer price;
    private Integer quantity;

    public static OrderDetail createOrderDetail(Long orderId, Cart cart) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.orderId = orderId;
        orderDetail.productId = cart.getProduct().getId();
        orderDetail.price = cart.getProduct().getPrice();
        orderDetail.quantity = cart.getQuantity();
        return orderDetail;
    }
}
