package com.flab.order.domain.dto;

import com.flab.order.domain.entity.Status;

import java.time.Instant;
import java.util.List;

public class OrderResponse {
    public static class Success{
        private Status orderStatus;
        private Details productDetails;
    }

    public static class Details{
        private List<ProductInfo> productInfoList;
        private Integer totalPrice;
    }

    public static class ProductInfo{
        private String name;
        private Integer price;
        private Integer stock;
    }
}
