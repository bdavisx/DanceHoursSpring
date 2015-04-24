package com.tartner.domain.password;

import com.thoughtworks.xstream.XStream;

import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordGenerator {
    private PasswordService passwordService;

    public PasswordGenerator( final PasswordService passwordService ) {
        this.passwordService = passwordService;
    }

    public static void main( String[] args ) {
        PasswordService service = new PasswordService();
        PasswordGenerator me = new PasswordGenerator( service );
        try {
            me.makePassword( "P@ssw0rd" );
        } catch( NoSuchAlgorithmException e ) {
            throw new RuntimeException( e );
        }
    }

    private static final int SaltLength = 32;

    public void makePassword( final String password )
        throws NoSuchAlgorithmException {
        byte[] salt = SecureRandom.getInstanceStrong().generateSeed(
            SaltLength );

        KeySpec keySpecification = createKeySpecification( password, salt );

        final byte[] passwordHash = createPasswordHash( keySpecification );
        TestPasswordHolder holder = new TestPasswordHolder();
        holder.passwordHash = passwordHash;
        holder.salt = salt;

        XStream x = new XStream();
        final String passwordData = x.toXML( holder );
        System.out.println( passwordData );
    }

    private boolean matches( final byte[] expectedPasswordHash,
        final byte[] passwordHash ) {
        return Arrays.equals( expectedPasswordHash, passwordHash );
    }

    private byte[] createPasswordHash( final KeySpec keySpecification ) {
        return passwordService.createPasswordHash( keySpecification );
    }

    private PBEKeySpec createKeySpecification( final String password,
        final byte[] salt ) {
        return passwordService.createKeySpecification( password, salt );
    }
}
