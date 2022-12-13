package com.acme.tuor.model

import javax.persistence.*

@Entity
data class Promocao(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val descricao: String? = null,
    val local: String? = null,
    val isAllInclusive: Boolean? = false,
    val qtdDias: Int? = null,
    val preco: Double? = null
)