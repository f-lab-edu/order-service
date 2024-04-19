package com.flab.order.service;

import com.flab.order.domain.entity.Member;
import com.flab.order.global.session.SessionMember;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final HttpSession httpSession;

    public void setAuthenticatedUser(Member member) {
        httpSession.setAttribute("memberId", SessionMember.of(member));
    }
}
