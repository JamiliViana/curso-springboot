package com.acme.tuor.controller

import com.acme.tuor.model.Promocao
import com.acme.tuor.service.PromocaoService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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
	fun deveRetornarOK_QuandoChamarGetAll(){

		service?.create(Promocao(
				null,
				"Viagem de Natal e Ano Novo",
				"São Paulo",
				false,
				6,
				550.00))
		service?.create(Promocao(
			null,
			"Viagem Carnaval",
			"Santos",
			false,
			6,
			1550.00))
		mockMvc?.perform(get("/promocoes")
			.accept(MediaType.APPLICATION_JSON))
			?.andExpect(status().isOk)

		val promocaoReturn = (service?.getAll(0,5));
		Assertions.assertEquals(service?.getAll(0,5)?.size,promocaoReturn?.size);
	}
	@Test
	@Throws(Exception::class)
	fun deveRetornarCreated_QuandoCriarPromocao() {
		val promocao = Promocao (
			null,
			"Viagem de Natal e Ano Novo",
			"São Paulo",
			false,
			6,
			550.00)
	var resultado = mockMvc?.perform(post("/promocoes")
		.contentType("application/json")
		.content(objectMapper!!.writeValueAsString(promocao)))
		?.andExpect(status().isCreated)
		?.andReturn()?.response?.contentAsString
		var promocaoResultado = objectMapper?.readValue(resultado,Promocao::class.java)


		val promocaoReturn = service?.getById(id = promocaoResultado!!.id!!);
		Assertions.assertNotNull(promocaoReturn?.id);
		Assertions.assertEquals(promocao.descricao,promocaoReturn?.descricao);
		Assertions.assertEquals(promocao.local,promocaoReturn?.local);
		Assertions.assertEquals(promocao.isAllInclusive, promocaoReturn?.isAllInclusive);
		Assertions.assertEquals(promocao.qtdDias, promocaoReturn?.qtdDias);
		Assertions.assertEquals(promocao.preco, promocaoReturn?.preco);


	}

	@Test
	fun deveRetornarOK_QuandoChamarGetAllMenores(){
		service?.create(Promocao(
			null,
			"Viagem 1",
			"São Paulo",
			false,
			6,
			1500.00))
		service?.create(Promocao(
			null,
			"Viagem 2",
			"Santos",
			false,
			6,
			2000.00))
		service?.create(Promocao(
			null,
			"Viagem 3",
			"Santos",
			false,
			6,
			1000.00))

		mockMvc?.perform(get("/promocoes/menorQue/?preco=2000")
			.accept(MediaType.APPLICATION_JSON))
			?.andExpect(status().isOk)

		var promocaoReturn = service?.getAllByPrecoMenorQue(2000.00)

		Assertions.assertEquals(service?.getAllByPrecoMenorQue(2000.00)?.size,promocaoReturn?.size)

	}


}
