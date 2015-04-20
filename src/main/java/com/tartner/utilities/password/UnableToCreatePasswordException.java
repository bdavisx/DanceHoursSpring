package com.tartner.utilities.password;

public class UnableToCreatePasswordException extends RuntimeException {
    public UnableToCreatePasswordException( final Throwable cause ) {
        super( cause );
    }
}
