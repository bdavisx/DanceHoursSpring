package com.tartner.dancehours.web.donations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DonationEntryController {
    @Autowired private DonationEntryQueryRepository queryRepository;

    @RequestMapping(value = "/NewVolunteerDonationEntry", method = RequestMethod.GET)
    public String get() {
        final String userId =
            SecurityContextHolder.getContext().getAuthentication().getName();
        DonationEntryForm form = queryRepository.CreateNewEntryForm( userId );

        return "volunteerDonations/DonationEntry";
    }
}
