package com.kotlin.api.handeler

import org.apache.commons.lang3.StringUtils
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder


class ResponseBase(
    var warningMessage: String? = null,
    var errorMessage: String? = null
) {

    private var messageSource: MessageSource? = null

    private fun getLangMessage(msg: String): String? {
        val message: String = StringUtils.substringBetween(msg, "{", "}") ?: return msg
        val anotherMessage = msg.replace("{$message}", "")
        return """
             ${messageSource!!.getMessage(message, null, LocaleContextHolder.getLocale())}
             ${if (!anotherMessage.isEmpty()) anotherMessage else ""}
             """.trimIndent()
    }
}