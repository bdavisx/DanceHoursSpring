package com.tartner.dancehours.domain.danceuser.external

import com.tartner.dancehours.querymodel.danceuser.DanceUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

public interface DanceUserAggregateQueryModel {
    public fun userIdAlreadyExists(userId: UUID): Boolean
    public fun emailAlreadyExists(email: String): Boolean
}

@Component
public class DefaultDanceUserAggregateQueryModel @Autowired constructor(
    private val repository: DanceUserRepository ) : DanceUserAggregateQueryModel {

    // Note: need to make this an interface that's implemented elsewhere,
    // this inverts the dependency, but for now, implement directly and then
    // refactor later

    /** Checks to see if the email is already in the sytem.  */
    public override fun userIdAlreadyExists(userId: UUID): Boolean {
        return repository.exists(userId)
    }

    /** Checks to see if the email is already in the system.  */
    public override fun emailAlreadyExists(email: String): Boolean {
        return repository.existsByEmail(email) > 0
    }
}

