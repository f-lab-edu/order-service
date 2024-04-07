package com.flab.order.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequest {

    @NoArgsConstructor
    @Getter
    public static class Login{
        @NotBlank(message = "이메일을 입력해 주세요.")
        @Email(message = "유효하지 않은 이메일 형식입니다.")
        private String email;
        @NotBlank(message = "비밀번호를 입력해 주세요.")
        private String password;
    }
}
