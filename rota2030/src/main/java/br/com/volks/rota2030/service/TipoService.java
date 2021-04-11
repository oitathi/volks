package br.com.volks.rota2030.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.volks.rota2030.exceptions.TipoNotFoundException;
import br.com.volks.rota2030.model.Tipo;
import br.com.volks.rota2030.repository.TipoRepository;

@Service 
public class TipoService {
	
	@Autowired
	private TipoRepository repository;
	
	public List<Tipo> listarTodos(){
		return repository.findAll();
	}
	
	public Tipo buscaPorDescricao(String descricao) {
		List<Tipo> tipos = repository.findByDescricao(descricao);
		if(!tipos.isEmpty()) {
			return tipos.get(0);
		}
		throw new TipoNotFoundException(descricao);
	}

}
