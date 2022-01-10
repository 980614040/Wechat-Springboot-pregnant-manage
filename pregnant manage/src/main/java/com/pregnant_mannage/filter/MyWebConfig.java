package com.pregnant_mannage.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;


@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BaseInterceptor())
                .excludePathPatterns("/login", "/statics/**");
                //需要配置2：----------- 告知拦截器：/static/admin/** 与 /static/user/** 不需要拦截 （配置的是 路径）

    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        registry.addResourceHandler("/css/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/css/");
//        registry.addResourceHandler("/js/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/js/");
//        registry.addResourceHandler("/img/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/img/");
//        registry.addResourceHandler("/fonts/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/fonts/");
//
//        super.addResourceHandlers(registry);
//        //加入新的静态资源
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        //registry.addViewController("/error/404").setViewName("/admin/page_error/error_404.html");
//    }
}









//@Configuration
//public class MyWebConfig extends WebMvcConfigurationSupport {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MVCInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/login")
////                .excludePathPatterns("/register")
//                .excludePathPatterns("/js/*")
//                .excludePathPatterns("/css/*")
//                .excludePathPatterns("/img/*")
//                .excludePathPatterns("/error")
//                .excludePathPatterns("/fonts/*")
//                .excludePathPatterns("/*.js")
//                .excludePathPatterns("/*.html")
//                .excludePathPatterns("/check_login");
//
//    }
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//        //registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
//        //super.addResourceHandlers(registry);
//    }
//}
