package com.tartner.utilities.password;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordService {
    private static final Logger log =
        LoggerFactory.getLogger( PasswordService.class );

    // The higher the number of iterations the more
    // expensive computing the hash is for us and
    // also for an attacker.
    private static final int NumberOfHashingIterations = 65536;
    private static final int SaltLength = 32;
    private static final int DesiredKeyLength = 256;
    public static final String SecretKeyAlgorithm = "PBKDF2WithHmacSHA1";

    private final SecureRandom random;

    public PasswordService( SecureRandom random ) {
        this.random = random;
    }

    public EncodedPassword createEncodedPassword(String password) {
        final byte[] salt = random.generateSeed( SaltLength );
        KeySpec keySpecification = CreateKeySpecification( password, salt );
        final byte[] passwordHash = createPasswordHash( keySpecification );

        // store the salt with the password
        EncodedPassword encodedPassword = new EncodedPassword( passwordHash, salt );
        return encodedPassword;
    }

    private byte[] createPasswordHash( final KeySpec keySpecification ) {
        try {
            final SecretKeyFactory keyFactory =
                SecretKeyFactory.getInstance( SecretKeyAlgorithm );

            return keyFactory.generateSecret( keySpecification ).getEncoded();
        } catch( NoSuchAlgorithmException|InvalidKeySpecException exception ) {
            throw new UnableToCreatePasswordException( exception );
        }
    }

    public boolean passwordsMatch( String password, EncodedPassword encodedPassword ) {
        KeySpec keySpecification = CreateKeySpecification( password,
            encodedPassword.getSalt() );
        final byte[] passwordHash = createPasswordHash( keySpecification );
        return encodedPassword.matches( passwordHash  );
    }

    private PBEKeySpec CreateKeySpecification( final String password,
        final byte[] salt ) {
        return new PBEKeySpec(password.toCharArray(), salt,
            NumberOfHashingIterations, DesiredKeyLength );
    }
}
