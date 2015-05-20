package com.tartner.utilities;

import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class SequentialGuidGeneratorTest {
    @Test
    public void newId() throws Exception {
        SequentialGuidGenerator generator = new SequentialGuidGenerator();

        for( int i = 0; i < 100; i++ ) {
            final UUID id = generator.newId();
//            System.out.println( id );
            assertThat( id, notNullValue() );
        }
    }

}
