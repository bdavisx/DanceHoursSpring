package com.tartner.dancehours.querymodel.danceuser

import com.tartner.dancehours.DanceHoursId
import com.tartner.utilities.Empty

public class DanceUserListTO {
    public var userId: DanceHoursId = DanceHoursId.Empty
    public var email: String = String.Empty
    public var roles: List<DanceUserListRoleListTO>? = null

    public constructor() {}

    public constructor(userId: DanceHoursId, email: String, roles: List<DanceUserListRoleListTO>) {
        this.userId = userId
        this.email = email
        this.roles = roles
    }
}
