package com.tartner.dancehours.web.login;

import com.tartner.dancehours.domain.danceuser.external.CreateDanceUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class CreateAccountController {
    @Autowired private CommandGateway commandGateway;
    @Autowired private CreateAccountFormValidator validator;

    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    public String get(Model model) {
        CreateAccountForm initialForm = new CreateAccountForm();
        initialForm.setFirstName( "Bill" );
        initialForm.setLastName( "Davis" );
        initialForm.setEmail( "bill@tartner.com" );
        initialForm.setPassword( "Abc@123" );
        model.addAttribute( "createAccountForm", initialForm );
        return "login/createAccount";
    }

    // TODO: add validation here
    // TODO: add command gateway, create / send command
    // TODO: login user
    // TODO: redirect to home

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String post(
        @ModelAttribute("createAccountForm") @Valid CreateAccountForm form,
        BindingResult bindingResult ) {

        validator.validate( form, bindingResult );

        if (bindingResult.hasErrors()) {
            return "login/createAccount";
        }

        // TODO: find the sequential UUID generator and use here
        commandGateway.send( new CreateDanceUserCommand( UUID.randomUUID() )
            .email( form.getEmail() )
            .firstName( form.getFirstName() )
            .lastName( form.getLastName() )
            .password( form.getPassword() ) );

        return "/home/homeSignedIn";
    }

}
