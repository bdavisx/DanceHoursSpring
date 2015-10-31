package com.tartner;

import org.junit.Assert;


public class ExpectExceptionInTest {
    public static void expectException( Class<?> clazz, Runnable code ) {
        boolean caughtException = false;

        try {
            code.run();
        } catch( Exception ex ) {
            caughtException = clazz.isAssignableFrom(ex.getClass());
        }

        if(!caughtException) {
            Assert.fail("Did not get expected exception: " + clazz.getCanonicalName() );
        }
    }
}
