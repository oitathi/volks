package br.com.volks.rota2030.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.volks.rota2030.dto.ItemDeSegurancaRequestDto;
import br.com.volks.rota2030.dto.ItemDeSegurancaResponseDto;
import br.com.volks.rota2030.main.ItemDeSegurancaJsonFactory;
import br.com.volks.rota2030.service.ItemDeSegurancaService;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class ItemDeSegurancaControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private ItemDeSeguracaController controller;

	@Mock
	private ItemDeSegurancaService service;

	private Page<ItemDeSegurancaResponseDto> mockaPageResponse() {
		List<ItemDeSegurancaResponseDto> listResult = Arrays.asList(mockaResponseDto());

		Pageable page = PageRequest.of(0, 1, Sort.by("id"));
		Page<ItemDeSegurancaResponseDto> resultPaged = new PageImpl<ItemDeSegurancaResponseDto>(listResult, page,listResult.size());

		return resultPaged;
	}
	
	private ItemDeSegurancaResponseDto mockaResponseDto() {
		return new ItemDeSegurancaResponseDto(1L, "descricao", "norma", "grupo", "tipo", true);
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void deveRealizarBuscaDinamica() throws Exception {
		Mockito.when(service.buscaDinamica(Mockito.anyMap(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).thenReturn(mockaPageResponse());
		
		mockMvc.perform(get("/item-de-seguranca/lista-itens")
				.param("filtro", ItemDeSegurancaJsonFactory.mockaJsonMap())
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	@Test
	public void deveSalvarUmNovoItem() throws Exception {
		Mockito.when(service.salvaItem(Mockito.<ItemDeSegurancaRequestDto>any())).thenReturn(mockaResponseDto());
		
		mockMvc.perform(post("/item-de-seguranca/novo-item")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ItemDeSegurancaJsonFactory.mockaJsonRequestDto())
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
	}
	
	@Test
	public void deveEditarUmItem() throws Exception {
		Mockito.when(service.editaItem(Mockito.<ItemDeSegurancaResponseDto>any())).thenReturn(mockaResponseDto());
		
		mockMvc.perform(put("/item-de-seguranca/edita-item/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ItemDeSegurancaJsonFactory.mockaJsonRequestDto())
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

	}
	
	@Test
	public void deveBuscarPorId() throws Exception {
		Mockito.when(service.buscaPorId(Mockito.anyLong())).thenReturn(mockaResponseDto());
		
		mockMvc.perform(get("/item-de-seguranca/lista-itens/1")
				.param("id", "1")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	@Test
	public void deveDeletarUmItem() throws Exception {
		Mockito.when(service.deletaItem(Mockito.anyLong(), Mockito.anyString())).thenReturn(true);
		
		mockMvc.perform(delete("/item-de-seguranca/deleta-item/{id}/{user}", "1","tester")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}
	
	@Test
	public void deveCriarRelatorio() throws Exception {
		Mockito.when(service.criaTokenDeAcompanhamento(Mockito.anyList())).thenReturn(1l);
		
		mockMvc.perform(post("/item-de-seguranca/download-relatorio")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ItemDeSegurancaJsonFactory.mockaListaJsonResponseDto())
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
	}



}
