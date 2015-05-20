package com.tartner.dancehours.web.config;

import com.tartner.utilities.GuidGenerator;
import com.tartner.utilities.SequentialGuidGenerator;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.contextsupport.spring.AnnotationDriven;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.jdbc.JdbcEventStore;
import org.axonframework.unitofwork.DefaultUnitOfWorkFactory;
import org.axonframework.unitofwork.SpringTransactionManager;
import org.axonframework.unitofwork.TransactionManager;
import org.axonframework.unitofwork.UnitOfWorkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@AnnotationDriven
public class AxonConfiguration {
    @Autowired private DataSource dataSource;
    @Autowired private PlatformTransactionManager platformTransactionManager;

    @Bean
    public GuidGenerator guidGenerator() {
        return new SequentialGuidGenerator();
    }

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

    @Bean
    public UnitOfWorkFactory unitOfWorkFactory() {
        DefaultUnitOfWorkFactory factory =
            new DefaultUnitOfWorkFactory( transactionManager() );
        return factory;
    }

    @Bean
    public TransactionManager transactionManager() {
        SpringTransactionManager transactionManager =
            new SpringTransactionManager( platformTransactionManager );
        return transactionManager;
    }
}
