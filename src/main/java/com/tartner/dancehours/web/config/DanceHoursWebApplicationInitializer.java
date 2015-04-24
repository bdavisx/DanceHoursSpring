package com.tartner.dancehours.web.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class DanceHoursWebApplicationInitializer implements
    WebApplicationInitializer {

    @Override
    public void onStartup( final ServletContext servletContext )
        throws ServletException {
        //Load application context
        AnnotationConfigWebApplicationContext
            rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register( new Class<?>[] {
            WebMVCConfiguration.class, PersistenceConfiguration.class,
            SecurityConfig.class, AxonConfiguration.class,
            PasswordConfiguration.class } );
        rootContext.setDisplayName("Dance Hours");

        //Context loader listener
        servletContext.addListener(new ContextLoaderListener(rootContext));

        //Dispatcher servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
            new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
