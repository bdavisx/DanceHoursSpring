package com.tartner.utilities

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.Test

public class SequentialIdentifierGeneratorTest {
    @Test
    public fun newId() {
        val generator = SequentialIdentifierGenerator()
        for (i in 0..99) {
            val id = generator.newId()
            //            System.out.println( id );
            assertThat(id, notNullValue())
        }
    }

}
