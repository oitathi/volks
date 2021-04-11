package br.com.volks.rota2030.util;

import java.util.List;

import br.com.volks.rota2030.dto.ItemDeSegurancaResponseDto;
import br.com.volks.rota2030.exceptions.ToCsvException;

public class StringOperations {
	
	public static String listToCsv(List<ItemDeSegurancaResponseDto> itens) {
		try {
			StringBuilder sb = new StringBuilder();
			itens.forEach(responseDto -> sb.append(responseDto.toCsv()));
			return sb.toString();
		}catch(Exception e ) {
			throw new ToCsvException(e);
		}
	}

}
