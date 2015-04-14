package com.tartner.dancehours.home;

import com.tartner.dancehours.database.DanceUser;
import com.tartner.dancehours.database.DanceUserRole;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	private HomeController() {
		int i = 5;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}
}
