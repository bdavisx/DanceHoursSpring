package com.tartner.dancehours

import com.google.common.base.MoreObjects
import com.tartner.utilities.IdentifierGenerator
import com.tartner.utilities.emptyUUID
import java.io.Serializable
import java.util.*

class DanceHoursId internal constructor ( public val identifier : UUID ) : Serializable {

    override fun equals(other : Any?) : Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as DanceHoursId

        return identifier == other.identifier
    }

    override fun hashCode() : Int{ return identifier.hashCode()}

    override fun toString() : String {
        return MoreObjects.toStringHelper(this).add("identifier", identifier).toString()
    }

    companion object Default {
        private val EmptyUUID : UUID = emptyUUID()
        public val Empty = DanceHoursId(EmptyUUID)

        fun create(uuid : UUID) = DanceHoursId(uuid)

        fun create() = IdentifierGenerator.createDefault()
    }
}
