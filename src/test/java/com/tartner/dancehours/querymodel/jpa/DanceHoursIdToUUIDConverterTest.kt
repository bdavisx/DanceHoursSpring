package com.tartner.dancehours.querymodel.jpa

import com.tartner.dancehours.DanceHoursId
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.equalTo
import org.hamcrest.core.IsNull
import org.junit.Test
import java.util.*

class DanceHoursIdToUUIDConverterTest {
    @Test
    public fun ConvertToColumn_NonNull_ShouldNotBeNull() {
        val uuid = UUID.randomUUID()
        val id = DanceHoursId(uuid)

        val converter = CreateConverter()
        val convertedUUID = converter.convertToDatabaseColumn(id)

        MatcherAssert.assertThat(convertedUUID, equalTo(uuid))
    }

    @Test
    public fun ConvertTooColumn_Empty_ShouldBeNull() {
        val id = DanceHoursId.Empty

        val converter = CreateConverter()
        val convertedUUID = converter.convertToDatabaseColumn(id)

        MatcherAssert.assertThat(convertedUUID, IsNull())
    }

    private fun CreateConverter() = DanceHoursIdToUUIDConverter()

    @Test
    public fun ConvertToEntityAttribute_NonNull_ShouldNotBeNull() {
        val uuid = UUID.randomUUID()

        val converter = CreateConverter()
        val convertedId : DanceHoursId? = converter.convertToEntityAttribute(uuid)

        MatcherAssert.assertThat(convertedId?.identifier, equalTo(uuid))
    }

    @Test
    public fun ConvertToEntityAttribute_Null_ShouldBeNull() {
        val converter = CreateConverter()
        val convertedId : DanceHoursId? = converter.convertToEntityAttribute(null)

        MatcherAssert.assertThat(convertedId, equalTo(DanceHoursId.Empty))
    }

    @Test
    public fun ConvertToEntityAttribute_Empty_ShouldBeNull() {
        val converter = CreateConverter()
        val convertedId : DanceHoursId? = converter.convertToEntityAttribute(DanceHoursId.EmptyUUID)

        MatcherAssert.assertThat(convertedId, equalTo(DanceHoursId.Empty))
    }
}
