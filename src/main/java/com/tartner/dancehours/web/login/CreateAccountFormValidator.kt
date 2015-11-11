package com.tartner.dancehours.web.login


import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component open class CreateAccountFormValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return javaClass<CreateAccountForm>().isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        val form = target as CreateAccountForm
        // TODO: custom validation?

    }
}
