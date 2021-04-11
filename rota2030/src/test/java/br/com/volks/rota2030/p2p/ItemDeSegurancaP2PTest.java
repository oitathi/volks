package br.com.volks.rota2030.p2p;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import br.com.volks.rota2030.controller.ItemDeSeguracaController;
import br.com.volks.rota2030.dto.ItemDeSegurancaRequestDto;
import br.com.volks.rota2030.dto.ItemDeSegurancaResponseDto;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = ("spring.h2.console.enabled=true"))
public class ItemDeSegurancaP2PTest {
	
	@Autowired
	private ItemDeSeguracaController controller;
	
	private Map<String, String> filtro;
	
	
	
	@Test
	@Order(2)
	public void deveRetornarListaDeItensSemFiltro() {
		filtro = new HashMap<>();
		Page<ItemDeSegurancaResponseDto> actual = controller.listaTodos(filtro, 0, 10, "id");
		
		assertEquals(4, actual.getTotalElements());
	}
	
	@Test
	@Order(3)
	public void deveRetornarListaDeItensFiltradoPorDescricao() {
		filtro = new HashMap<>();
		filtro.put("descricao", "Item X");
		Page<ItemDeSegurancaResponseDto> actual = controller.listaTodos(filtro, 0, 10, "id");
		
		assertEquals(1, actual.getTotalElements());
	}
	
	@Test
	@Order(4)
	public void deveRetornarListaDeItensFiltradosPorNorma() {
		filtro = new HashMap<>();
		filtro.put("norma", "Norma X");
		Page<ItemDeSegurancaResponseDto> actual = controller.listaTodos(filtro, 0, 10, "id");
		
		assertEquals(1, actual.getTotalElements());
	}
	
	@Test
	@Order(5)
	public void deveRetornarListaDeItensFiltradosPorGrupo() {
		filtro = new HashMap<>();
		filtro.put("grupo", "Grupo C");
		Page<ItemDeSegurancaResponseDto> actual = controller.listaTodos(filtro, 0, 10, "id");
		
		assertEquals(1, actual.getTotalElements());
	}
	
	@Test
	@Order(6)
	public void deveRetornarListaDeItensFiltradosPorTipo() {
		filtro = new HashMap<>();
		filtro.put("tipo", "Utilitario");
		Page<ItemDeSegurancaResponseDto> actual = controller.listaTodos(filtro, 0, 10, "id");
		
		assertEquals(1, actual.getTotalElements());
	}
	
	@Test
	@Order(7)
	public void deveRetornarListaDeItensFiltradosPorDescricaoNormaGrupoTipo() {
		filtro = new HashMap<>();
		filtro.put("descricao", "Item X");
		filtro.put("norma", "Norma X");
		filtro.put("grupo", "Grupo A");
		filtro.put("tipo", "Automovel");
		Page<ItemDeSegurancaResponseDto> actual = controller.listaTodos(filtro, 0, 10, "id");
		
		assertEquals(1, actual.getTotalElements());
	}
	
	@Test
	@Order(8)
	public void deveIgnorarFiltroVazio() {
		filtro = new HashMap<>();
		filtro.put("descricao", "");
		filtro.put("norma", null);
		filtro.put("grupo", "Grupo A");
		filtro.put("tipo", "Automovel");
		Page<ItemDeSegurancaResponseDto> actual = controller.listaTodos(filtro, 0, 10, "id");
		
		assertEquals(2, actual.getTotalElements());
	}
	
	@Test
	@Order(9)
	public void deveSalvarUmNovoItem() {
		ItemDeSegurancaRequestDto requestDto = new ItemDeSegurancaRequestDto("item teste", "norma teste", "Grupo A", "Automovel", false, "tester");
		controller.novoItemDeSeguranca(requestDto);
		
		filtro = new HashMap<>();
		filtro.put("descricao", "item teste");
		
		Page<ItemDeSegurancaResponseDto> actual = controller.listaTodos(filtro, 0, 10, "id");
		assertEquals(1, actual.getTotalElements());
	}
	
	@Test
	@Order(10)
	public void deveEditarUmItem() {
		ItemDeSegurancaResponseDto dtoParaEditar = new ItemDeSegurancaResponseDto(1L, "Item X", "Norma Alfa", "Grupo C", "Caminhonete",  false, "tester");
		ItemDeSegurancaResponseDto dtoEditado = controller.editaItem(dtoParaEditar);
		
		
		dtoEditado = controller.buscaPorId(1L);
		
		assertEquals(1L, dtoEditado.getId());
		assertEquals("Item X", dtoEditado.getDescricao());
		assertEquals("Norma Alfa", dtoEditado.getNorma());
		assertEquals("Grupo C", dtoEditado.getGrupo());
		assertEquals("Caminhonete", dtoEditado.getTipo());
		assertEquals(false, dtoEditado.isObrigatorio());
	} 
	
	@Test
	@Order(11)
	public void deveDeletarUmItem() {
		boolean actual = controller.deletaItem(1L, "tester");
		assertEquals(true, actual);
	}

}
