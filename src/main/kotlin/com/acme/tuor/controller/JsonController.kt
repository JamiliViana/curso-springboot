package com.acme.tuor.controller

import com.acme.tuor.model.Cliente
import com.acme.tuor.model.SimpleObject
import com.acme.tuor.model.Telefone
import org.hibernate.cfg.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class JsonController {

    @GetMapping("/json")
    fun getJson() = SimpleObject()

    @GetMapping("/cliente")
    fun getCliente(): Cliente{
        var telefone = Telefone("21", "27178093", "fixo")
        var cliente =  Cliente(1,"Leonardo", Date(), telefone)
        return cliente
    }
}