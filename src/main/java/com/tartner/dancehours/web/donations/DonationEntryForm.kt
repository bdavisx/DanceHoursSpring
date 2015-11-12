package com.tartner.dancehours.web.donations

import com.tartner.dancehours.DanceHoursId
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.joda.time.DateTime
import java.math.BigDecimal

class DonationEntryForm() {
    public var entryId: DanceHoursId = DanceHoursId.Empty
    public var memberDonationWasProvidedFor: DanceHoursId = DanceHoursId.Empty
    public var donationTypeId: DanceHoursId = DanceHoursId.Empty
    public var dateOfDonation: DateTime = DateTime.now()
    public var numberOfHours: Short = 0
    public var amountOfDonation: Money = Money.of( CurrencyUnit.USD, BigDecimal(0))
    public var description: String = ""
}
