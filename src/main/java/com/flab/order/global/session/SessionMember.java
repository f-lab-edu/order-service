package com.flab.order.global.session;

import com.flab.order.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SessionMember {
    private Long memberId;
    public static SessionMember of(Member member){
        return new SessionMember(member.getId());
    }
}
