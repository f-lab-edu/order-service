package com.flab.order.config;

import com.flab.order.global.interceptor.MemberAuthorizationInterceptor;
import com.flab.order.global.resolver.LoginMemberResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final MemberAuthorizationInterceptor memberAuthorizationInterceptor;
    private final LoginMemberResolver loginMemberResolver;
    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 인터셉터 MVC 구성에 추가
        registry.addInterceptor(memberAuthorizationInterceptor)
                .addPathPatterns("/orders/**"); // 인터셉터 적용할 경 등록
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginMemberResolver);
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }
}
