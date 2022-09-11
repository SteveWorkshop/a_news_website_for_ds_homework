package com.example.mynews.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        if(request.getSession().getAttribute("uid")==null)
        {
            //前端搞去
            //response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}