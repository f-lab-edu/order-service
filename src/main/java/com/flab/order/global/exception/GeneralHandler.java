package com.flab.order.global.exception;

import com.flab.order.global.response.BaseCode;
import com.flab.order.global.response.ResponseDTO;
import lombok.Getter;

@Getter
public class GeneralHandler extends RuntimeException {

    private BaseCode errorStatus;

    public GeneralHandler(BaseCode errorStatus) {
        super(errorStatus.getDto().getMessage());
        this.errorStatus = errorStatus;
    }

    public ResponseDTO getError() {
        return this.errorStatus.getDto();
    }

    public ResponseDTO getErrorHttpStatus() {
        return this.errorStatus.getHttpStatusDto();
    }
}
