package com.tartner.dancehours.events.danceuser;

public class DanceUserPasswordSetEvent {
    private byte[] passwordHash;
    private byte[] salt;

    public DanceUserPasswordSetEvent() {}

    public DanceUserPasswordSetEvent( final byte[] passwordHash,
        final byte[] salt ) {
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public byte[] getPasswordHash() { return passwordHash; }
    public void setPasswordHash( final byte[] passwordHash ) { this.passwordHash = passwordHash; }
    public byte[] getSalt() { return salt; }
    public void setSalt( final byte[] salt ) { this.salt = salt; }
}
