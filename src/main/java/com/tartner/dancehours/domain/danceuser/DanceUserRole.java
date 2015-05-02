package com.tartner.dancehours.domain.danceuser;

// TODO: make data driven
public enum DanceUserRole {
    Administrator( 1, "Administrator" ),
    ParentGuardian( 2, "Parent/Guardian" ),
    CompanyMember( 3, "Company Member" ),
    JuniorCompanyMember( 4, "Junior Company Member" ),
    ApprenticeCompanyMember( 5, "Apprentice Company Member" ),
    PerformanceCompanyMember( 6, "Performance Company Member" );

    private final int roleId;
    private final String displayName;

    private DanceUserRole( int roleId, String displayName ) {
        this.roleId = roleId;
        this.displayName = displayName;
    }

    public int getRoleId() { return roleId; }
    public String getDisplayName() { return displayName; }
}
