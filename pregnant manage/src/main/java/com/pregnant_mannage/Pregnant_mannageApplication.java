package com.pregnant_mannage;

import com.pregnant_mannage.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
 public class Pregnant_mannageApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        //SpringApplication.run(pregnant_mannageApplication.class, args);

        ApplicationContext applicationContext =
                SpringApplication.run(Pregnant_mannageApplication.class, args);
        SpringUtil.setApplicationContext(applicationContext);
        SpringUtil.printBean();

    }
    @Override
    //加入静态资源
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/fonts/");

        super.addResourceHandlers(registry);
    }

}
