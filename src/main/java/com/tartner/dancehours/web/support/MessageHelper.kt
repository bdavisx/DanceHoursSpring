package com.tartner.dancehours.web.support

import org.springframework.ui.Model
import org.springframework.web.servlet.mvc.support.RedirectAttributes

public object MessageHelper {

    public fun addSuccessAttribute(ra: RedirectAttributes, message: String, vararg args: Any) {
        addAttribute(ra, message, Message.Type.SUCCESS, *args)
    }

    public fun addErrorAttribute(ra: RedirectAttributes, message: String, vararg args: Any) {
        addAttribute(ra, message, Message.Type.DANGER, *args)
    }

    public fun addInfoAttribute(ra: RedirectAttributes, message: String, vararg args: Any) {
        addAttribute(ra, message, Message.Type.INFO, *args)
    }

    public fun addWarningAttribute(ra: RedirectAttributes, message: String, vararg args: Any) {
        addAttribute(ra, message, Message.Type.WARNING, *args)
    }

    private fun addAttribute(ra: RedirectAttributes, message: String, type: Message.Type,
        vararg args: Any) {
        ra.addFlashAttribute(Message.MESSAGE_ATTRIBUTE, Message(message, type, *args))
    }

    public fun addSuccessAttribute(model: Model, message: String, vararg args: Any) {
        addAttribute(model, message, Message.Type.SUCCESS, *args)
    }

    public fun addErrorAttribute(model: Model, message: String, vararg args: Any) {
        addAttribute(model, message, Message.Type.DANGER, *args)
    }

    public fun addInfoAttribute(model: Model, message: String, vararg args: Any) {
        addAttribute(model, message, Message.Type.INFO, *args)
    }

    public fun addWarningAttribute(model: Model, message: String, vararg args: Any) {
        addAttribute(model, message, Message.Type.WARNING, *args)
    }

    private fun addAttribute(model: Model, message: String, type: Message.Type, vararg args: Any) {
        model.addAttribute(Message.MESSAGE_ATTRIBUTE, Message(message, type, *args))
    }
}
