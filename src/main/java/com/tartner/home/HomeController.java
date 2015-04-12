package com.tartner.home;

import java.security.Principal;

import com.tartner.domain.DanceUser;
import com.tartner.domain.DanceUserType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
public class HomeController {
	@PersistenceContext
	private EntityManager em;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
		final DanceUser danceUser = new DanceUser();
		danceUser.setFirstName( "Bill" );
		danceUser.setLastName( "Davis" );
		danceUser.setEmail( "bdavisx@yahoo.com" );
		danceUser.setIsActive( true );
		danceUser.setUserType( DanceUserType.Administrator );
		em.persist( danceUser );

		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}
}
