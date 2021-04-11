package br.com.volks.rota2030.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.google.gson.Gson;

import br.com.volks.rota2030.component.ItemDeSegurancaComponent;
import br.com.volks.rota2030.dto.ItemDeSegurancaRequestDto;
import br.com.volks.rota2030.dto.ItemDeSegurancaResponseDto;
import br.com.volks.rota2030.enums.AcoesEnum;
import br.com.volks.rota2030.enums.TabelasEnum;
import br.com.volks.rota2030.exceptions.ItemDeSeguracaUpdateException;
import br.com.volks.rota2030.exceptions.ItemDeSegurancaNotDeletedException;
import br.com.volks.rota2030.exceptions.ItemDeSegurancaNotSalvedException;
import br.com.volks.rota2030.exceptions.ItemDeSegurancaSearchException;
import br.com.volks.rota2030.model.Grupo;
import br.com.volks.rota2030.model.ItemDeSeguranca;
import br.com.volks.rota2030.model.Logs;
import br.com.volks.rota2030.model.Tipo;
import br.com.volks.rota2030.repository.ItemDeSegurancaRepository;
import br.com.volks.rota2030.repository.LogsRepository;
import br.com.volks.rota2030.repository.RelatorioRepository;

@TestMethodOrder(OrderAnnotation.class)
@RunWith(MockitoJUnitRunner.class)
public class ItemDeSegurancaServiceTest {

	@InjectMocks
	private ItemDeSegurancaService itemDeSegurancaService;
	
	@Mock
	private ItemDeSegurancaComponent itemSegurancaComponent;
	
	@Mock
	private ItemDeSegurancaRepository itemSegurancarepository;
	
	@Mock
	private RelatorioRepository relatorioRepository;
	
	@Mock
	private LogsRepository logsRepository;
		
	
	
	private ItemDeSegurancaRequestDto mockaRequestDto() {
		return new ItemDeSegurancaRequestDto("item de seguranaca x", "norma x", "Grupo A" , "Automovel", false, "tester");
	}
	
	private ItemDeSegurancaResponseDto mockaResponseDto() {
		return new ItemDeSegurancaResponseDto(1L, "item de seguranca x", "norma x", "Grupo A ", "Automovel", false);
	}
	
	private Grupo mockaGrupo() {
		return new Grupo(1L, "Grupo A");
	}
	
	private Tipo mockaTipo() {
		return new Tipo(1L, "Automovel");
	}
	
	private ItemDeSeguranca mockaItemDeSeguranca() {
		return new ItemDeSeguranca(1L, "item de Seguranca x" , "norma x", false, mockaGrupo(), mockaTipo());
	}
	
	private Logs mockaLogs() {
		return  new Logs("tester", AcoesEnum.CRIAR, TabelasEnum.ITEM_DE_SEGURANCA, 1L, "FOO", new Date());

	}
	
	@Order(1)
	@Test
	public void deveSalvarUmItem() {
		Mockito.when(itemSegurancaComponent.toEntity(Mockito.<ItemDeSegurancaRequestDto>any())).thenReturn(mockaItemDeSeguranca());
		Mockito.when(itemSegurancarepository.save(Mockito.<ItemDeSeguranca>any())).thenReturn(mockaItemDeSeguranca());
		Mockito.when(logsRepository.save(Mockito.<Logs>any())).thenReturn(mockaLogs());
		
		ItemDeSegurancaResponseDto actual = itemDeSegurancaService.salvaItem(mockaRequestDto());
		assertEquals(1L, actual.getId());
	}
	
	@Order(2)
	@Test(expected = ItemDeSegurancaNotSalvedException.class)
	public void deveLancarExcecaoAoSalvarUmItem() {
		Mockito.when(itemSegurancaComponent.toEntity(Mockito.<ItemDeSegurancaRequestDto>any())).thenThrow(NullPointerException.class);
		itemDeSegurancaService.salvaItem(mockaRequestDto());
	}
	
