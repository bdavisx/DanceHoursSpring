package com.tartner.dancehours.querymodel.jpa;

import com.google.common.base.Objects;
import org.hibernate.annotations.Type;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dance_user", schema = "public", catalog = "dance_hours")
public class DanceUserEntity {
    private UUID userId;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private List<UserRolesEntity> roles;

    @Id
    @Type(type="pg-uuid")
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public UUID getUserId() {
        return userId;
    }
    public void setUserId( final UUID userId ) {
        this.userId = userId;
    }
    @Basic
    @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 254)
    public String getEmail() {
        return email;
    }
    public void setEmail( final String email ) { this.email = email; }
    @Basic
    @Column(name = "first_name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName( final String firstName ) { this.firstName = firstName; }
    @Basic
    @Column(name = "last_name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getLastName() {
        return lastName;
    }
    public void setLastName( final String lastName ) {
        this.lastName = lastName;
    }
    @Basic
    @Column(name = "is_active", nullable = false, insertable = true, updatable = true)
    public boolean getIsActive() { return isActive; }
    public void setIsActive( final boolean isActive ) { this.isActive = isActive; }

    @OneToMany
    @JoinTable(
        name = "dance_user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public List<UserRolesEntity> getRoles() { return roles; }
    public void setRoles( final List<UserRolesEntity> roles ) { this.roles = roles; }

    @Override public int hashCode() {
        return Objects.hashCode( userId );
    }
    @Override public boolean equals( final Object o ) {
        if( this == o ) { return true; }
        if( o == null || getClass() != o.getClass() ) { return false; }
        final DanceUserEntity that = (DanceUserEntity) o;
        return Objects.equal( userId, that.userId );
    }
}
