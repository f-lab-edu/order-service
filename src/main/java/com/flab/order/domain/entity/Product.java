package com.flab.order.domain.entity;

import lombok.Getter;

import java.time.Instant;

@Getter
public class Product {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer stock;
    private Instant createdAt;
}
