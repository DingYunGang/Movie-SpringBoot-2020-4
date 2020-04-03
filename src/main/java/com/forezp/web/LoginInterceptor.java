package com.forezp.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 丁云刚 on 2018/8/25.
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("name");
        System.out.println("使用"+username);
        System.out.println(url);
//        System.out.println(request.getServletPath());
        if(url.indexOf("index.html")>=0){
            return true;
        }
        if(url.indexOf("login2")>=0){
            return true;
        }
        if(url.indexOf("helloworld")>=0){
            return true;
        }
        //获取Session

        if(username != null){
            return true;
        }
        //不符合条件的，跳转到登录界面
       request.getRequestDispatcher("index.html").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
