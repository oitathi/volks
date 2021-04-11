package br.com.volks.rota2030.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TipoNotFoundException extends RuntimeException {
	
	private Logger logger = LogManager.getLogger(TipoNotFoundException.class);
	
	public TipoNotFoundException(String tipo) {
		super("Tipo nao encontrado: " + tipo);
		logger.error("Tipo nao encontrado: " + tipo);
	}


}
