package com.tartner.domain.password;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordService {
    // The higher the number of iterations the more
    // expensive computing the hash is for us and
    // also for an attacker.
    private static final int DesiredKeyLength = 256;
    private static final int NumberOfHashingIterations = 65536;
    public static final String SecretKeyAlgorithm = "PBKDF2WithHmacSHA1";

    public PasswordService() { }

    byte[] createPasswordHash( final KeySpec keySpecification ) {
        try {
            final SecretKeyFactory keyFactory =
                SecretKeyFactory.getInstance( SecretKeyAlgorithm );

            return keyFactory.generateSecret( keySpecification ).getEncoded();
        } catch( NoSuchAlgorithmException | InvalidKeySpecException exception ) {
            throw new UnableToCreatePasswordException( exception );
        }
    }

    PBEKeySpec createKeySpecification( final String password,
        final byte[] salt ) {
        return new PBEKeySpec( password.toCharArray(), salt,
            NumberOfHashingIterations, DesiredKeyLength );
    }
}
