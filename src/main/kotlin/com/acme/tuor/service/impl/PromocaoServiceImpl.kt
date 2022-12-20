package com.acme.tuor.service.impl

import com.acme.tuor.exception.PromocaoNotFoundException
import com.acme.tuor.model.Promocao
import com.acme.tuor.repository.PromocaoRepository
import com.acme.tuor.service.PromocaoService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class PromocaoServiceImpl(val promocaoRepository: PromocaoRepository): PromocaoService {

    override fun getById(id: Long): Promocao? {
        var promocao = promocaoRepository.findById(id).orElse(null)
        if (promocao != null){
            return promocao
        }else throw PromocaoNotFoundException()
    }

    override fun create(promocao: Promocao) : Promocao{
        return this.promocaoRepository.save(promocao)
    }

    override fun delete(id: Long){
        if (this.getById(id) != null) {
            promocaoRepository.deleteById(id)
        }else throw PromocaoNotFoundException()
    }

    override fun update(id: Long, promocao: Promocao): Promocao? {
        if (this.getById(id) != null) {
            create(promocao)
            return this.getById(id)
        }else throw PromocaoNotFoundException()
    }

    override fun searchByLocal(local: String): List<Promocao> {
        val list = this.promocaoRepository.findByLocalInList(local)
        if (list.isEmpty()) {
            throw PromocaoNotFoundException()
        } else return list
    }


    override fun getAll(start: Int, size: Int): List<Promocao> {
        var pages:Pageable = PageRequest.of(start,size,Sort.by("local").ascending())
        val list =this.promocaoRepository.findAll(pages).toList()
        if (list.isEmpty()) {
            throw PromocaoNotFoundException()
        } else return list
    }

    override fun count(): Long =
        this.promocaoRepository.count()

    override fun getAllSortedBylocal(): List<Promocao> {
        val list = this.promocaoRepository.findAll(Sort.by("local").ascending()).toList()
        if (list.isEmpty()) {
            throw PromocaoNotFoundException()
        } else return list
    }

    override fun getAllByPrecoMenorQue(preco:Double): List<Promocao> {
        val list = this.promocaoRepository.findByPrecoMenorQue(preco)
        if (list.isEmpty()) {
            throw PromocaoNotFoundException()
        }else return list
    }

    override fun deleteAll() {
        promocaoRepository.deleteAll()
    }

}