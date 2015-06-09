package com.tartner.dancehours.web;

/**
 Some constants for the Kotlin UI forms. I realize that you really shouldn't define constants
 in an interface, but this is a work-around for the fact that you can't create compile-time
 constants in Kotlin ([public] static final ...)
 */
public interface StandardUIMessageConstants {
    String NOT_BLANK_MESSAGE = "{notBlank.message}";
    String EMAIL_MESSAGE = "{email.message}";
}
