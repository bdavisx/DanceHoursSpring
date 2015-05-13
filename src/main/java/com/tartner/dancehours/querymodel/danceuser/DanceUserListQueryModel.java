package com.tartner.dancehours.querymodel.danceuser;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class DanceUserListQueryModel {
    @PersistenceContext private EntityManager em;

    public DanceUserListQueryModel() {}

    public List<DanceUserListTO> getAllUsers() {
        return em.createQuery("select due from DanceUserEntity due").getResultList();
    }
}
