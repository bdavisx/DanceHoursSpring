package com.tartner.dancehours.home;

import com.tartner.dancehours.domain.DanceUser;
import com.tartner.dancehours.domain.DanceUserType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomeController {
	@Autowired
	private SessionFactory sessionFactory;

	private HomeController() {
		int i = 5;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
		final DanceUser danceUser = new DanceUser();
		danceUser.setFirstName( "Bill" );
		danceUser.setLastName( "Davis" );
		danceUser.setEmail( "bdavisx@yahoo.com" );
		danceUser.setIsActive( true );
		danceUser.setUserType( DanceUserType.Administrator );
		final Session session = sessionFactory.openSession();
		session.persist( danceUser );
		session.close();

		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}
}
