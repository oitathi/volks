package br.com.volks.rota2030.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.volks.rota2030.dto.ItemDeSegurancaRequestDto;
import br.com.volks.rota2030.dto.ItemDeSegurancaResponseDto;
import br.com.volks.rota2030.service.ItemDeSegurancaService;

@RestController 
@RequestMapping(path = "item-de-seguranca")
public class ItemDeSeguracaController { 
	
	@Autowired
	private ItemDeSegurancaService service;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/lista-itens", produces = "application/json")
	public Page<ItemDeSegurancaResponseDto> listaTodos(
			 @RequestParam (required = false) Map<String,String> filtro,
			 @RequestParam(defaultValue = "0") int pageNo, 
             @RequestParam(defaultValue = "10") int pageSize,
             @RequestParam(defaultValue = "id") String sortBy) {
		
		return service.buscaDinamica(filtro, pageNo, pageSize, sortBy);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/novo-item", consumes = "application/json", produces = "application/json")
	public ItemDeSegurancaResponseDto novoItemDeSeguranca(@RequestBody ItemDeSegurancaRequestDto novoItem) {
		return service.salvaItem(novoItem);
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PutMapping(path ="/edita-item/{id}",consumes = "application/json", produces = "application/json")
	public ItemDeSegurancaResponseDto editaItem(@RequestBody ItemDeSegurancaResponseDto itemEditado)  {
		return service.editaItem(itemEditado);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/lista-itens/{id}", produces = "application/json")
	public ItemDeSegurancaResponseDto buscaPorId(@PathVariable Long id) {
		return service.buscaPorId(id);
		
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@DeleteMapping(path = "/deleta-item/{id}/{user}",produces = "application/json")
	public boolean deletaItem(@PathVariable Long id, @PathVariable String user) {
		return service.deletaItem(id, user);
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PostMapping(path= "/download-relatorio", produces = "application/json")
	public long donwloadRelatorio(@RequestBody List<ItemDeSegurancaResponseDto> itensDto) {
		return service.criaTokenDeAcompanhamento(itensDto);
	}
	
	

}
