package com.tartner.dancehours.web.error

import com.google.common.base.Throwables
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.text.MessageFormat
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class CustomErrorController {

    /**
     * Display an error page, as defined in web.xml `custom-error` element.
     */
    @RequestMapping("/generalError")
    public fun generalError(request: HttpServletRequest, response: HttpServletResponse,
        model: Model): String {
        // retrieve some useful information from the request
        val statusCode = request.getAttribute("javax.servlet.error.status_code") as Int
        val throwable = request.getAttribute("javax.servlet.error.exception") as Throwable
        // String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        val exceptionMessage = getExceptionMessage(throwable, statusCode)

        var requestUri: String? = request.getAttribute("javax.servlet.error.request_uri") as String
        if (requestUri == null) {
            requestUri = "Unknown"
        }

        val message = MessageFormat.format("{0} returned for {1} with message {2}", statusCode,
            requestUri, exceptionMessage)

        model.addAttribute("errorMessage", message)
        return "error/general"
    }

    private fun getExceptionMessage(throwable: Throwable?, statusCode: Int?): String? {
        if (throwable != null) {
            return Throwables.getRootCause(throwable).getMessage()
        }
        val httpStatus = HttpStatus.valueOf(statusCode!!)
        return httpStatus.reasonPhrase
    }
}
