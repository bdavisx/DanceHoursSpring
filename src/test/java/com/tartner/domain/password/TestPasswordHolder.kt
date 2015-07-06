package com.tartner.domain.password

import com.thoughtworks.xstream.XStream

/** This class has a pre-generated passwordHash and Salt from the
 * DefaultMatchingPassword that you can use when testing password code.
 */

public class TestPasswordHolder {

    public var passwordHash: ByteArray = ByteArray(0)
    public var salt: ByteArray = ByteArray(0)

    companion object {
        public val DefaultMatchingPassword: String = "P@ssw0rd"

        public val TestHolderDefaultXStreamXML: String = """
            <com.tartner.domain.password.TestPasswordHolder>
                <passwordHash>M1oUJRFvTJIfY77zzcV+bWz0dLvT7b37QzeRXIogjW0=</passwordHash>
                <salt>jZenrXxOBVyKMPXssoo+KxjZxD+tfql2EiO8mWVS1YI=</salt>
            </com.tartner.domain.password.TestPasswordHolder>"""

        public fun CreateDefaultTest(): TestPasswordHolder {
            val x = XStream()
            return x.fromXML(TestHolderDefaultXStreamXML) as TestPasswordHolder
        }
    }
}
