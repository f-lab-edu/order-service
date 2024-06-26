package com.flab.order.global.response.statusEnums;

import com.flab.order.global.response.BaseCode;
import com.flab.order.global.response.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseCode {

    // 일반적인 실패 응답
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 내의 문제가 발생했습니다."),

    // 회원 MEMBER
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER4010", "비밀번호가 일치하지 않습니다."),
    MEMBER_NOT_EXIST(HttpStatus.NOT_FOUND, "MEMBER4040", "존재하지 않는 회원 정보입니다."),
    INVALID_BALANCE(HttpStatus.CONFLICT, "MEMBER4090", "회원의 잔액이 부족합니다."),

    // 장바구니 CART
    EMPTY_CART(HttpStatus.UNPROCESSABLE_ENTITY, "CART4220", "장바구니가 비어있습니다."),
    INVALID_QUANTITY(HttpStatus.CONFLICT, "CART4090", "장바구니에 담긴 상품의 재고가 부족합니다."),
    INVALID_TOTAL_PRICE(HttpStatus.CONFLICT, "CART4091", "장바구니에 담긴 상품의 총액이 회원의 잔액을 초과하였습니다."),

    // 상품 PRODUCT
    INVALID_STOCK(HttpStatus.CONFLICT, "PRODUCT4090", "상품의 재고가 부족합니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ResponseDTO getDto() {
        return ResponseDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ResponseDTO getHttpStatusDto() {
        return ResponseDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
