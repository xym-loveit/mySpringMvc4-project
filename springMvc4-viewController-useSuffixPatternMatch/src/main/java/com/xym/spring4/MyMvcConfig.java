package com.xym.spring4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 更多配置参见WebMvcConfigurerAdater API
 *
 * @author xym
 */
@Configuration
@ComponentScan("com.xym.spring4")
@EnableWebMvc
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();
        resourceViewResolver.setViewClass(JstlView.class);
        resourceViewResolver.setPrefix("/WEB-INF/classes/views/");
        resourceViewResolver.setSuffix(".jsp");
        return resourceViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }

    /*通过重写该方法--简化简单的页面跳转*/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
    }

    /**
     * 在Spring MVC中，路径参数如果带”.”的话，”.”后面的值将被忽略，例如，访问http://localhost/springMvc4.x-pathMatchParameter/anno/pathvar/xxx.yy，此时，”.”后面的yy被忽略
     *
     * @param configurer
     */

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }
}
