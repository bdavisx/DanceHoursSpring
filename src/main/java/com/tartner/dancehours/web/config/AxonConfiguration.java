package com.tartner.dancehours.web.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.jdbc.JdbcEventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AxonConfiguration {
    @Autowired
    private DataSource dataSource;

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    public CommandBus commandBus() {
        return new SimpleCommandBus();
    }

    @Bean
    public CommandGateway commandGateway() {
        return new DefaultCommandGateway( commandBus() );
    }

    @Bean
    public EventStore eventStore() {
        JdbcEventStore eventStore = new JdbcEventStore( dataSource );
        return eventStore;
    }

    @Bean
    public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
        final AnnotationEventListenerBeanPostProcessor postProcessor =
            new AnnotationEventListenerBeanPostProcessor();
        postProcessor.setEventBus( eventBus() );
        return postProcessor;
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        AnnotationCommandHandlerBeanPostProcessor postProcessor =
            new AnnotationCommandHandlerBeanPostProcessor();
        postProcessor.setCommandBus( commandBus() );
        return postProcessor;
    }
}
