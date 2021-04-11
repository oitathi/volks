package br.com.volks.rota2030.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.volks.rota2030.dto.ItemDeSegurancaResponseDto;
import br.com.volks.rota2030.exceptions.ToCsvException;

public class StringOperationsTest {
	
	
	
	private List<ItemDeSegurancaResponseDto> mockaListaItemDeSegurancaResponseDto(){
		List<ItemDeSegurancaResponseDto> lista = new ArrayList<ItemDeSegurancaResponseDto>();
		
		lista.add(new ItemDeSegurancaResponseDto(1L, "item w", "norma w", "grupo a", "caminhonete", true));
		lista.add(new ItemDeSegurancaResponseDto(2L, "item x", "norma x", "grupo b", "automovel", false));
		
		return lista;
	}
	
	private String mockaStringDeItemDeSegurancaResponseDtoParaCsv() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(1L);
		sb.append(";");
		sb.append("item w");
		sb.append(";");
		sb.append("norma w");
		sb.append(";");
		sb.append("grupo a");
		sb.append(";");
		sb.append("caminhonete");
		sb.append(";");
		sb.append(true);
		sb.append(System.lineSeparator());
		
		sb.append(2L);
		sb.append(";");
		sb.append("item x");
		sb.append(";");
		sb.append("norma x");
		sb.append(";");
		sb.append("grupo b");
		sb.append(";");
		sb.append("automovel");
		sb.append(";");
		sb.append(false);
		sb.append(System.lineSeparator());
		
		return sb.toString();
	}
	
	@Test
	public void deveConverterListaDeItemDeSegurancaResponseDtoParaCsv() {
		String actual = StringOperations.listToCsv(mockaListaItemDeSegurancaResponseDto());
		assertEquals(mockaStringDeItemDeSegurancaResponseDtoParaCsv(), actual);
	}
	
	@Test(expected = ToCsvException.class)
	public void deveLancarExcecaoAoEntarCoverterLista() {
		List<ItemDeSegurancaResponseDto> lista = mockaListaItemDeSegurancaResponseDto();
		lista.add(null);
		
		StringOperations.listToCsv(lista);
	}

}
