package com.tartner.dancehours.web.donations

data class DonationEntryPageData(
    public var memberList: List<DanceMemberListItem>,
    public var preSelectedMember: DanceMemberListItem )
