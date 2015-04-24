package com.tartner.domain.password;

import com.tartner.dancehours.querymodel.database.tables.daos.AggregatePasswordsDao;
import com.tartner.dancehours.querymodel.database.tables.pojos.AggregatePasswords;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.UUID;

public class PasswordQueryModel {
    @Autowired private PasswordService passwordService;
    @Autowired private AggregatePasswordsDao dao;

    public PasswordQueryModel() {
    }

    PasswordQueryModel( final PasswordService passwordService,
        final AggregatePasswordsDao dao ) {
        this.passwordService = passwordService;
        this.dao = dao;
    }

    public boolean passwordsMatch( UUID aggregateId, String password ) {
        final AggregatePasswords aggregatePassword =
            dao.fetchOneByAggregateId( aggregateId );

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
