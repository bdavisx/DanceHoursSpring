package com.tartner.utilities

import com.fasterxml.uuid.Generators
import com.fasterxml.uuid.NoArgGenerator
import com.tartner.dancehours.DanceHoursId

public class SequentialIdentifierGenerator(private val generator: NoArgGenerator) : IdentifierGenerator {
    public constructor() : this(Generators.timeBasedGenerator())
    override fun newId(): DanceHoursId { return DanceHoursId(generator.generate()) }
}
