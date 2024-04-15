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

    public boolean authenticate(String email, String password) {
        Member member = memberMapper.findByEmail(email)
                .orElseThrow(()-> new GeneralHandler(ErrorStatus.MEMBER_NOT_EXIST));

        return member.getPassword().equals(password);
    }
}
