package br.com.volks.rota2030.component;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.volks.rota2030.dto.ItemDeSegurancaRequestDto;
import br.com.volks.rota2030.dto.ItemDeSegurancaResponseDto;
import br.com.volks.rota2030.model.Grupo;
import br.com.volks.rota2030.model.ItemDeSeguranca;
import br.com.volks.rota2030.model.Tipo;
import br.com.volks.rota2030.service.GrupoService;
import br.com.volks.rota2030.service.TipoService;

@TestMethodOrder(OrderAnnotation.class)
@RunWith(MockitoJUnitRunner.class)
public class ItemDeSegurancaComponentTest {
	
	@InjectMocks
	private ItemDeSegurancaComponent component;
	
	@Mock
	private GrupoService grupoService;
	
	@Mock
	private TipoService tipoService;
	
	
	private ItemDeSegurancaRequestDto mockaRequestDto() {
		return new ItemDeSegurancaRequestDto("item de seguranca x", "norma x", "Grupo A" , "Automovel", false, "tester");
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
	
	@Test
	public void deveConverterResquestDtoParaEntity() {
		Mockito.when(grupoService.buscaPorDescricao(Mockito.<String>any())).thenReturn(mockaGrupo());
		Mockito.when(tipoService.buscaPorDescricao(Mockito.<String>any())).thenReturn(mockaTipo());
		
		ItemDeSeguranca actual = component.toEntity(mockaRequestDto());
		
		assertEquals("item de seguranca x", actual.getDescricao());
		
	}
	
	@Test
	public void deveConverterResponseDtoToEntity() {
		Mockito.when(grupoService.buscaPorDescricao(Mockito.<String>any())).thenReturn(mockaGrupo());
		Mockito.when(tipoService.buscaPorDescricao(Mockito.<String>any())).thenReturn(mockaTipo());
		
		ItemDeSeguranca actual = component.toEntity(mockaResponseDto());
		
		assertEquals("item de seguranca x", actual.getDescricao());
	}
	
	
}
