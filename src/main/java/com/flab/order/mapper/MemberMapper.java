package com.flab.order.mapper;

import com.flab.order.domain.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
}
