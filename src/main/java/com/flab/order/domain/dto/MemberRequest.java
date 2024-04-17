package com.flab.order.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

public class MemberRequest {

    @Value
    public static class Login{
        @NotBlank(message = "이메일을 입력해 주세요.")
        @Email(message = "유효하지 않은 이메일 형식입니다.")
        String email;
        @NotBlank(message = "비밀번호를 입력해 주세요.")
        String password;

        @JsonCreator
        public Login(@JsonProperty("email") String email, @JsonProperty("password") String password) {
            this.email = email;
            this.password = password;
        }
    }
}
