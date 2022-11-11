package com.example.boot.core;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** @author ltk */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/index").setViewName("/index");
  }

  /**
   * 拦截器
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
     registry.addInterceptor(new MyInterceptor())
             .addPathPatterns("/**");
  }

  /**
   * 过滤器
   * @return
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  @Bean
  public FilterRegistrationBean filterRegister() {
    FilterRegistrationBean frBean = new FilterRegistrationBean();
    frBean.setFilter(new MyFilter());
    frBean.addUrlPatterns("/*");
    return frBean;
  }

  // @SuppressWarnings({"rawtypes", "unchecked"})
  // @Bean
  // public ServletListenerRegistrationBean listenerRegist() {
  //     ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
  //     srb.setListener(new MyHttpSessionListener());
  //     System.out.println("listener");
  //     return srb;
  // }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
  }
}
