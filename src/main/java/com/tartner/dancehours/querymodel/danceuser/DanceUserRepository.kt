package com.tartner.dancehours.querymodel.danceuser

import com.tartner.dancehours.querymodel.jpa.DanceUserEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

import java.util.UUID

public interface DanceUserRepository : CrudRepository<DanceUserEntity, UUID> {
    
    Query("SELECT count(danceUser) from DanceUserEntity danceUser where danceUser.email = ?1")
    public fun existsByEmail(email: String): Long
    
    Query("SELECT danceUser from DanceUserEntity danceUser where danceUser.email = ?1")
    public fun findByEmail(email: String): DanceUserEntity
}