	@Order(3)
	@Test
	public void deveFiltrarItem() {
		Map<String, String> filtro = new HashMap<String, String>();
		
		List<ItemDeSeguranca> resultado = Arrays.asList(mockaItemDeSeguranca());
		Pageable page = PageRequest.of(0, 1, Sort.by("id"));
		Page<ItemDeSeguranca> resultPaged = new PageImpl<ItemDeSeguranca>(resultado, page, resultado.size());
		
		Mockito.when(itemSegurancarepository.buscaPorFiltros(
				Mockito.anyMap(),
				Mockito.anyInt(),
				Mockito.anyInt(),
				Mockito.anyString()))
		.thenReturn(resultPaged);
		
		Page<ItemDeSegurancaResponseDto> actual = itemDeSegurancaService.buscaDinamica(filtro, 1, 1, "id");
		assertEquals(1, actual.getNumberOfElements());
	}
	
	@Order(4)
	@Test(expected = ItemDeSegurancaSearchException.class)
	public void deveLancarExcecaoAoFiltrarItem() {
		Map<String, String> filtro = new HashMap<String, String>();
		Mockito.when(itemSegurancarepository.buscaPorFiltros(
				Mockito.anyMap(),
				Mockito.anyInt(),
				Mockito.anyInt(),
				Mockito.anyString()))
		.thenThrow(NullPointerException.class);
		
		itemDeSegurancaService.buscaDinamica(filtro, 0, 1, "id");
	}
	
	@Order(5)
	@Test
	public void deveBuscarPorId() {
		Mockito.when(itemSegurancarepository.findByIdFullLoad(1L)).thenReturn(mockaItemDeSeguranca());
		ItemDeSegurancaResponseDto actual = itemDeSegurancaService.buscaPorId(1L);
		assertEquals("Automovel", actual.getTipo());
	}
	
	@Order(6)
	@Test(expected = ItemDeSegurancaSearchException.class)
	public void deveLancarExcecaoAoBuscarPorId() {
		Mockito.when(itemSegurancarepository.findByIdFullLoad(1L)).thenThrow(NullPointerException.class);
		itemDeSegurancaService.buscaPorId(1L);
	}
	
	@Order(7)
	@Test
	public void deveEditarUmItem() {
		Mockito.when(itemSegurancarepository.update(
				Mockito.anyLong(),
				Mockito.<String>any(),
				Mockito.<String>any() , 
				Mockito.anyBoolean() , 
				Mockito.<String>any(),
				Mockito.<String>any()))
		.thenReturn(1);
		
		ItemDeSegurancaResponseDto dto = mockaResponseDto();
		ItemDeSegurancaResponseDto actual = itemDeSegurancaService.editaItem(dto);
		
		assertEquals("Automovel", actual.getTipo());
	}
	
	@Order(8)
	@Test(expected = ItemDeSeguracaUpdateException.class)
	public void deveLancarExcecaoAoEditarUmItem() {
		Mockito.when(itemSegurancarepository.update(
				Mockito.anyLong(),
				Mockito.<String>any(),
				Mockito.<String>any() , 
				Mockito.anyBoolean() , 
				Mockito.<String>any(),
				Mockito.<String>any()))
		.thenThrow(NullPointerException.class);
		
		itemDeSegurancaService.editaItem(mockaResponseDto());
	}
	
	@Order(9)
	@Test
	public void deveDeletarUmItem() {
		Mockito.doNothing().when(itemSegurancarepository).deleteById(Mockito.anyLong());
		boolean actual = itemDeSegurancaService.deletaItem(1L, "tester");
		
		assertTrue(actual);
	}
	
	@Order(10)
	@Test(expected = ItemDeSegurancaNotDeletedException.class)
	public void deveLancarExcecaoAoDeletarUmItem() {
		Mockito.doThrow(new NullPointerException()).when(itemSegurancarepository).deleteById(Mockito.anyLong());
		itemDeSegurancaService.deletaItem(1L, "tester");
		
	}
	
}
