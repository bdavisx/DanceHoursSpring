package com.tartner.utilities;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;

import java.util.UUID;

public class SequentialGuidGenerator implements UUIDGenerator {
    private NoArgGenerator generator;

    public SequentialGuidGenerator( final NoArgGenerator generator ) {
        this.generator = generator;
    }

    public SequentialGuidGenerator() {
        this.generator = Generators.timeBasedGenerator();
    }

    @Override public UUID newId() {
        return generator.generate();
    }
}
