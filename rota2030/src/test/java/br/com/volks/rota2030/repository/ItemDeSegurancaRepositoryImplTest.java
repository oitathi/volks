package br.com.volks.rota2030.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;

import br.com.volks.rota2030.model.Grupo;
import br.com.volks.rota2030.model.ItemDeSeguranca;
import br.com.volks.rota2030.model.Tipo;

@TestMethodOrder(OrderAnnotation.class)
@RunWith(MockitoJUnitRunner.class)
public class ItemDeSegurancaRepositoryImplTest {
	
	@InjectMocks
	private ItemDeSegurancaRepositoryImpl repositoryImpl;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private EntityGraph entityGraph;
	
	@Mock
	private CriteriaBuilder cb;
	
	@Mock
	private CriteriaQuery<ItemDeSeguranca> cq;
	
	@Mock
	private TypedQuery<ItemDeSeguranca> tp;
	
	@Mock
	private Root<ItemDeSeguranca> itemSeg;
	
	@Mock
	private Predicate predicate;
	
	@Mock
	private javax.persistence.criteria.Order order;
	
	@Mock
	private Path path;
	
	@Mock
	private Map<String, Object> properties;
	
	private ItemDeSeguranca mockaItemDeSeguranca() {
		return new ItemDeSeguranca(1L, "item de Seguranca x" , "norma x", false, mockaGrupo(), mockaTipo());
	}
	
	private Grupo mockaGrupo() {
		return new Grupo(1L, "Grupo A");
	}
	
	private Tipo mockaTipo() {
		return new Tipo(1L, "Automovel");
	}
	
	@Order(2)
	@Test
	public void deveBuscarSemFiltros() {
		
		Mockito.when(em.getEntityGraph(Mockito.anyString())).thenReturn(entityGraph);
		Mockito.when(em.getCriteriaBuilder()).thenReturn(cb);
		Mockito.when(cb.createQuery(Mockito.eq(ItemDeSeguranca.class))).thenReturn(cq);
		Mockito.when(cq.from(Mockito.eq(ItemDeSeguranca.class))).thenReturn(itemSeg);
		Mockito.when(cb.conjunction()).thenReturn(predicate);
		Mockito.when(em.createQuery(cq)).thenReturn(tp);
		Mockito.when(cq.where(predicate)).thenReturn(cq);
		Mockito.when(itemSeg.get((Mockito.anyString()))).thenReturn(path);
		Mockito.when(cb.asc(path)).thenReturn(order);
		Mockito.when(cq.orderBy(order)).thenReturn(cq);
		Mockito.when(tp.setHint(Mockito.anyString(), Mockito.<EntityGraph>any())).thenReturn(tp);
		Mockito.when(tp.getResultList()).thenReturn(Arrays.asList(mockaItemDeSeguranca()));
		
		Map<String,String> filtro = new HashMap<String, String>();
		filtro.put("descricao", null);
		
		Page<ItemDeSeguranca> actual = repositoryImpl.buscaPorFiltros(filtro, 0, 1, "id");
		verify(cb, times(0)).and(predicate);
	}
	
	@Order(3)
	@Test
	public void deveBuscarPorDescricao() {
		
		Mockito.when(em.getEntityGraph(Mockito.anyString())).thenReturn(entityGraph);
		Mockito.when(em.getCriteriaBuilder()).thenReturn(cb);
		Mockito.when(cb.createQuery(Mockito.eq(ItemDeSeguranca.class))).thenReturn(cq);
		Mockito.when(cq.from(Mockito.eq(ItemDeSeguranca.class))).thenReturn(itemSeg);
		Mockito.when(cb.conjunction()).thenReturn(predicate);
		
		Mockito.when(itemSeg.get(Mockito.anyString())).thenReturn(path);
			
		
		Mockito.when(cb.and(cb.equal(itemSeg.get("descricao"), "item x"))).thenReturn(predicate);
		Mockito.when(cb.and(cb.equal(itemSeg.get("norma"), "norma x"))).thenReturn(predicate);
		Mockito.when(cb.and(cb.equal(itemSeg.get("grupo").get("descricao"), "Grupo A"))).thenReturn(predicate);
		Mockito.when(cb.and(cb.equal(itemSeg.get("tipo").get("descricao"), "Automovel"))).thenReturn(predicate);
		
		
		Mockito.when(cq.where(predicate)).thenReturn(cq);
		Mockito.when(em.createQuery(cq)).thenReturn(tp); 
		Mockito.when(cb.asc(path)).thenReturn(order);
		Mockito.when(cq.orderBy(order)).thenReturn(cq);
		Mockito.when(tp.setHint(Mockito.anyString(), Mockito.<EntityGraph>any())).thenReturn(tp);
		Mockito.when(tp.getResultList()).thenReturn(Arrays.asList(mockaItemDeSeguranca()));
		
		Map<String,String> filtro = new HashMap<String, String>();
		filtro.put("descricao", "item x");
		filtro.put("norma", "norma x");
		filtro.put("grupo", "Grupo A");
		filtro.put("tipo", "Automovel");
		
		Page<ItemDeSeguranca> actual = repositoryImpl.buscaPorFiltros(filtro, 0, 1, "id");
		verify(cb, times(2)).equal(itemSeg.get("descricao"), "item x");
		verify(cb, times(2)).equal(itemSeg.get("norma"), "norma x");
		verify(cb, times(2)).equal(itemSeg.get("grupo").get("descricao"), "Grupo A");
		verify(cb, times(2)).equal(itemSeg.get("tipo").get("descricao"), "Automovel");
	}
	
	@Order(1)
	@Test
	public void deveBuscarPorId() {
		Mockito.when(em.getEntityGraph(Mockito.anyString())).thenReturn(entityGraph);
		
		Map<String, Object> map = new HashMap<>();
	    map.put("javax.persistence.fetchgraph", entityGraph);
		
		repositoryImpl.findByIdFullLoad(1L);
		verify(em, times(1)).find(ItemDeSeguranca.class, 1L, map);
	}

}
