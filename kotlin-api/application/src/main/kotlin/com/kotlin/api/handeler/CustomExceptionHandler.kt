package com.kotlin.api.handeler

import mu.KotlinLogging
import org.apache.commons.lang3.StringUtils
import org.springframework.boot.web.server.ErrorPage
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaSystemException
import org.springframework.transaction.TransactionSystemException
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.util.*
import java.util.stream.Collectors
import javax.persistence.PersistenceException
import javax.validation.ConstraintViolationException


@RestControllerAdvice
class CustomExceptionHandler(
    private var messageSource: MessageSource
) {

    private val log = KotlinLogging.logger {}

    @ExceptionHandler(GeneralException::class)
    fun generalException(ex: Exception): ResponseEntity<ResponseBase?>? {
        log.error("GeneralException", ex)
        var error = ResponseBase(errorMessage = getLangMessage(ex.message, null))
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(GeneralWarning::class)
    fun generalWarning(ex: java.lang.Exception): ResponseEntity<ResponseBase?>? {
        log.error("GeneralWarning", ex)
        val warning = ResponseBase(warningMessage = getLangMessage(ex.message, null))
        return ResponseEntity(warning, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [RuntimeException::class])
    fun runtimeException(ex: java.lang.Exception): ResponseEntity<ResponseBase?>? {
        log.error("RuntimeException", ex)
        val error = ResponseBase(errorMessage = getLangMessage(ex.message, null))
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(JpaSystemException::class)
    fun jpaSystemException(ex: java.lang.Exception): ResponseEntity<ResponseBase?>? {
        log.error("JpaSystemException", ex)
        val error = ResponseBase(errorMessage = getLangMessage(ex.cause!!.message, null))
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(PersistenceException::class)
    fun persistenceException(ex: PersistenceException?): ResponseEntity<ResponseBase?>? {
        log.error("PersistenceException", ex)
        val error = ResponseBase(errorMessage = getLangMessage("{review.data}", null))
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun dataIntegrityViolationException(ex: DataIntegrityViolationException?): ResponseEntity<ResponseBase?>? {
        log.error("DataIntegrityViolationException", ex)
        val error = ResponseBase(errorMessage = getLangMessage("{before.data.enter}", null))
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationException(ex: ConstraintViolationException?): ResponseEntity<ResponseBase?>? {
        log.error("ConstraintViolationException", ex)
        val error = ResponseBase(errorMessage = StringUtils.join(getViolationsMsg(ex), ", "))
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }


    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ResponseBase?>? {
        log.error("MethodArgumentNotValidException", ex)

        val allError: MutableList<Any>? = ex.bindingResult.allErrors.stream().map<Any?> { m: ObjectError ->
            getLangMessage(
                m.defaultMessage,
                if (m.arguments != null)
                    Arrays.stream(m.arguments).filter { f -> f !is DefaultMessageSourceResolvable }.toArray() else null
            )
        }.toList()
        val error = ResponseBase(errorMessage = StringUtils.join(allError, ", "))
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [TransactionSystemException::class])
    fun transactionSystemException(ex: TransactionSystemException): ResponseEntity<ResponseBase?>? {
        log.error("TransactionSystemException", ex)
        val error = ResponseBase()
        val t = ex.cause
        if (t is ConstraintViolationException) {
            error.errorMessage = StringUtils.join(getViolationsMsg(t as ConstraintViolationException?), ", ")
        } else if (t!!.cause != null) {
            val tt = t.cause
            if (tt is ConstraintViolationException) {
                error.errorMessage = StringUtils.join(getViolationsMsg(tt as ConstraintViolationException?), ", ")
            } else {
                error.errorMessage = ex.message
            }
        } else {
            error.errorMessage = ex.message
        }
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [MethodArgumentTypeMismatchException::class])
    fun MethodArgumentTypeMismatchException(ex: java.lang.Exception?) {
        log.error("MethodArgumentTypeMismatchException", ex)
        ErrorPage(HttpStatus.NOT_FOUND, "/error")
    }

    @ExceptionHandler(value = [java.lang.Exception::class])
    fun unknownException(ex: java.lang.Exception?): ResponseEntity<ResponseBase?>? {
        log.error("Exception", ex)
        val error = ResponseBase(errorMessage = getLangMessage("{unknown.exception}", null))
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    private fun getLangMessage(msg: String?, arguments: Array<Any>?): String? {
        val message: String = StringUtils.substringBetween(msg, "{", "}") ?: return msg
        val anotherMessage = msg?.replace("{$message}", "")
        return """
             ${messageSource.getMessage(message, arguments, LocaleContextHolder.getLocale())}
             ${if (!anotherMessage!!.isEmpty()) anotherMessage else ""}
             """.trimIndent()
    }

    private fun getViolationsMsg(ex: ConstraintViolationException?): List<String?>? {
        val errorMessages: MutableList<String?> = ArrayList()
        for (violation in ex!!.constraintViolations) {
            val attributes = violation.constraintDescriptor.attributes
            val keysForFiltering: List<String> = listOf("groups", "inclusive", "message", "payload")
            val objects = attributes.entries
                .stream()
                .filter { (key): Map.Entry<String, Any> ->
                    !keysForFiltering.contains(key)
                }
                .map<Any> { (key, value) -> java.util.Map.entry(key, value) }
                .collect(Collectors.toList<Any>())
            errorMessages.add(getLangMessage(violation.message, objects.toTypedArray()))
        }
        return errorMessages
    }
}