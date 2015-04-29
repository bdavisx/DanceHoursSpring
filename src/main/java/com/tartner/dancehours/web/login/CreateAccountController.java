package com.tartner.dancehours.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateAccountController {
    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String post(
        @ModelAttribute("createAccountForm") CreateAccountForm form ) {

        return "login/createAccount";
    }

}
