package com.tartner.dancehours.querymodel.danceuser;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.tartner.dancehours.querymodel.database.tables.DanceUser.DANCE_USER;
import static com.tartner.dancehours.querymodel.database.tables.DanceUserRoles.DANCE_USER_ROLES;

@Component
public class DanceUserListQueryModel {
    @Autowired private DSLContext dsl;

    public DanceUserListQueryModel() {}

    public List<DanceUserListTO> getAllUsers() {
        final Result<?> record = dsl.select()
            .from( DANCE_USER )
            .leftOuterJoin( DANCE_USER_ROLES )
            .on( DANCE_USER.USER_ID.equal( DANCE_USER_ROLES.USER_ID ) )
            .fetch();

        System.out.println( record );

        return new ArrayList<>();
    }
}
