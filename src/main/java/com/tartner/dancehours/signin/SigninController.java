package com.tartner.dancehours.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SigninController {

	@RequestMapping(value = "signin", method = RequestMethod.GET)
	public String get() {
        return "signin/signin";
    }

    @RequestMapping(value = "signin", method = RequestMethod.POST)
    public String post() {
        return "signin/signin";
    }
}
