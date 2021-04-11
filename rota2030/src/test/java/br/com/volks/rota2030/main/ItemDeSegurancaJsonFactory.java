package br.com.volks.rota2030.main;

import java.util.Arrays;
import java.util.List;


import com.google.gson.Gson;

import br.com.volks.rota2030.dto.ItemDeSegurancaRequestDto;
import br.com.volks.rota2030.dto.ItemDeSegurancaResponseDto;

public class ItemDeSegurancaJsonFactory {
	
	private static Gson gson = new Gson();

		
	public static void imprime() {
		System.out.println("Exemplo de Item de Seguranca Request:" + System.lineSeparator() + mockaJsonRequestDto());
		System.out.println(System.lineSeparator());
		System.out.println("Exemplo de Item de Seguranca Response:" + System.lineSeparator() + mockaJsonResponseDto());
		System.out.println(System.lineSeparator());
		System.out.println("Exemplo Item de Seguranca Busca por Filtro:" + System.lineSeparator() + mockaJsonMap());
	}
	
	public static String mockaJsonRequestDto() {
		return gson.toJson(mockaRequestDto());
	}
	
	public static String mockaJsonResponseDto() {
		return gson.toJson(mockaResponseDto());
	}
	
	public static String mockaJsonMap() {
		return gson.toJson(new ItemDeSegurancaFiltroFactory());
	}
	
	public static String mockaListaJsonResponseDto() {
		List<ItemDeSegurancaResponseDto> lista = Arrays.asList(new ItemDeSegurancaResponseDto(1L, "item de seguranca x", "norma x", "Grupo A ", "Automovel", false));
		return gson.toJson(lista);
	}
	
	public static ItemDeSegurancaRequestDto mockaRequestDto() {
		return new ItemDeSegurancaRequestDto("item de seguranaca x", "norma x", "Grupo A" , "Automovel", false, "tester");
	}
	
	public static ItemDeSegurancaResponseDto mockaResponseDto() {
		return new ItemDeSegurancaResponseDto(1L, "item de seguranca x", "norma x", "Grupo A ", "Automovel", false);
	}
}
