package com.tartner.utilities

import com.google.common.base.Strings

class KPreconditions {
    companion object {
        fun checkNotEmpty(value : String) {
            checkNotEmpty(value, "Empty String when there shouldn't be")
        }

        fun checkNotEmpty(value : String, message : String) {
            if (Strings.isNullOrEmpty(value)) {
                throw IllegalArgumentException(message)
            }
        }
    }
}
