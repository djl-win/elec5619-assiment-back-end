package com.group50.config;

import com.group50.handler.SpringHandlerLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 处理拦截器等操作（拦截器写在了handler中）
 */
@Configuration
public class SpringMvcSupport implements WebMvcConfigurer {

    @Autowired
    private SpringHandlerLogin springHandlerLogin;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//未启用拦截，等页面搞好再看看
//        registry.addInterceptor(springHandlerLogin)
//                .addPathPatterns("/pages/**")
//                .excludePathPatterns("/pages/index.html","/pages/sign.html");
    }


}
