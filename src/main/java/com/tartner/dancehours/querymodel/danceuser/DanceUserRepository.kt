package com.tartner.dancehours.querymodel.danceuser

import com.tartner.dancehours.DanceHoursId
import com.tartner.dancehours.querymodel.jpa.DanceUserDataEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

public interface DanceUserRepository : CrudRepository<DanceUserDataEntity, DanceHoursId> {

    @Query("SELECT count(danceUser) from DanceUserEntity danceUser where danceUser.email = ?1")
    public fun existsByEmail(email: String): Long

    @Query("SELECT danceUser from DanceUserEntity danceUser where danceUser.email = ?1")
    public fun findByEmail(email: String): DanceUserDataEntity
}
