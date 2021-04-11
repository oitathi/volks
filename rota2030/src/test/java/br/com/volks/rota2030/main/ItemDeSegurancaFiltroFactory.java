package br.com.volks.rota2030.main;

import java.util.HashMap;
import java.util.Map;

public class ItemDeSegurancaFiltroFactory {
	
	private Map<String,String>filtro = new HashMap<>();
	private int pageNo = 0; 
    private int pageSize = 10;
    private String sortBy = "id";
	
	public ItemDeSegurancaFiltroFactory() {
		
		filtro.put("descricao", "Item X");
		filtro.put("norma", "Norma X");
		filtro.put("grupo", "Grupo A");
		filtro.put("tipo", "Automovel");
	}

}
