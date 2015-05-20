package com.tartner.dancehours.web.donations;

import org.joda.money.Money;
import org.joda.time.DateTime;

import java.util.UUID;

public class DonationEntryForm {
    private UUID entryId;

    private UUID memberDonationWasProvidedFor;
    private DateTime dateOfDonation;
    private UUID DonationTypeId;
    private short numberOfHours;
    private Money amountOfDonation;
    private String description;

    public UUID getEntryId() { return entryId; }
    public void setEntryId( final UUID entryId ) { this.entryId = entryId; }
    public UUID getMemberDonationWasProvidedFor() { return memberDonationWasProvidedFor; }
    public void setMemberDonationWasProvidedFor( final UUID memberDonationWasProvidedFor ) { this.memberDonationWasProvidedFor = memberDonationWasProvidedFor; }
    public DateTime getDateOfDonation() { return dateOfDonation; }
    public void setDateOfDonation( final DateTime dateOfDonation ) { this.dateOfDonation = dateOfDonation; }
    public UUID getDonationTypeId() { return DonationTypeId; }
    public void setDonationTypeId( final UUID donationTypeId ) { DonationTypeId = donationTypeId; }
    public short getNumberOfHours() { return numberOfHours; }
    public void setNumberOfHours( final short numberOfHours ) { this.numberOfHours = numberOfHours; }
    public Money getAmountOfDonation() { return amountOfDonation; }
    public void setAmountOfDonation( final Money amountOfDonation ) { this.amountOfDonation = amountOfDonation; }
    public String getDescription() { return description; }
    public void setDescription( final String description ) { this.description = description; }
}
