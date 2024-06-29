package com.mac.spzx.manager.config;

import com.mac.spzx.manager.interceptar.LoginAuthInterceptor;
import com.mac.spzx.manager.properties.UserAuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年06月27日, 15:38:04
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    private LoginAuthInterceptor loginAuthInterceptor;
    private UserAuthProperties userAuthProperties;

    @Autowired
    public WebMvcConfiguration(LoginAuthInterceptor loginAuthInterceptor,
                                UserAuthProperties userAuthProperties) {
        this.loginAuthInterceptor = loginAuthInterceptor;
        this.userAuthProperties = userAuthProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("*");
    }

    // 将拦截器注册到Spring MVC中

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                // TODO 后续优化排除的路径，从配置文件读入
                .addPathPatterns("/**")
                .excludePathPatterns(userAuthProperties.getNoAuthUrls());
    }
}