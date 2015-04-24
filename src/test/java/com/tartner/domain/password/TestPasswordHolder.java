package com.tartner.domain.password;

import com.thoughtworks.xstream.XStream;

public class TestPasswordHolder {
    public static final String TestHolderDefaultXStreamXML =
        "<com.tartner.domain.password.TestPasswordHolder>\n" +
            "  <passwordHash>M1oUJRFvTJIfY77zzcV+bWz0dLvT7b37QzeRXIogjW0=</passwordHash>\n" +
            "  <salt>jZenrXxOBVyKMPXssoo+KxjZxD+tfql2EiO8mWVS1YI=</salt>\n" +
            "</com.tartner.domain.password.TestPasswordHolder>";

    public static TestPasswordHolder CreateDefaultTest() {
        XStream x = new XStream();
        return (TestPasswordHolder) x.fromXML( TestHolderDefaultXStreamXML );
    }

    public byte[] passwordHash;
    public byte[] salt;
    public TestPasswordHolder() {}

    public byte[] getPasswordHash() { return passwordHash; }
    public void setPasswordHash( final byte[] passwordHash ) { this.passwordHash = passwordHash; }
    public byte[] getSalt() { return salt; }
    public void setSalt( final byte[] salt ) { this.salt = salt; }
}
