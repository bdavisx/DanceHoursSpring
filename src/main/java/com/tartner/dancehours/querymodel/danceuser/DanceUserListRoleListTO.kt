package com.tartner.dancehours.querymodel.danceuser

public class DanceUserListRoleListTO {
    public var roleId: Int = 0
    public var displayName: String? = null

    public constructor() {}

    public constructor(roleId: Int, displayName: String) {
        this.roleId = roleId
        this.displayName = displayName
    }
}
