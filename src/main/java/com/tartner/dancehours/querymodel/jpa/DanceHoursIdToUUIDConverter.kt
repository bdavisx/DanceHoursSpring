package com.tartner.dancehours.querymodel.jpa

import com.tartner.dancehours.DanceHoursId
import java.util.*
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter class DanceHoursIdToUUIDConverter : AttributeConverter<DanceHoursId, UUID> {
    override fun convertToDatabaseColumn(attribute : DanceHoursId?) : UUID? {
        attribute?.let {
            if(attribute.equals(DanceHoursId.Empty)) return null

            return attribute.identifier
        }

        return null
    }

    override fun convertToEntityAttribute(dbData : UUID?) : DanceHoursId? {
        dbData?.let { return DanceHoursId(dbData) }

        return DanceHoursId.Empty
    }
}
