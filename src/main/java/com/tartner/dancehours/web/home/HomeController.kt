package com.tartner.dancehours.web.home

import com.tartner.dancehours.querymodel.danceuser.DanceUserListQueryModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.security.Principal

@Controller
public class HomeController {
    @Autowired private val queryModel: DanceUserListQueryModel? = null

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    public fun index(principal: Principal?): String {
        val allUsers = queryModel!!.getAllUsers()

        return if (principal != null) "home/homeSignedIn" else "redirect:/login"
    }
}
