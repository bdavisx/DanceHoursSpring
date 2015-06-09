package com.tartner.dancehours.web.login

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank
import java.io.Serializable

public data class CreateAccountForm(
    NotBlank(message = "{notBlank.message}")
    public var firstName: String = "",

    NotBlank(message = "{notBlank.message}")
    public var lastName: kotlin.String = "",

    NotBlank(message = "{notBlank.message}")
    Email(message = "{email.message}")
    public var email: kotlin.String = "",

    NotBlank(message = "{notBlank.message}")
    public var password: kotlin.String = ""

    ) : Serializable {

    public fun createAccount() {
    }

    companion object Constants {
        val NOT_BLANK_MESSAGE : String = "{notBlank.message}"
        val EMAIL_MESSAGE : String = "{email.message}"
    }
}
