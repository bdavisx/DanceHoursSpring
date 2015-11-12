package com.tartner.dancehours.web.config

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet
import javax.servlet.ServletContext
import javax.servlet.ServletException

public open class DanceHoursWebApplicationInitializer : WebApplicationInitializer {

    @Throws(ServletException::class)
    override fun onStartup(servletContext: ServletContext) {
        //Load application context
        val rootContext = AnnotationConfigWebApplicationContext()
        rootContext.register(*arrayOf<Class<*>>(WebMVCConfiguration::class.java))
        rootContext.displayName = "Dance Hours"

        //Context loader listener
        servletContext.addListener(ContextLoaderListener(rootContext))

        //Dispatcher servlet
        val dispatcher = servletContext.addServlet("dispatcher", DispatcherServlet(rootContext))
        dispatcher.setLoadOnStartup(1)
        dispatcher.addMapping("/")
    }
}
