package br.com.volks.rota2030.dto;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ItemDeSegurancaResponseDtoTest {
	
	@Test
	public void loadClass() {
		ItemDeSegurancaResponseDto dto = new ItemDeSegurancaResponseDto();
		ItemDeSegurancaResponseDto dto2 = new ItemDeSegurancaResponseDto(1L,"Item X", "Norma Alfa", "Grupo C", "Caminhonete",  false, "tester");

		assertNotNull(dto2.getDescricao());
		
	}

}
