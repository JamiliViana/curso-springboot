package com.acme.tuor.controller

import com.acme.tuor.model.Promocao;
import com.acme.tuor.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping (value = ["/promocoes"])
class PromocaoController {
    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("/menorQue/")
    @ResponseStatus(HttpStatus.OK)
    fun getAllMenores(@RequestParam(required = true) preco: Double) = this.promocaoService.getAllByPrecoMenorQue(preco)


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getId(@PathVariable id: Long) = this.promocaoService.getById(id)

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid promocao: Promocao) = this.promocaoService.create(promocao)


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun delete(@PathVariable id: Long) = this.promocaoService.delete(id)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(@PathVariable id: Long, @RequestBody @Valid promocao: Promocao) = this.promocaoService.update(id, promocao)

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun getAll(
        @RequestParam(required = false, defaultValue = "0") start: Int,
        @RequestParam(required = false, defaultValue = "3") size: Int, ): List<Promocao> = this.promocaoService.getAll(start, size)

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    fun count(): ResponseEntity<Map<String, Long>> = ResponseEntity.ok().body(mapOf("count" to this.promocaoService.count()))

    @GetMapping("/ordenados")
    @ResponseStatus(HttpStatus.OK)
    fun ordenados(): List<Promocao> = this.promocaoService.getAllSortedBylocal()


    @GetMapping("/byLocal/")
    fun getByLocal(@RequestParam(required = true) local: String) = this.promocaoService.searchByLocal(local);

}
