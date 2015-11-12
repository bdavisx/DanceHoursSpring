package com.tartner.dancehours.querymodel.jpa

import com.tartner.dancehours.DanceHoursId
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import java.util.*

class DanceHoursIdToUUIDConverterTest {
    @Test
    public fun ConvertToColumn_NonNull_ShouldNotBeNull() {
        val uuid = UUID.randomUUID()
        val id = DanceHoursId(uuid)

        val converter = DanceHoursIdToUUIDConverter()
        val convertedUUID = converter.convertToDatabaseColumn(id)

        MatcherAssert.assertThat(convertedUUID, equalTo(uuid))
    }

}
