package com.tartner.domain.password;

import java.util.UUID;

public class PasswordSetEvent {
    private UUID aggregateId;
    private byte[] passwordHash;
    private byte[] salt;

    public PasswordSetEvent() {}

    public PasswordSetEvent( final UUID aggregateId, final byte[] passwordHash,
        final byte[] salt ) {
        this.aggregateId = aggregateId;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public UUID getAggregateId() { return aggregateId; }
    public void setAggregateId( final UUID aggregateId ) { this.aggregateId = aggregateId; }
    public byte[] getPasswordHash() { return passwordHash; }
    public void setPasswordHash( final byte[] passwordHash ) { this.passwordHash = passwordHash; }
    public byte[] getSalt() { return salt; }
    public void setSalt( final byte[] salt ) { this.salt = salt; }
}
