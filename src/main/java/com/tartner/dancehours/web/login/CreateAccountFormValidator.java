package com.tartner.dancehours.web.login;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreateAccountFormValidator implements Validator {
    @Override
    public boolean supports( final Class<?> clazz ) {
        return CreateAccountForm.class.isAssignableFrom( clazz );
    }
    @Override
    public void validate( final Object target, final Errors errors ) {
        CreateAccountForm form = (CreateAccountForm) target;
        // TODO: custom validation?

    }
}
