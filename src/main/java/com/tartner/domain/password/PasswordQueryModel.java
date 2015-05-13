package com.tartner.domain.password;

import com.tartner.dancehours.querymodel.jpa.AggregatePasswordsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.UUID;

@Component
public class PasswordQueryModel {
    @Autowired private PasswordService passwordService;
    @Autowired private AggregatePasswordRepository repository;

    public PasswordQueryModel() {}
    public PasswordQueryModel(
        final PasswordService passwordService,
        final AggregatePasswordRepository repository ) {
        this.passwordService = passwordService;
        this.repository = repository;
    }

    public boolean passwordsMatch( UUID aggregateId, String password ) {
        final AggregatePasswordsEntity aggregatePassword =
            repository.findOne( aggregateId );

        return passwordsMatch( password, aggregatePassword.getPasswordHash(),
            aggregatePassword.getSalt() );
    }

    public boolean passwordsMatch( final String password,
        final byte[] passwordHashToCompareAgainst, final byte[] salt ) {

        KeySpec keySpecification = passwordService
            .createKeySpecification( password, salt );
        final byte[] passwordHash =
            passwordService.createPasswordHash( keySpecification );
        return matches( passwordHashToCompareAgainst, passwordHash  );
    }

    private boolean matches( final byte[] expectedPasswordHash,
        final byte[] passwordHash ) {
        return Arrays.equals( expectedPasswordHash, passwordHash );
    }
}
