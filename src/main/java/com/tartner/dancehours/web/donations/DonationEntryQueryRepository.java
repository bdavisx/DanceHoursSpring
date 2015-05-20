package com.tartner.dancehours.web.donations;

import com.tartner.utilities.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DonationEntryQueryRepository {
    @Autowired private UUIDGenerator uuidGenerator;

    public DonationEntryForm CreateNewEntryForm( String userId ) {
        DonationEntryForm form = new DonationEntryForm();
        form.setEntryId( uuidGenerator.newId() );
        form.setMemberDonationWasProvidedFor(
            defaultMemberIdForDonations( userId ) );
        return form;
    }

    private UUID defaultMemberIdForDonations( final String userId ) {
        // TODO: change default implementation
        return null;
    }
}
