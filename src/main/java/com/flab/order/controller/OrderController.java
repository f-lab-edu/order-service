package com.flab.order.controller;

import com.flab.order.domain.dto.OrderResponse;
import com.flab.order.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "주문 API", description = "주문 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    @Operation(summary = "장바구니 상품 주문 API")
    @PostMapping()
    public ApiResponse<OrderResponse.Success> orderCartItems(){
        return null;
    }
}
