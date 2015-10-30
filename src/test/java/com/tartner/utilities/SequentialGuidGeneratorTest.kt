package com.tartner.utilities

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.Test

public class SequentialGuidGeneratorTest {
    @Test @throws(Exception::class)
    public fun newId() {
        val generator = SequentialGuidGenerator()

        for (i in 0..99) {
            val id = generator.newId()
            //            System.out.println( id );
            assertThat(id, notNullValue())
        }
    }

}
