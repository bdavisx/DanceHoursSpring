package com.tartner.dancehours.home;

import com.tartner.dancehours.database.DanceUser;
import com.tartner.dancehours.database.DanceUserType;
import org.apache.ibatis.session.Configuration;
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
		final DanceUser danceUser = new DanceUser();
		danceUser.setId( UUID.randomUUID() );
		danceUser.setFirstName( "Bill" );
		danceUser.setLastName( "Davis" );
		danceUser.setEmail( "bdavisx@yahoo.com" );
		danceUser.setIsActive( true );
		danceUser.setUserType( DanceUserType.Administrator );

		final SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert( "DanceUser.insertUser", danceUser );
		final List<DanceUser> users =
			sqlSession.selectList( "DanceUser.selectUsers" );

		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}
}
