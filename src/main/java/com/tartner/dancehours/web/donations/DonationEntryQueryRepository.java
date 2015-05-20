package com.tartner.dancehours.web.donations;

import com.tartner.utilities.GuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DonationEntryQueryRepository {
    @Autowired private GuidGenerator guidGenerator;

    public DonationEntryForm CreateNewEntryForm( String userId ) {
        DonationEntryForm form = new DonationEntryForm();
        form.setEntryId( guidGenerator.newId() );
        form.setMemberDonationWasProvidedFor(
            defaultMemberIdForDonations( userId ) );
        return form;
    }

    private UUID defaultMemberIdForDonations( final String userId ) {
        // TODO: change default implementation
        return null;
    }
}
