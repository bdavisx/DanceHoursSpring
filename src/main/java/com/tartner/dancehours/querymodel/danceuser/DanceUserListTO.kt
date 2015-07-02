package com.tartner.dancehours.querymodel.danceuser

import java.util.UUID

public class DanceUserListTO {
    public var userId: UUID? = null
    public var email: String? = null
    public var roles: List<DanceUserListRoleListTO>? = null

    public constructor() {}

    public constructor(userId: UUID, email: String, roles: List<DanceUserListRoleListTO>) {
        this.userId = userId
        this.email = email
        this.roles = roles
    }
}
