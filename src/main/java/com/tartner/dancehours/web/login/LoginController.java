package com.tartner.dancehours.web.login;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

import static com.tartner.dancehours.querymodel.database.tables.DanceUser.DANCE_USER;

@Controller
public class LoginController {
    @Autowired
    DSLContext dsl;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String get() {
        dsl.insertInto( DANCE_USER )
            .set( DANCE_USER.USER_ID, UUID.randomUUID() )
            .set( DANCE_USER.EMAIL, "b@c.com" )
            .set( DANCE_USER.FIRST_NAME, "Bill")
            .set( DANCE_USER.LAST_NAME, "Davis" )
            .set( DANCE_USER.IS_ACTIVE, true )
            .set( DANCE_USER.USER_TYPE, "" )
            .execute();

        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String post() {

        return "login/createAccount";
    }
}
