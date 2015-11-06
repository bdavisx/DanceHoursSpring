package com.tartner.utilities

import org.junit.Test
import kotlin.test.assertFailsWith

class KPreconditionsTest {
    @Test
    public fun checkEmptyValue_NonEmptyString_NotBeAnError() {
        KPreconditions.checkNotEmpty("not empty")
    }

    @Test
    public fun checkEmptyValue_EmptyString_BeDefaultErrorMessage() {
        assertFailsWith( IllegalAccessException::class.java, {
            KPreconditions.checkNotEmpty("")
        } )
    }


}
