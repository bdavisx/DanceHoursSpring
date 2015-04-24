package com.tartner.domain.password;

import com.tartner.dancehours.querymodel.database.tables.daos.AggregatePasswordsDao;
import com.tartner.dancehours.querymodel.database.tables.pojos.AggregatePasswords;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.spec.PBEKeySpec;
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

        KeySpec keySpecification = createKeySpecification( password,
            aggregatePassword.getSalt() );
        final byte[] passwordHash = createPasswordHash( keySpecification );
        return matches( aggregatePassword.getPasswordHash(), passwordHash  );
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
