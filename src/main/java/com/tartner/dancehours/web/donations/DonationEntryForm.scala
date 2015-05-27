package com.tartner.dancehours.web.donations

import java.util.UUID

import org.joda.money.Money
import org.joda.time.DateTime

import scala.beans.BeanProperty

class DonationEntryForm {
    @BeanProperty var entryId: UUID = null
    @BeanProperty var memberDonationWasProvidedFor: UUID = null
    @BeanProperty var dateOfDonation: DateTime = null
    @BeanProperty var DonationTypeId: UUID = null
    @BeanProperty var numberOfHours: Short = 0
    @BeanProperty var amountOfDonation: Money = null
    @BeanProperty var description: String = null
}
