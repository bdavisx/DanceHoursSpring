package com.tartner.dancehours.web.donations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class DonationEntryController @Autowired ( queryRepository: DonationEntryQueryRepository ) {

    @RequestMapping(value = Array("/NewVolunteerDonationEntry"), method = Array(RequestMethod.GET)) def get: String = {
        val userId: String = SecurityContextHolder.getContext.getAuthentication.getName
        val form: DonationEntryForm = queryRepository.CreateNewEntryForm(userId)
        return "volunteerDonations/DonationEntry"
    }
}
