package com.flab.order.domain.entity;

import lombok.Getter;

import java.time.Instant;

@Getter
public class Member {
    private Long id;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String address;
    private Integer balance;
    private Instant createdAt;
    private Instant updatedAt;
}
