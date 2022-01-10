//package com.pregnant_mannage.filter;
//import com.pregnant_mannage.entity.User;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class MVCInterceptor extends HandlerInterceptorAdapter {
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object handler) {
//        boolean flag = true;
//        //打印出调用请求的URL地址
//        String requestURL = request.getRequestURI();
//        System.out.println(requestURL);
//        User user = (User) request.getSession()//用来验证用户是否登录，权限也可以在该函数检验
//                .getAttribute("user_info");
//        if (user == null) {//session不存在则跳转到重新登录
//            flag = false;
//            try {
//                response.sendRedirect("show_login_pc");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
       // 以下添加权限检测代码，如果不能执行此操作，可以建议过滤掉。
//        return flag;
//    }
//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView){
//    }
//}