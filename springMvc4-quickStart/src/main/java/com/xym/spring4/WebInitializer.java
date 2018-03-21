package com.xym.spring4;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * ① WebApplicationInitializer是Spring提供用来配置Servlet3.0+配置的接口，从而实现替代web.xml的位置。实现此接口将会自动被SpringServletContainerInitializer(用来启动Servlet3.0容器)获取到。
 * ② 新建WebApplicationContext，注册配置类，并将其和当前servletContext关联。
 * ③ 注册Spring MVC的DispatcherServlet。
 *
 * @author xym
 */
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyMvcConfig.class);
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}