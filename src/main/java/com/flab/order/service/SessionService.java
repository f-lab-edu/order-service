package com.flab.order.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final HttpSession httpSession;

    public void setAuthenticatedUser(Long memberId) {
        httpSession.setAttribute("memberId", memberId);
    }
}
