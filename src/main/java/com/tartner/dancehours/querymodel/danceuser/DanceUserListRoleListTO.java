package com.tartner.dancehours.querymodel.danceuser;

public class DanceUserListRoleListTO {
    private int roleId;
    private String displayName;

    public DanceUserListRoleListTO() {
    }

    public DanceUserListRoleListTO( final int roleId,
        final String displayName ) {
        this.roleId = roleId;
        this.displayName = displayName;
    }

    public int getRoleId() { return roleId; }
    public void setRoleId( final int roleId ) { this.roleId = roleId; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName( final String displayName ) { this.displayName = displayName; }
}
