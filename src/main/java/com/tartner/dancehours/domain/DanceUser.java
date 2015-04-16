package com.tartner.dancehours.domain;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DanceUser {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private long passwordHash;
    private List<DanceUserRole> userRoles;

    public ImmutableList<DanceUserRole> getUserRoles() {
        return ImmutableList.copyOf( userRoles );
    }
    public void setUserRoles( final List<DanceUserRole> userRoles ) {
        this.userRoles = new ArrayList<>( userRoles );
    }

    public UUID getId() { return id; }
    public void setId( final UUID id ) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName( final String firstName ) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName( final String lastName ) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail( final String email ) { this.email = email; }
    public boolean isActive() { return isActive; }
    public void setIsActive( final boolean isActive ) { this.isActive = isActive; }
    public long getPasswordHash() { return passwordHash; }
    public void setPasswordHash( final long passwordHash ) { this.passwordHash = passwordHash; }

}