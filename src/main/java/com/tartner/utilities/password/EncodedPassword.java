package com.tartner.utilities.password;

import com.google.common.base.Objects;

public class EncodedPassword {
    private final byte[] passwordHash;
    private final byte[] salt;

    public EncodedPassword( final byte[] passwordHash, final byte[] salt ) {
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public boolean matches( final byte[] passwordHash ) {
        return Objects.equal( this.passwordHash, passwordHash );
    }

    public byte[] getSalt() { return salt; }

    @Override
    public boolean equals( final Object o ) {
        if( this == o ) { return true; }
        if( o == null || getClass() != o.getClass() ) { return false; }
        final EncodedPassword that = (EncodedPassword) o;
        return Objects
            .equal( passwordHash, that.passwordHash ) &&
            Objects.equal( salt, that.salt );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode( passwordHash, salt );
    }
}
