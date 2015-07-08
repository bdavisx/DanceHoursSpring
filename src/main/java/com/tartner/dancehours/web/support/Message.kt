package com.tartner.dancehours.web.support

/**
 * A message to be displayed in web context. Depending on the type, different style will be applied.
 */
public class Message : java.io.Serializable {

    /**
     * The type of the message to be displayed. The type is used to show message in a different style.
     */
    public enum class Type {
        DANGER,
        WARNING,
        INFO,
        SUCCESS
    }

    public val message: String
    public val type: Type
    public val args: Any?

    public constructor(message: String, type: Message.Type) : this(message, type, null)

    public constructor(message: String, type: Message.Type, vararg args: Any?) {
        this.message = message
        this.type = type
		this.args = args
    }

    companion object {
        /**
         * Name of the flash attribute.
         */
        public val MESSAGE_ATTRIBUTE: String = "message"
    }
}
