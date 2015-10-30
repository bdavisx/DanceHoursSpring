package com.tartner.dancehours.web.donations

import com.tartner.utilities.UUIDGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
public class DonationEntryQueryRepository @Autowired constructor( val uuidGenerator: UUIDGenerator ) {

    public fun CreateNewEntryForm(userId: String): DonationEntryForm {
        val form = DonationEntryForm(
            entryId = uuidGenerator.newId(),
            memberDonationWasProvidedFor = defaultMemberIdForDonations( userId ),
            donationTypeId = defaultDonationType( userId ) )

        return form
    }

    // TODO: make this work for real
    private fun defaultDonationType(userId: String): UUID {
        return UUID.randomUUID()
    }

    private fun defaultMemberIdForDonations(userId: String): UUID {
        return UUID.randomUUID()
    }
}
