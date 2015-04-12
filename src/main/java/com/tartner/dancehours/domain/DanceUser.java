package com.tartner.dancehours.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DanceUser {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private long passwordHash;

    @Enumerated(EnumType.STRING)
    private DanceUserType userType;

    public Long getId() { return id; }
    public void setId( final Long id ) { this.id = id; }
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
    public DanceUserType getUserType() { return userType; }
    public void setUserType( final DanceUserType userType ) { this.userType = userType; }
}
