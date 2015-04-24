package com.tartner.domain.password;

public class UnableToCreatePasswordException extends RuntimeException {
    public UnableToCreatePasswordException( final Throwable cause ) {
        super( cause );
    }
}
