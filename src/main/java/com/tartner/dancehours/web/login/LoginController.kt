package com.tartner.dancehours.web.login

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.util.Arrays

Controller
public class LoginController {

    RequestMapping(value = "/login", method = arrayOf(RequestMethod.GET))
    public fun get(): String {
        return "login/login"
    }

    RequestMapping(value = "/login", method = arrayOf(RequestMethod.POST))
    public fun post(
        RequestParam("username") username: String,
        RequestParam("password") password: String): String {

        // TODO: implement actual login

        val user = User(username, "",
            Arrays.asList<GrantedAuthority>(*arrayOf<GrantedAuthority>(object : GrantedAuthority {
                override fun getAuthority(): String {
                    return "ROLE_USER"
                }
            })))
        val authentication = UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())

        SecurityContextHolder.getContext().setAuthentication(authentication)

        return "redirect:/"
    }
}
