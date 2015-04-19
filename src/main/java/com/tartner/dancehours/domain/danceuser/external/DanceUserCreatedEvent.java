package com.tartner.dancehours.domain.danceuser.external;

import com.tartner.dancehours.domain.danceuser.DanceUserRole;

import java.util.Set;
import java.util.UUID;

public class DanceUserCreatedEvent {
    private UUID userId;
    private String email;
    private String lastName;
    private String firstName;
    private Set<DanceUserRole> roles;

    public UUID getUserId() { return userId; }
    public void setUserId( final UUID userId ) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail( final String email ) { this.email = email; }
    public String getLastName() { return lastName; }
    public void setLastName( final String lastName ) {
        this.lastName = lastName;
    }
    public String getFirstName() { return firstName; }
    public void setFirstName( final String firstName ) {
        this.firstName = firstName;
    }
    public Set<DanceUserRole> getRoles() { return roles; }
    public void setRoles( final Set<DanceUserRole> roles ) {
        this.roles = roles;
    }

    @Override
    public boolean equals( final Object o ) {
        if( this == o ) { return true; }
        if( o == null || getClass() != o.getClass() ) { return false; }

        final DanceUserCreatedEvent that = (DanceUserCreatedEvent) o;

        if( !userId.equals( that.userId ) ) { return false; }
        if( !email.equals( that.email ) ) { return false; }
        if( !lastName.equals( that.lastName ) ) { return false; }
        if( !firstName.equals( that.firstName ) ) { return false; }
        return !(roles != null ? !roles.equals( that.roles ) :
            that.roles != null);

    }
    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}
