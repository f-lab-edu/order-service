package com.flab.order.global.interceptor;


import com.flab.order.global.exception.GeneralHandler;
import com.flab.order.global.response.statusEnums.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MemberAuthorizationInterceptor implements HandlerInterceptor { // HTTP 요청에 대해 사용자 인증을 확인하는 인터셉터
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { // 핸들러가 실행되기 전에 호출
        HttpSession currentSession = request.getSession(false);
        if (currentSession == null || currentSession.getAttribute("memberId") == null)
            throw new GeneralHandler(ErrorStatus._UNAUTHORIZED);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
