package com.acme.tuor.repository

import com.acme.tuor.model.Promocao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PromocaoRepository: PagingAndSortingRepository<Promocao,Long> {

    @Query(value = "select p from Promocao p where p.preco <= :preco")
    fun findByPrecoMenorQue(@Param(value = "preco") preco:Double): List<Promocao>

    @Query(value = "select p from Promocao p where p.local IN :local ")
    fun findByLocalInList(@Param(value = "local") local:String): List<Promocao>

    @Query(value = "UPDATE Promocao p set p.preco = :valor WHERE p.local = :local")
    @Transactional @Modifying 
    fun updateByLocal(@Param(value = "valor") preco: Double, @Param(value = "local") local: String)


}