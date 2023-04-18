package com.jojoidu.book.springboot.config;

import com.jojoidu.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){    //HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolvers() 통과해야함
        argumentResolvers.add(loginUserArgumentResolver);
    }


}
