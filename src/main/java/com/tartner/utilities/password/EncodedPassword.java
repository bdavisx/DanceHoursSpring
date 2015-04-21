package com.tartner.utilities.password;

import java.util.Arrays;

public class EncodedPassword {
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
