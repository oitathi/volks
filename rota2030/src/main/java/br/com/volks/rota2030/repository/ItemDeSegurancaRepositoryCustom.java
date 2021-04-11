package br.com.volks.rota2030.repository;

import java.util.Map;

import org.springframework.data.domain.Page;

import br.com.volks.rota2030.model.ItemDeSeguranca;

public interface ItemDeSegurancaRepositoryCustom {
	
	Page<ItemDeSeguranca> buscaPorFiltros(Map<String, String> filtro, int pageNo, int pageSize, String sortBy);
	
	ItemDeSeguranca findByIdFullLoad(long id);

}
