package com.example.mynews.config;

import com.example.mynews.interceptor.UserLoginInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

public class UserLoginInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new UserLoginInterceptor();

        // 白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/assets/**");
        patterns.add("/index.html");
        patterns.add("/login.html");
        patterns.add("/register.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/images/**");
        patterns.add("user_types/find_all");
        patterns.add("/admins/login");
        patterns.add("/**/admin/**");

        // 通过注册工具添加拦截器
        //registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    }
}