package br.com.volks.rota2030.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.volks.rota2030.dto.ItemDeSegurancaRequestDto;
import br.com.volks.rota2030.dto.ItemDeSegurancaResponseDto;
import br.com.volks.rota2030.model.Grupo;
import br.com.volks.rota2030.model.ItemDeSeguranca;
import br.com.volks.rota2030.model.Tipo;
import br.com.volks.rota2030.service.GrupoService;
import br.com.volks.rota2030.service.TipoService;

@Component
public class ItemDeSegurancaComponent {
	
	@Autowired
	private GrupoService grupoService;

	@Autowired
	private TipoService tipoService;
	
	
	public ItemDeSeguranca toEntity(ItemDeSegurancaRequestDto dto) {
		Grupo grupoEntity = grupoService.buscaPorDescricao(dto.getGrupo());
		Tipo tipoEntity = tipoService.buscaPorDescricao(dto.getTipo());

		return new ItemDeSeguranca(dto.getDescricao(), dto.getNorma(), dto.isObrigatorio(), grupoEntity, tipoEntity);
	}
	
	public ItemDeSeguranca toEntity(ItemDeSegurancaResponseDto dto) {
		Grupo grupoEntity = grupoService.buscaPorDescricao(dto.getGrupo());
		Tipo tipoEntity = tipoService.buscaPorDescricao(dto.getTipo());

		return new ItemDeSeguranca(dto.getId(),dto.getDescricao(), dto.getNorma(), dto.isObrigatorio(), grupoEntity, tipoEntity);
	}

}
