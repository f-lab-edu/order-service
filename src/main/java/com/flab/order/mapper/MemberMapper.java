package com.flab.order.mapper;

import com.flab.order.domain.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member selectMemberById(Long id);
}
