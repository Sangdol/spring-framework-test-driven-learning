package com.sangdol.springSimple;

import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author hugh
 */
@Configuration
public class AnnotationConfigWebApplicationContextLoader {
    // https://spring.io/projects
    // http://projects.spring.io/spring-framework/
    // http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html
    // http://docs.spring.io/spring/docs/current/spring-framework-reference/html/overview.html
    public static void main(String[] args) {
        WebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ServletContextListener listener = new ContextLoaderListener(ctx);
        ServletContextEvent servletContextEvent = new ServletContextEvent(new MockServletContext());
        listener.contextInitialized(servletContextEvent);

//        System.out.println(ctx.getDisplayName());
//        System.out.println(ctx.getBeanDefinitionCount());
//        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));

        for (String s : ctx.getBeanDefinitionNames()) {
            System.out.println(s);
        }

    }
}
