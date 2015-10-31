package com.tartner.utilities

import com.google.common.base.Strings

class KPreconditions {
    companion object {
        fun checkNotEmpty(value : String) {
            if (Strings.isNullOrEmpty(value)) {
                throw IllegalArgumentException("Empty String when there shouldn't be")
            }
        }
    }
}
