package com.tartner.dancehours.querymodel.danceuser

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
public class DanceUserListQueryModel @Autowired constructor(
    @PersistenceContext private val em: EntityManager ) {

    public fun getAllUsers(): List<DanceUserListTO> {
        return em.createQuery(
            "select due from DanceUserEntity due").resultList as List<DanceUserListTO>
    }
}
