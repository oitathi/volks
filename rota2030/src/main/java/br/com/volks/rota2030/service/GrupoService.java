package br.com.volks.rota2030.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.volks.rota2030.exceptions.GrupoNotFoundException;
import br.com.volks.rota2030.model.Grupo;
import br.com.volks.rota2030.repository.GrupoRepository;

@Service 
public class GrupoService {
	
	@Autowired
	private GrupoRepository repository;
	
	public List<Grupo> listarTodos(){
		return repository.findAll();
	}
	
	public Grupo buscaPorDescricao(String descricao) {
		List<Grupo> grupos = repository.findByDescricao(descricao);
		if(!grupos.isEmpty()) {
			return grupos.get(0);
		}
		throw new GrupoNotFoundException(descricao);
	}

}
