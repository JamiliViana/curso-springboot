package com.acme.tuor.advice


import com.acme.tuor.exception.PromocaoNotFoundException
import com.acme.tuor.model.ErrorMessage
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler (): ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(ex:MethodArgumentNotValidException,
                                              headers: HttpHeaders,
                                              status:HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> {
        val message = mutableListOf<String>()
        ex.bindingResult.allErrors.forEach {message.add("${it.defaultMessage}") }
        return handleExceptionInternal(ex, message, headers, HttpStatus.BAD_REQUEST, request)
    }
    @ExceptionHandler(PromocaoNotFoundException::class)
    fun PromocaoNotFoundExceptionHandler(exception:Exception): ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Promoção não localizada"),
        HttpStatus.NOT_FOUND)
    }

}