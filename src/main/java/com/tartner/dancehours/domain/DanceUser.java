package com.tartner.dancehours.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
public class DanceUser {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id")
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private long passwordHash;

    @Enumerated(EnumType.STRING)
    private DanceUserType userType;

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
    public DanceUserType getUserType() { return userType; }
    public void setUserType( final DanceUserType userType ) { this.userType = userType; }
}
