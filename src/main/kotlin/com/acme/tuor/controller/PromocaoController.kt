package com.acme.tuor.controller

import com.acme.tuor.exception.PromocaoNotFoundException
import com.acme.tuor.model.ErrorMessage
import com.acme.tuor.model.Promocao;
import com.acme.tuor.model.RespostaJSON
import com.acme.tuor.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping (value = ["/promocoes"])
class PromocaoController {
    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("/menorQue/")
    fun getAllMenores(@RequestParam(required = true) preco: Double): Any {
        val list = this.promocaoService.getAllByPrecoMenorQue(preco);
        if(list.size == 0){
            return ResponseEntity(ErrorMessage("Promoções não localizadas","Promocões com o valor menor que ${preco}, não foram localizadas"),
                HttpStatus.NOT_FOUND)
        } else
        return ResponseEntity(list,HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getId(@PathVariable id:Long): ResponseEntity<Any>{
        var promocao = this.promocaoService.getById(id)
        return if(promocao != null)
            return ResponseEntity(promocao,HttpStatus.OK)
        else
            return ResponseEntity(ErrorMessage("Promoção não localizada","Promocao ${id} não localizada"),
                HttpStatus.NOT_FOUND)
        }

    @PostMapping()
    fun create(@RequestBody promocao: Promocao): ResponseEntity<RespostaJSON> {
        this.promocaoService.create(promocao)
        val respostaJSON= RespostaJSON("OK", Date())
        return ResponseEntity(respostaJSON,HttpStatus.CREATED)
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long): ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if(this.promocaoService.getById(id) != null){
            status = HttpStatus.ACCEPTED
            this.promocaoService.delete(id)
        }
        return ResponseEntity(Unit,status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if(this.promocaoService.getById(id) != null){
            this.promocaoService.update(id,promocao)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit,status)
    }
    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "0") start: Int,
               @RequestParam(required = false, defaultValue = "3") size: Int,): ResponseEntity<Any>{
        val list = this.promocaoService.getAll(start,size)
        if(list.size == 0){
            return ResponseEntity(ErrorMessage("Promoções não localizadas","Não possui promoções cadastradas"),
                HttpStatus.NOT_FOUND)
        } else
            return ResponseEntity(list,HttpStatus.OK)
    }
    @GetMapping("/count")
    fun count(): ResponseEntity<Map<String,Long>> =
        ResponseEntity.ok().body(mapOf("count" to this.promocaoService.count()))

    @GetMapping("/ordenados")
    fun ordenados() = this.promocaoService.getAllSortedBylocal()

    @GetMapping("/byLocal/")
    fun getByLocal(@RequestParam(required = true) local:String): Any{
        val list = this.promocaoService.searchByLocal(local);
        if(list.size == 0){
            return ResponseEntity(ErrorMessage("Promoções não localizadas","Promocões com o nome ${local}, não foram localizadas"),
                HttpStatus.NOT_FOUND)
        } else
            return ResponseEntity(list,HttpStatus.OK)
    }


}