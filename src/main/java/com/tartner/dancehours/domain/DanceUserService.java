package com.tartner.dancehours.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DanceUserService {

    public void createUser( final DanceUser newUser ) {
        // TODO: validate newUser
        // TODO: validate no other user w/ email

//        final SqlSession session = sqlSessionFactory.openSession();
//        session.insert( "DanceUser.insertUser", newUser );
    }

    public boolean doesUserExist( final String email ) {
//        final SqlSession session = sqlSessionFactory.openSession();
//        return ((int) session.selectOne( "DanceUser.selectEmailCount", email )) > 0;
        return false;
    }

}
