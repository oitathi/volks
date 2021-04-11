package br.com.volks.rota2030.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.volks.rota2030.exceptions.GrupoNotFoundException;
import br.com.volks.rota2030.model.Grupo;
import br.com.volks.rota2030.repository.GrupoRepository;

@TestMethodOrder(OrderAnnotation.class)
@RunWith(MockitoJUnitRunner.class)
public class GrupoServiceTest {
	
	@InjectMocks
	private GrupoService service;
	
	@Mock
	private GrupoRepository repository;
	
	private Grupo mockaGrupo() {
		return new Grupo(1L, "Grupo A");
	}
	
	@Order(1)
	@Test
	public void deveListarTodosTipos() {
		Mockito.when(repository.findAll()).thenReturn(Arrays.asList(mockaGrupo()));
		
		List<Grupo> actual = service.listarTodos();
		assertEquals(1, actual.size());
	}
	
	@Order(2)
	@Test
	public void deveBuscarPorDescricao() {
		Mockito.when(repository.findByDescricao(Mockito.anyString())).thenReturn(Arrays.asList(mockaGrupo()));
		
		Grupo actual = service.buscaPorDescricao("Grupo A");
		assertEquals("Grupo A", actual.getDescricao());
	}
	
	@Order(3)
	@Test(expected = GrupoNotFoundException.class)
	public void deveLancarExcecaoAoBuscarPorDescricaoNaoExistente() {
		Mockito.when(repository.findByDescricao(Mockito.anyString())).thenReturn(new ArrayList<>());
		
		service.buscaPorDescricao("Grupo Z");
	}

}
