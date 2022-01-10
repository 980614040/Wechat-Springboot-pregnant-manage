package com.pregnant_mannage.filter;

import com.pregnant_mannage.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseInterceptor extends HandlerInterceptorAdapter {
    @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response, Object o) throws Exception {



        String requestURL = request.getRequestURI();
        System.out.println(requestURL);
        User user = (User) request.getSession()//用来验证用户是否登录，权限也可以在该函数检验
                .getAttribute("user_info");
        if (user == null) {//session不存在则跳转到重新登录

            try {
                response.sendRedirect("show_login_pc");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
}
}
