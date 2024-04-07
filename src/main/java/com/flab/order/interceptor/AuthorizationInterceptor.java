package com.flab.order.interceptor;

import com.flab.order.global.exception.GeneralHandler;
import com.flab.order.global.response.statusEnums.ErrorStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MemberAuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession currentSession = request.getSession(false);
        if (currentSession == null || currentSession.getAttribute("memberEmail") == null) {
            throw new GeneralHandler(ErrorStatus._UNAUTHORIZED);
        }
        return true;
    }
}
