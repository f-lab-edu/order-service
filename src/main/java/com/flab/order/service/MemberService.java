package com.flab.order.service;

import com.flab.order.domain.entity.Member;
import com.flab.order.global.exception.GeneralHandler;
import com.flab.order.global.response.statusEnums.ErrorStatus;
import com.flab.order.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final SessionService sessionService;

    public boolean loginUser(String email, String password) {
        return memberMapper.findByEmail(email)
                .map(member -> checkPasswordAndLogin(member, password))
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.MEMBER_NOT_EXIST));
    }

    private boolean checkPasswordAndLogin(Member member, String password) {
        if (!member.getPassword().equals(password)) {
            throw new GeneralHandler(ErrorStatus.INVALID_PASSWORD);
        }
        sessionService.setAuthenticatedUser(member.getId());
        return true;
    }
}
