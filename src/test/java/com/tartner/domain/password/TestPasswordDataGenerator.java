package com.tartner.domain.password;

import com.thoughtworks.xstream.XStream;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class TestPasswordDataGenerator {
    private PasswordService passwordService;

    public TestPasswordDataGenerator( final PasswordService passwordService ) {
        this.passwordService = passwordService;
    }

    public static void main( String[] args ) {
        PasswordService service = new PasswordService();
        TestPasswordDataGenerator me = new TestPasswordDataGenerator( service );
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

        KeySpec keySpecification =
            passwordService.createKeySpecification( password, salt );

        final byte[] passwordHash =
            passwordService.createPasswordHash( keySpecification );
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

}
