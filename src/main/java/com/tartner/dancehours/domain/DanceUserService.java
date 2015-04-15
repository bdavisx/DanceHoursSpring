package com.tartner.dancehours.domain;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DanceUserService {
    @Autowired private SqlSessionFactory sqlSessionFactory;

    public void createUser( final DanceUser newUser ) {
        // TODO: validate newUser
        // TODO: validate no other user w/ email

        final SqlSession session = sqlSessionFactory.openSession();
        session.insert( "DanceUser.insertUser", newUser );
    }

    public boolean doesUserExist( final String email ) {
        final SqlSession session = sqlSessionFactory.openSession();
        return ((int) session.selectOne( "DanceUser.selectEmailCount", email )) > 0;
    }

}
