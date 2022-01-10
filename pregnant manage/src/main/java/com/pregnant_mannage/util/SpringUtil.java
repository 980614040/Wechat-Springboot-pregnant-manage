package com.pregnant_mannage.util;

import org.springframework.context.ApplicationContext;
import java.util.Arrays;
public class SpringUtil {
    public static ApplicationContext applicationContext = null;
    public  static void setApplicationContext(ApplicationContext ctx){
        SpringUtil.applicationContext = ctx;
    }
    //这个类是用来获取系统帮你自动生成的类的对象的。
    //printBean是用来查询系统帮自己生成了那些对象。这样可以直接通过名字调用
    public static void printBean(){
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
