package com.tartner.dancehours.domain.danceuser

// TODO: make data driven
public enum class DanceUserRole private constructor(
    public val roleId: Int, public val displayName: String) {

    Administrator(1, "Administrator"),
    ParentGuardian(2, "Parent/Guardian"),
    CompanyMember(3, "Company Member"),
    JuniorCompanyMember(4, "Junior Company Member"),
    ApprenticeCompanyMember(5, "Apprentice Company Member"),
    PerformanceCompanyMember(6, "Performance Company Member")
}
