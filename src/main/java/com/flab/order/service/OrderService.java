package com.flab.order.service;

import com.flab.order.domain.dto.OrderResponse;
import com.flab.order.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberService memberService;
    private final CartService cartService;
    public OrderResponse.Success orderCartItems(Long memberId){
        // 로그인 회원 정보 불러오기
        Member member = memberService.getMember(memberId);
        // 장바구니 상품 재고와 가격 확인
        cartService.checkStockAndPrice(member);

        return null;
    }
}
