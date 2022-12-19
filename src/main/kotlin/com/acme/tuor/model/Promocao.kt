package com.acme.tuor.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Promocao(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @field:NotBlank(message = "Escrever a descrição é obrigatório")
    val descricao: String? = null,
    @field:NotBlank(message = "Escrever o local é obrigatório")
    val local: String? = null,
    val isAllInclusive: Boolean? = false,
    @field:NotNull(message = "Escrever a quantidade de dias é obrigatório")
    val qtdDias: Int? = null,
    @field:NotNull(message = "Escrever o preço é obrigatório")
    val preco: Double? = null
)