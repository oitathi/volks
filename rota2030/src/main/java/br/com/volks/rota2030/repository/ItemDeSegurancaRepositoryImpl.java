package br.com.volks.rota2030.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import br.com.volks.rota2030.model.ItemDeSeguranca;

@Repository
public class ItemDeSegurancaRepositoryImpl implements ItemDeSegurancaRepositoryCustom{
	
	
	@PersistenceContext
    private EntityManager em;
	
    
	@Override
	public Page<ItemDeSeguranca> buscaPorFiltros(Map<String, String> filtro, int pageNo, int pageSize, String sortBy) {
		EntityGraph entityGraph = em.getEntityGraph("item-com-grupo-e-tipo");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<ItemDeSeguranca> cq = cb.createQuery(ItemDeSeguranca.class);
	    
	    
	    Root<ItemDeSeguranca> itemSeg = cq.from(ItemDeSeguranca.class);
	    Predicate predicate = cb.conjunction();
	   
	    for (Map.Entry<String, String> entry : filtro.entrySet()) {
	    	if(StringUtils.isNotBlank(entry.getValue())) {
		    	switch (entry.getKey()) {
					
			    	case "descricao":
			    		predicate = cb.and(cb.equal(itemSeg.get("descricao"), entry.getValue()));
						break;
			    	
			    	case "norma":
			    		predicate = cb.and(cb.equal(itemSeg.get("norma"), entry.getValue()));
			    		break;
			    	
			    	case "grupo":
			    		predicate = cb.and(cb.equal(itemSeg.get("grupo").get("descricao"), entry.getValue()));
			    		break; 
			    	
			    	case "tipo":
			    		predicate = cb.and(cb.equal(itemSeg.get("tipo").get("descricao"), entry.getValue()));
			    		break; 
		    	}
	   		}
	    }
	    TypedQuery<ItemDeSeguranca> tp = em
	    			.createQuery(cq.where(predicate)
	    			.orderBy(cb.asc(itemSeg.get(sortBy))))
	    		    .setHint("javax.persistence.loadgraph", entityGraph); 
	    
	    
	    tp.setFirstResult(pageNo * pageNo);
	    tp.setMaxResults(pageSize);
	    
	    List<ItemDeSeguranca> resultado = tp.getResultList();
	   	   	    
	    Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	    Page<ItemDeSeguranca> resultPaged = new PageImpl<ItemDeSeguranca>(resultado, page, resultado.size());

	    return resultPaged;
	}


	@Override
	public ItemDeSeguranca findByIdFullLoad(long id) {
		EntityGraph entityGraph = em.getEntityGraph("item-com-grupo-e-tipo");
	    Map<String, Object> properties = new HashMap<>();
	    properties.put("javax.persistence.fetchgraph", entityGraph);
	    ItemDeSeguranca iseg = em.find(ItemDeSeguranca.class, id, properties);
	    return iseg;
	}




}
