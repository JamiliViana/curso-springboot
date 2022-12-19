package com.acme.tuor.exception

import org.springframework.http.HttpStatus

class PromocaoNotFoundException(message: String,status: HttpStatus):Exception(message) {

}