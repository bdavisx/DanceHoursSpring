package com.tartner.utilities

import com.fasterxml.uuid.Generators
import com.fasterxml.uuid.NoArgGenerator

import java.util.UUID

public class SequentialGuidGenerator(private val generator: NoArgGenerator) : UUIDGenerator {
    public constructor() : this(Generators.timeBasedGenerator())
    override fun newId(): UUID { return generator.generate() }
}
