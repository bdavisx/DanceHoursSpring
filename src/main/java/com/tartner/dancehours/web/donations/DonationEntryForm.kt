package com.tartner.dancehours.web.donations

import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.joda.time.DateTime
import java.math.BigDecimal
import java.util.UUID

public data class DonationEntryForm(
    public var entryId: UUID,

    public var memberDonationWasProvidedFor: UUID,
    public var donationTypeId: UUID,
    public var dateOfDonation: DateTime = DateTime.now(),
    public var numberOfHours: Short = 0,
    public var amountOfDonation: Money = Money.of( CurrencyUnit.USD, BigDecimal(0)),
    public var description: String = "" )
