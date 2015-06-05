package com.tartner.dancehours.web.login

import com.tartner.dancehours.domain.danceuser.external.CreateDanceUserCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.util.UUID
import javax.validation.Valid

Controller
public class CreateAccountController
@Autowired
constructor(private val commandGateway: CommandGateway, private val validator: CreateAccountFormValidator) {

    RequestMapping(value = "/createAccount", method = arrayOf(RequestMethod.GET))
    public fun get(model: Model): String {
        val initialForm = CreateAccountForm()
        initialForm.setFirstName("Bill")
        initialForm.setLastName("Davis")
        initialForm.setEmail("bill@tartner.com")
        initialForm.setPassword("Abc@123")
        model.addAttribute("createAccountForm", initialForm)
        return "login/createAccount"
    }

    // TODO: add validation here
    // TODO: add command gateway, create / send command
    // TODO: login user
    // TODO: redirect to home

    RequestMapping(value = "/createAccount", method = arrayOf(RequestMethod.POST))
    public fun post(ModelAttribute("createAccountForm") Valid form: CreateAccountForm,
        bindingResult: BindingResult): String {

        validator.validate(form, bindingResult)

        if (bindingResult.hasErrors()) {
            return "login/createAccount"
        }

        // TODO: find the sequential UUID generator and use here
        commandGateway.send(
            CreateDanceUserCommand(UUID.randomUUID()).email(form.getEmail()).firstName(
                form.getFirstName()).lastName(form.getLastName()).password(form.getPassword()))

        return "/home/homeSignedIn"
    }

}
