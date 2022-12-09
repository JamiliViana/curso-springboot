package com.acme.tuor.advice

import com.acme.tuor.exception.PromocaoNotFoundException
import com.acme.tuor.model.ErrorMessage
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)
    fun JsonFormatExecptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: java.lang.Exception):
            ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Json ERROR", exception.message?: "invalid json"), HttpStatus.BAD_REQUEST)

    }
    @ExceptionHandler(PromocaoNotFoundException::class)
    fun PromocaoNotFoundExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse,
                                         exception: java.lang.Exception): ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Promocao nao localizada",exception.message !!),
        HttpStatus.NOT_FOUND)
    }
}