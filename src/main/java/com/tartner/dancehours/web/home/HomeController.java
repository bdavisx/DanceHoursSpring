package com.tartner.dancehours.web.home;

import com.tartner.dancehours.querymodel.danceuser.DanceUserListQueryModel;
import com.tartner.dancehours.querymodel.danceuser.DanceUserListTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
	@Autowired private DanceUserListQueryModel queryModel;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
        final List<DanceUserListTO> allUsers = queryModel.getAllUsers();

        return principal != null ? "home/homeSignedIn" : "redirect:/login";
	}
}
