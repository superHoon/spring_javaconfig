package com.taehoon.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.Controller;

import com.taehoon.interceptor.CommonInterceptor;

@EnableWebMvc //<mvc:annotation-driven>에 해당.
@ComponentScan(basePackages = {"com.taehoon"})  //<context:component-scan base-package="”com.figo.web”/">에 해당됨.
@Configuration
public class RootConfig extends WebMvcConfigurerAdapter{
	/**
     * 인터셉터 추가
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new CommonInterceptor());
    }
    /**
     * CSS / JavaScript / Image 등의 정적 리소스를 처리해주는 핸들러를 등록
     * <resources mapping="/resources/**" location="/resources/">에 해당됨.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    @Override
    public void addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry registry) {
 
        // 특별히 controller 를 타지 않아도 되는 뷰만 있는 경우 등록
        // ex) 디자인만 입힌 것들.
        registry.addViewController("/simpleView").setViewName("/simpleView");
 
 
        // 404 오류가 발생했을때 보여줄 뷰를 등록
        // registry.addViewController("/page-not-found").setViewName("errors/404");
    }
}
