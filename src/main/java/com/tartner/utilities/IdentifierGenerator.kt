package com.tartner.utilities

import com.tartner.dancehours.DanceHoursId

public interface IdentifierGenerator {
    public fun newId(): DanceHoursId

    companion object {
        var defaultGenerator : IdentifierGenerator = SequentialIdentifierGenerator()

        fun createDefault() : DanceHoursId = defaultGenerator.newId()
    }
}

