package com.acme.tuor.service

import com.acme.tuor.model.Promocao

interface PromocaoService {
    fun getById(id:Long): Promocao?
    fun create(promocao: Promocao)
    fun delete(id:Long)
    fun update(id:Long, promocao:Promocao)
    fun searchByLocal(local:String): List<Promocao>
    fun getAll(start: Int, size : Int):List<Promocao>
    fun count(): Long
    fun getAllSortedBylocal():List<Promocao>
    fun getAllByPrecoMenorQue():List<Promocao>

}