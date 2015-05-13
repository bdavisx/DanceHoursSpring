package com.tartner.dancehours.querymodel.jpa;

import com.google.common.base.Objects;
import org.hibernate.annotations.Type;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "user_roles", schema = "public", catalog = "dance_hours")
public class UserRolesEntity {
    private UUID roleId;
    private String name;
    private String description;
    private Boolean isAdmin;
    @Id
    @Type(type="pg-uuid")
    @Column(name = "role_id", nullable = false, insertable = true, updatable = true)
    public UUID getRoleId() {
        return roleId;
    }
    public void setRoleId( final UUID roleId ) {
        this.roleId = roleId;
    }
    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }
    public void setName( final String name ) {
        this.name = name;
    }
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getDescription() {
        return description;
    }
    public void setDescription( final String description ) {
        this.description = description;
    }
    @Basic
    @Column(name = "is_admin", nullable = true, insertable = true, updatable = true)
    public Boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin( final Boolean isAdmin ) {
        this.isAdmin = isAdmin;
    }
    @Override public int hashCode() {
        return Objects.hashCode( roleId, name, description, isAdmin );
    }
    @Override public boolean equals( final Object o ) {
        if( this == o ) { return true; }
        if( o == null || getClass() != o.getClass() ) { return false; }
        final UserRolesEntity that = (UserRolesEntity) o;
        return Objects.equal( roleId, that.roleId ) &&
            Objects.equal( name, that.name ) &&
            Objects.equal( description, that.description ) &&
            Objects.equal( isAdmin, that.isAdmin );
    }
}
