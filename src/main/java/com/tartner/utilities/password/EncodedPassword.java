package com.tartner.utilities.password;

import java.util.Arrays;

public class EncodedPassword {
    // TODO: need to create passwordHash and Salt classes, make sure
    // those and this one are compatible w/ XStream; then change the
    // event to have an EncodedPassword instead of the separate properties
    private final byte[] passwordHash;
    private final byte[] salt;

    public EncodedPassword( final byte[] passwordHash, final byte[] salt ) {
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public boolean matches( final byte[] passwordHash ) {
        return Arrays.equals( this.passwordHash, passwordHash );
    }

    public byte[] getSalt() { return salt; }
}
