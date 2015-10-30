package com.tartner.dancehours.web.config

import com.tartner.Application
import nz.net.ultraq.thymeleaf.LayoutDialect
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.thymeleaf.spring4.SpringTemplateEngine
import org.thymeleaf.spring4.view.ThymeleafViewResolver
import org.thymeleaf.templateresolver.ServletContextTemplateResolver
import org.thymeleaf.templateresolver.TemplateResolver

// Note: need to move the specific configuration more "local" to the component,
// while leaving environment configuration stuff to the app

// TODO: organize pom references

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = arrayOf(Application::class))
@EnableTransactionManagement
public open class WebMVCConfiguration : WebMvcConfigurerAdapter() {

    @Bean(name = arrayOf("messageSource"))
    public open fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename(MESSAGE_SOURCE)
        messageSource.setCacheSeconds(5)
        return messageSource
    }

    @Bean
    public open fun templateResolver(): TemplateResolver {
        val templateResolver = ServletContextTemplateResolver()
        templateResolver.prefix = ViewsLocation
        templateResolver.suffix = ".html"
        templateResolver.templateMode = "HTML5"
        templateResolver.isCacheable = false
        return templateResolver
    }

    @Bean
    public open fun templateEngine(): SpringTemplateEngine {
        val templateEngine = SpringTemplateEngine()
        templateEngine.setTemplateResolver(templateResolver())
        templateEngine.addDialect(LayoutDialect())
        //        templateEngine.addDialect( new SpringStandardDialect() );
        return templateEngine
    }

    @Bean
    public open fun viewResolver(): ThymeleafViewResolver {
        val thymeleafViewResolver = ThymeleafViewResolver()
        thymeleafViewResolver.templateEngine = templateEngine()
        thymeleafViewResolver.characterEncoding = "UTF-8"
        return thymeleafViewResolver
    }

    override fun getValidator(): Validator {
        val validator = LocalValidatorFactoryBean()
        validator.setValidationMessageSource(messageSource())
        return validator
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry!!.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION)
    }

    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer?) {
        configurer!!.enable()
    }

    /**
     * Handles favicon.ico requests assuring no `404 Not Found` error is returned.
     */
    @Controller
    class FaviconController {
        @RequestMapping("favicon.ico")
        fun favicon(): String {
            return "forward:/resources/images/favicon.ico"
        }
    }

    companion object {

        private val MESSAGE_SOURCE = "/WEB-INF/i18n/messages"
        private val ViewsLocation = "/WEB-INF/views/"

        private val RESOURCES_LOCATION = "/resources/"
        private val RESOURCES_HANDLER = RESOURCES_LOCATION + "**"

        @Bean
        public open fun propertyConfigInDev(): PropertySourcesPlaceholderConfigurer {
            return PropertySourcesPlaceholderConfigurer()
        }
    }
}
