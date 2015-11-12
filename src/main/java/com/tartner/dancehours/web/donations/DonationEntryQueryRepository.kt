package com.tartner.dancehours.web.donations

import com.tartner.dancehours.DanceHoursId
import com.tartner.utilities.IdentifierGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
public class DonationEntryQueryRepository @Autowired constructor( val identifierGenerator : IdentifierGenerator) {

    public fun CreateNewEntryForm(userId: String): DonationEntryForm {
        val form = DonationEntryForm().apply {
            entryId = identifierGenerator.newId()
            memberDonationWasProvidedFor = defaultMemberIdForDonations(userId)
            donationTypeId = defaultDonationType(userId)
        }

        return form
    }

    // TODO: make this work for real
    private fun defaultDonationType(userId: String): DanceHoursId {
        return identifierGenerator.newId()
    }

    private fun defaultMemberIdForDonations(userId: String): DanceHoursId {
        return identifierGenerator.newId()
    }
}
