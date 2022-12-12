package com.acme.tuor.controller

import com.acme.tuor.model.Promocao
import com.acme.tuor.service.PromocaoService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*


@SpringBootTest
@AutoConfigureMockMvc
class TuorApplicationTests {

	@Autowired
	private val mockMvc: MockMvc? = null

	@Autowired
	private val objectMapper: ObjectMapper? = null

	@Autowired
	private val service: PromocaoService ? = null

	@Test
	fun contextLoads() {
	}
	@Test
	@Throws(Exception::class)
	fun case1() {
		val promocao = Promocao (
			1,
			"Viagem de Natal e Ano Novo",
			"Campos de Jordão",
			false,
			6,
			550.00)
	mockMvc?.perform(post("/promocoes")
		.contentType("application/json")
		.content(objectMapper!!.writeValueAsString(promocao)))
		?.andExpect(status().isCreated);

		val promocaoRetorn = service?.getById(id = promocao.id);
		Assertions.assertEquals(promocaoRetorn?.id,1);
		Assertions.assertEquals(promocaoRetorn?.descricao,"Viagem de Natal e Ano Novo");
		Assertions.assertEquals(promocaoRetorn?.local,"Campos de Jordão");
		Assertions.assertEquals(promocaoRetorn?.isAllInclusive, false);
		Assertions.assertEquals(promocaoRetorn?.qtdDias, 6);
		Assertions.assertEquals(promocaoRetorn?.preco, 550.00);


	}

}
