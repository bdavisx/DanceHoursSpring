package com.tartner.dancehours.commands.danceuser;

import com.tartner.dancehours.domain.danceuser.DanceUserRole;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class CreateDanceUserCommand {
    @TargetAggregateIdentifier
    private final UUID userId;
    private String email;
    private String lastName;
    private String firstName;
    private String password;
    private Optional<Set<DanceUserRole>> roles;

    public CreateDanceUserCommand( final UUID userId ) { this.userId = userId; }

    public UUID getUserId() { return userId;  }
    public String getEmail() { return email;  }
    public void setEmail( final String email ) { this.email = email; }
    public String getLastName() { return lastName; }
    public void setLastName( final String lastName ) {
        this.lastName = lastName;
    }
    public String getFirstName() { return firstName; }
    public void setFirstName( final String firstName ) {
        this.firstName = firstName;
    }
    public String getPassword() { return password; }
    public void setPassword( final String password ) {
        this.password = password;
    }
    public Optional<Set<DanceUserRole>> getRoles() { return roles; }
    public void setRoles( final Optional<Set<DanceUserRole>> roles ) {
        this.roles = roles;
    }

    public CreateDanceUserCommand email( final String email ) {
        this.email = email;
        return this;
    }
    public CreateDanceUserCommand lastName( final String lastName ) {
        this.lastName = lastName;
        return this;
    }
    public CreateDanceUserCommand firstName( final String firstName ) {
        this.firstName = firstName;
        return this;
    }
    public CreateDanceUserCommand password( final String password ) {
        this.password = password;
        return this;
    }
    public CreateDanceUserCommand roles(
        final Optional<Set<DanceUserRole>> roles ) {
        this.roles = roles;
        return this;
    }
}
