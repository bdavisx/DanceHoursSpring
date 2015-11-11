package com.tartner.dancehours.querymodel.danceuser

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component open class DanceUserListQueryModel @Autowired constructor(
    @PersistenceContext private val em: EntityManager ) {

    public fun getAllUsers(): List<DanceUserListTO> {
        return em.createQuery(
            "select due from DanceUserDataEntity due").resultList as List<DanceUserListTO>
    }
}
