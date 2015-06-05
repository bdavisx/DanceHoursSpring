package com.tartner.dancehours.web.donations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

Controller class DonationEntryController Autowired constructor(
    val queryRepository: DonationEntryQueryRepository ) {

    RequestMapping( "/NewVolunteerDonationEntry", method = arrayOf(RequestMethod.GET))

    fun get(): String {
        val userId: String = SecurityContextHolder.getContext().getAuthentication().getName()
        val form: DonationEntryForm = queryRepository.CreateNewEntryForm(userId)

        return "volunteerDonations/DonationEntry"
    }
}
