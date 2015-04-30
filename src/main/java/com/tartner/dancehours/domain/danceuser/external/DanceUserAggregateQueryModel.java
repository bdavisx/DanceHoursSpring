package com.tartner.dancehours.domain.danceuser.external;

import com.tartner.dancehours.querymodel.database.tables.daos.DanceUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DanceUserAggregateQueryModel {
    @Autowired private DanceUserDao dao;

    public DanceUserAggregateQueryModel() {}

    public DanceUserAggregateQueryModel( final DanceUserDao dao ) {
        this.dao = dao;
    }

    // Note: need to make this an interface that's implemented elsewhere,
    // this inverts the dependency, but for now, implement directly and then
    // refactor later

    /** Checks to see if the email is already in the sytem. */
    public boolean userIdAlreadyExists( UUID userId ) {
        return dao.existsById( userId );
    }

    /** Checks to see if the email is already in the sytem. */
    public boolean emailAlreadyExists( String email ) {
        return dao.fetchByEmail( email ).stream().findAny().isPresent();
    }
}
