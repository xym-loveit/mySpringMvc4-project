package com.xym.spring4;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author xym
 */
public class WebInitialize implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        /*Spring 上下文*/
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(MyMvcConfig.class);
        applicationContext.setServletContext(servletContext);

        /*Spring MVC上下文*/
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
    }
}
