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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
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


/////////////////////////
	@Test
	fun deveRetornarOK_QuandoChamarGetAll(){
	service?.deleteAll()

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

		val promocaoReturn = (service?.getAll(0,5))
	Assertions.assertEquals(service?.getAll(0,5)?.size,promocaoReturn?.size)
	}

	@Test
	fun deveRetornarNotFound_QuandoChamarGetAll(){
		service?.deleteAll()
		mockMvc?.perform(get("/promocoes"))
			?.andExpect(status().isNotFound)
	}

////////////////////////
	@Test
	@Throws(Exception::class)
	fun deveRetornarCreated_QuandoChamarCreate() {
	service?.deleteAll()
		val promocao = Promocao (
			null,
			"Viagem de Natal e Ano Novo",
			"São Paulo",
			false,
			6,
			550.00)
		val resultado = mockMvc?.perform(post("/promocoes")
		.contentType("application/json")
		.content(objectMapper!!.writeValueAsString(promocao)))
		?.andExpect(status().isCreated)
		?.andReturn()?.response?.contentAsString
		val promocaoResultado = objectMapper?.readValue(resultado,Promocao::class.java)


		val promocaoReturn = service?.getById(id = promocaoResultado!!.id!!)
	Assertions.assertNotNull(promocaoReturn?.id)
		Assertions.assertEquals(promocao.descricao,promocaoReturn?.descricao)
		Assertions.assertEquals(promocao.local,promocaoReturn?.local)
		Assertions.assertEquals(promocao.isAllInclusive, promocaoReturn?.isAllInclusive)
		Assertions.assertEquals(promocao.qtdDias, promocaoReturn?.qtdDias)
		Assertions.assertEquals(promocao.preco, promocaoReturn?.preco)


	}

////////////////////
	@Test
	fun deveRetornarACCEPT_QuandoChamarDelete(){
	service?.deleteAll()
		val promocao1 =service?.create(Promocao(
			null,
			"Viagem 1",
			"São Paulo",
			false,
			6,
			1500.00))
		mockMvc?.perform(delete("/promocoes/${promocao1?.id}")
			.accept(MediaType.APPLICATION_JSON))
			?.andExpect(status().isAccepted)

	}

	@Test
	fun deveRetornarNotFound_QuandoChamarDelete(){
		service?.deleteAll()
		mockMvc?.perform(delete("/promocoes/1"))
			?.andExpect(status().isNotFound)
	}

//////////////////////////

	@Test
	fun deveRetornarOK_QuandoChamarGetAllMenores(){
		service?.deleteAll()
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

		val promocaoReturn = service?.getAllByPrecoMenorQue(2000.00)

		Assertions.assertEquals(service?.getAllByPrecoMenorQue(2000.00)?.size,promocaoReturn?.size)

	}

	@Test
	fun deveRetornarNotFound_QuandoChamarGetAllMenores(){
		service?.deleteAll()
		mockMvc?.perform(get("/promocoes/menorQue/?preco=2000"))
			?.andExpect(status().isNotFound)
	}

/////////////////////////////////////
	@Test
	fun deveRetornarOK_QuandoChamarGetById(){
	service?.deleteAll()
		val promocaoGetId =service?.create(Promocao(
			1,
			"Viagem Get Id",
			"São Paulo",
			false,
			6,
			1500.00))

		 mockMvc?.perform(get("/promocoes/1")
			.accept(MediaType.APPLICATION_JSON))
			?.andExpect(status().isOk)

		val promocaoReturn = service?.getById(1)
		Assertions.assertNotNull(promocaoReturn?.id)
		Assertions.assertEquals(promocaoGetId?.descricao,promocaoReturn?.descricao)
		Assertions.assertEquals(promocaoGetId?.local,promocaoReturn?.local)
		Assertions.assertEquals(promocaoGetId?.isAllInclusive, promocaoReturn?.isAllInclusive)
		Assertions.assertEquals(promocaoGetId?.qtdDias, promocaoReturn?.qtdDias)
		Assertions.assertEquals(promocaoGetId?.preco, promocaoReturn?.preco)


	}

	@Test
	fun deveRetornarNotFound_QuandoChamarGetById() {
		service?.deleteAll()
		mockMvc?.perform(get("/promocoes/1"))
			?.andExpect(status().isNotFound)
	}
//////////////////////////

	@Test
	fun deveRetornarACCEPT_QuandoChamarUpdate(){
		service?.deleteAll()
		val promocao1 =service?.create(Promocao(
			null,
			"Viagem 1",
			"São Paulo",
			false,
			6,
			1500.00))

		val promocaoUpdate = Promocao (
			promocao1?.id,
			"Viagem 1 Alterada",
			"São Caetano",
			false,
			6,
			550.00)

			mockMvc?.perform(put("/promocoes/${promocao1!!.id}")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(objectMapper!!.writeValueAsString(promocaoUpdate)))
			?.andExpect(status().isAccepted)


		val promocaoReturn = service?.getById(promocaoUpdate.id!!)
		Assertions.assertNotNull(promocaoReturn?.id)
		Assertions.assertEquals(promocaoUpdate.descricao,promocaoReturn?.descricao)
		Assertions.assertEquals(promocaoUpdate.local,promocaoReturn?.local)
		Assertions.assertEquals(promocaoUpdate.isAllInclusive, promocaoReturn?.isAllInclusive)
		Assertions.assertEquals(promocaoUpdate.qtdDias, promocaoReturn?.qtdDias)
		Assertions.assertEquals(promocaoUpdate.preco, promocaoReturn?.preco)



	}


	@Test
	fun deveRetornarNotFound_QuandoChamarUpdate(){
		service?.deleteAll()
		val update = Promocao (
			1,
			"Viagem 1 Alterada",
			"São Caetano",
			false,
			6,
			550.00)

		mockMvc?.perform(put("/promocoes/1")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(objectMapper!!.writeValueAsString(update)))
			?.andExpect(status().isNotFound)
	}


////////////////////////

	@Test
	fun deveRetonarOK_QuandoChamarCount(){
		service?.deleteAll()
		service?.create(
			Promocao(
			null,
			"Viagem1",
			"São Paulo",
			true,
			3,
			200.00
		))
		mockMvc?.perform(get("/promocoes/count")
			.accept(MediaType.APPLICATION_JSON))
			?.andExpect(status().isOk)

		val totalCount = service?.count()
		Assertions.assertEquals(service?.count(),totalCount)
		Assertions.assertEquals(1,totalCount)
	}

////////////////////

	@Test
	fun deveRetornarOK_QuandoChamarOrdenados(){
		service?.deleteAll()
		service?.create(
			Promocao(
				null,
				"Viagem1",
				"B",
				true,
				3,
				200.00
			))
		service?.create(
			Promocao(
				null,
				"Viagem1",
				"A",
				true,
				3,
				200.00
			))
			mockMvc?.perform(get("/promocoes/ordenados")
			.accept(MediaType.APPLICATION_JSON))
			?.andExpect(status().isOk)



		val promocaoRetorn = service?.getAllSortedBylocal()
		Assertions.assertEquals("A",promocaoRetorn?.get(0)?.local)

	}

	@Test
	fun deveRetornarNotFound_QuandoChamarOrdenados(){
		service?.deleteAll()
		mockMvc?.perform(get("/promocoes/ordenados"))
			?.andExpect(status().isNotFound)
	}

//////////////////////////
}
