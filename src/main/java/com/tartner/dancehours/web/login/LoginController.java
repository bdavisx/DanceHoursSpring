package com.tartner.dancehours.web.login;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String get() {
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String post( @RequestParam("username") String username,
        @RequestParam("password") String password ) {

        // TODO: implement actual login

        final User user = new User( username, "", Arrays.asList(
            new GrantedAuthority[] {
                new GrantedAuthority() {
                    public String getAuthority() { return "ROLE_USER"; }
                } } ) );
        Authentication authentication =
            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }
}
