package com.tartner.dancehours.web.login

import com.tartner.dancehours.web.StandardUIMessageConstants
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank

public data class CreateAccountForm(
    @NotBlank(message = StandardUIMessageConstants.NOT_BLANK_MESSAGE)
    public var fullName: String = "",

    @NotBlank(message = StandardUIMessageConstants.NOT_BLANK_MESSAGE)
    @Email(message = StandardUIMessageConstants.EMAIL_MESSAGE)
    public var email: kotlin.String = "",

    @NotBlank(message = StandardUIMessageConstants.NOT_BLANK_MESSAGE)
    public var password: kotlin.String = ""

    ) {

    public fun createAccount() {
    }
}
