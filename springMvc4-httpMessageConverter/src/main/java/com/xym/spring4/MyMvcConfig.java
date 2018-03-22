package com.xym.spring4;

import com.xym.spring4.domain.DemoObj;
import com.xym.spring4.messageconverter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * @author xym
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.xym.spring4")
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }


    /*需要经过controller的mapping，简单映射到页面配置*/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/converter").setViewName("converter");
    }


    /**
     * 配置自定义的HttpMessageConverter的Bean，在Spring MVC里面注册HttpMessageConverter有两个方法：
     * <p>
     * 1. 在文件MyMvcConfig中配置configureMessageConverters：重载会覆盖掉Spring MVC默认注册的多个HttpMessageConverter。
     * 2. 在文件MyMvcConfig中配置extendMessageConverters：仅添加一个自定义的HttpMessageConverter，不覆盖默认注册的HttpMessageConverter。
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(myHttpMessageConverter());
    }

    @Bean
    public HttpMessageConverter<DemoObj> myHttpMessageConverter() {
        return new MyMessageConverter();
    }

    /**
     * 静态资源css/js
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }
}