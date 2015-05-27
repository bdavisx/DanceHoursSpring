package com.tartner.dancehours.web.donations

import scala.beans.BeanProperty

class DonationEntryPageData {
    @BeanProperty var memberList: List[DanceMemberListItem] = null
    @BeanProperty var preSelectedMember: DanceMemberListItem = null
}
