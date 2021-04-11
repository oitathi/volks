package br.com.volks.rota2030.exceptions;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemDeSegurancaSearchException extends RuntimeException {
	
	private Logger logger = LogManager.getLogger(ItemDeSegurancaSearchException.class);
	
	public ItemDeSegurancaSearchException(Exception e) {
		super("Erro na busca dinamica de Itens de Seguranca. Motivo: " + ExceptionUtils.getFullStackTrace(e));
		logger.error("Erro na busca dinamica de Itens de Seguranca. Motivo: " + ExceptionUtils.getFullStackTrace(e));
	}

}
