package com.flab.order.domain.dto;

import com.flab.order.domain.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

public class OrderResponse {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Success{
        private Status orderStatus;
        private Details productDetails;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Details{
        private List<ProductInfo> productInfoList;
        private Integer totalPrice;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ProductInfo{
        private String name;
        private Integer price;
        private Integer stock;
    }
}
