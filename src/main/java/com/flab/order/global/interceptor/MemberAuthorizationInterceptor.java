package com.flab.order.global.interceptor;


import com.flab.order.global.exception.GeneralHandler;
import com.flab.order.global.response.statusEnums.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MemberAuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession currentSession = request.getSession(false);
        if (currentSession == null || currentSession.getAttribute("memberId") == null)
            throw new GeneralHandler(ErrorStatus._UNAUTHORIZED);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @NoArgsConstructor
    @Getter
    public static class AuthorizationException extends RuntimeException {
        public AuthorizationException(String message) {
            super(message);
        }
    }
}
