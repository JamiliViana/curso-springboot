package com.acme.tuor.service.impl

import com.acme.tuor.model.Promocao
import com.acme.tuor.repository.PromocaoRepository
import com.acme.tuor.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import javax.persistence.Cacheable

@Component
class PromocaoServiceImpl(val promocaoRepository: PromocaoRepository): PromocaoService {

    override fun getById(id: Long): Promocao? {
        return promocaoRepository.findById(id).orElseGet(null)
    }
//    @CacheEvict("promocoes", allEntries = true)
    override fun create(promocao: Promocao) {
        this.promocaoRepository.save(promocao)
    }
//    @CacheEvict("promocoes", allEntries = true)
    override fun delete(id: Long) {
        promocaoRepository.deleteById(id)
    }
//    @CacheEvict("promocoes", allEntries = true)
    override fun update(id: Long, promocao: Promocao) {
        create(promocao)
    }

    override fun searchByLocal(local: String): List<Promocao> =
        listOf()

//    @org.springframework.cache.annotation.Cacheable("promocoes")
    override fun getAll(start: Int, size: Int): List<Promocao> {
        var pages:Pageable = PageRequest.of(start,size,Sort.by("local").ascending())
        return this.promocaoRepository.findAll(pages).toList()
    }

    override fun count(): Long =
        this.promocaoRepository.count()

    override fun getAllSortedBylocal(): List<Promocao> =
        this.promocaoRepository.findAll(Sort.by("local").descending()).toList()

    override fun getAllByPrecoMenorQue(): List<Promocao> {
        return this.promocaoRepository.findByPrecoMenorQue(9000.0,6)
    }

}
