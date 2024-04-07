package com.flab.order.controller;

import com.flab.order.domain.dto.MemberRequest;
import com.flab.order.global.exception.GeneralHandler;
import com.flab.order.global.response.ApiResponse;
import com.flab.order.global.response.statusEnums.ErrorStatus;
import com.flab.order.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 API", description = "회원 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "로그인 API")
    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody MemberRequest.Login request, HttpSession session) {
        boolean isAuthenticated = memberService.authenticate(request.getEmail(), request.getPassword());
        if (isAuthenticated) {
            session.setAttribute("memberEmail", request.getEmail());
            return ApiResponse.onSuccess("로그인에 성공하였습니다.");
        }
        throw new GeneralHandler(ErrorStatus.INVALID_PASSWORD);
    }

}
