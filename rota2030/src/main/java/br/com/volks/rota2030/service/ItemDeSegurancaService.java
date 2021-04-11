package br.com.volks.rota2030.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.volks.rota2030.component.ItemDeSegurancaComponent;
import br.com.volks.rota2030.dto.ItemDeSegurancaRequestDto;
import br.com.volks.rota2030.dto.ItemDeSegurancaResponseDto;
import br.com.volks.rota2030.enums.AcoesEnum;
import br.com.volks.rota2030.enums.StatusRelatorioEnum;
import br.com.volks.rota2030.enums.TabelasEnum;
import br.com.volks.rota2030.exceptions.ItemDeSeguracaUpdateException;
import br.com.volks.rota2030.exceptions.ItemDeSegurancaNotDeletedException;
import br.com.volks.rota2030.exceptions.ItemDeSegurancaNotSalvedException;
import br.com.volks.rota2030.exceptions.ItemDeSegurancaSearchException;
import br.com.volks.rota2030.model.ItemDeSeguranca;
import br.com.volks.rota2030.model.Logs;
import br.com.volks.rota2030.model.Relatorio;
import br.com.volks.rota2030.repository.ItemDeSegurancaRepository;
import br.com.volks.rota2030.repository.LogsRepository;
import br.com.volks.rota2030.repository.RelatorioRepository;
import br.com.volks.rota2030.util.StringOperations;

@Service 
public class ItemDeSegurancaService {
	
	@Autowired
	private ItemDeSegurancaComponent itemSegurancaComponent;
	
	@Autowired
	private ItemDeSegurancaRepository itemSegurancarepository;
	
	@Autowired
	private RelatorioRepository relatorioRepository;
	
	@Autowired
	private LogsRepository logsRepository;
	
	
	
	public ItemDeSegurancaResponseDto salvaItem(ItemDeSegurancaRequestDto itemDto) {
		try {
			ItemDeSeguranca itemSeg =  itemSegurancaComponent.toEntity(itemDto);
			itemSeg = itemSegurancarepository.save(itemSeg);
			Logs log = new Logs(itemDto.getUsuario(), AcoesEnum.CRIAR, TabelasEnum.ITEM_DE_SEGURANCA, itemSeg.getId(), itemSeg.toString(), new Date());
			
			logsRepository.save(log);
						
			return itemSeg.toResponseDto();	
			
		}catch (Exception e) {
			throw new ItemDeSegurancaNotSalvedException(e);
		}
	}
	
	
	public Page<ItemDeSegurancaResponseDto> buscaDinamica(Map<String, String> filtro,  int pageNo, int pageSize, String sortBy){
		try {
			List<ItemDeSegurancaResponseDto> itensDto = new ArrayList<ItemDeSegurancaResponseDto>();
			
			Page<ItemDeSeguranca> entitiesList = itemSegurancarepository.buscaPorFiltros(filtro, pageNo, pageSize, sortBy);
			entitiesList.forEach(entity -> itensDto.add(entity.toResponseDto()));
			
			Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			Page<ItemDeSegurancaResponseDto> resultPaged = new PageImpl<ItemDeSegurancaResponseDto>(itensDto, page, itensDto.size());
					
			return resultPaged; 
			
		}catch (Exception e) {
			throw new ItemDeSegurancaSearchException(e);
		}
	}
	
	public ItemDeSegurancaResponseDto buscaPorId(Long id) {
		try {
		  ItemDeSeguranca iseg = itemSegurancarepository.findByIdFullLoad(id);
		  return iseg.toResponseDto();
		}catch (Exception e) {
			throw new ItemDeSegurancaSearchException(e);
		}
		
	}

		
	public ItemDeSegurancaResponseDto editaItem(ItemDeSegurancaResponseDto dto) {
		try {
				itemSegurancarepository.update(dto.getId(),dto.getDescricao(),dto.getNorma(),dto.isObrigatorio(), dto.getGrupo(), dto.getTipo());
				Logs log = new Logs(dto.getUsuario(), AcoesEnum.EDITAR, TabelasEnum.ITEM_DE_SEGURANCA, dto.getId(), dto.toString(), new Date());
				return dto;
				
		}catch (Exception e) {
			throw new ItemDeSeguracaUpdateException(e);
		}
	}
	
	public boolean deletaItem( Long id, String user) {
		try {
			itemSegurancarepository.deleteById(id);
			Logs log = new Logs(user, AcoesEnum.EXCLUIR, TabelasEnum.ITEM_DE_SEGURANCA,id, "", new Date());
			return true;
		}catch(Exception e) {
			throw new ItemDeSegurancaNotDeletedException(e);
		}
	}
	
	public long criaTokenDeAcompanhamento(List<ItemDeSegurancaResponseDto> itens) {
		Relatorio relatorio = new Relatorio(StatusRelatorioEnum.CRIADO, StringOperations.listToCsv(itens), new Date());
		relatorio = relatorioRepository.save(relatorio);
		
		return relatorio.getId(); 
	}
	

}
