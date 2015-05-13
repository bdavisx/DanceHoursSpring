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
@Table(name = "aggregate_passwords", schema = "public", catalog = "dance_hours")
public class AggregatePasswordsEntity {
    private UUID aggregateId;
    private byte[] passwordHash;
    private byte[] salt;
    @Id
    @Column(name = "aggregate_id", nullable = false, insertable = true, updatable = true)
    @Type(type="pg-uuid")
    public UUID getAggregateId() {
        return aggregateId;
    }
    public void setAggregateId( final UUID aggregateId ) {
        this.aggregateId = aggregateId;
    }
    @Basic
    @Column(name = "password_hash", nullable = false, insertable = true, updatable = true)
    public byte[] getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash( final byte[] passwordHash ) {
        this.passwordHash = passwordHash;
    }
    @Basic
    @Column(name = "salt", nullable = false, insertable = true, updatable = true)
    public byte[] getSalt() {
        return salt;
    }
    public void setSalt( final byte[] salt ) {
        this.salt = salt;
    }
    @Override public int hashCode() {
        return Objects.hashCode( aggregateId, passwordHash, salt );
    }
    @Override public boolean equals( final Object o ) {
        if( this == o ) { return true; }
        if( o == null || getClass() != o.getClass() ) { return false; }
        final AggregatePasswordsEntity that = (AggregatePasswordsEntity) o;
        return
            Objects.equal( aggregateId, that.aggregateId ) &&
                Objects
                    .equal( passwordHash, that.passwordHash ) &&
                Objects.equal( salt, that.salt );
    }
}
