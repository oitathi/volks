package br.com.volks.rota2030.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GrupoNotFoundException  extends RuntimeException {
	
	private Logger logger = LogManager.getLogger(GrupoNotFoundException.class);
	
	public GrupoNotFoundException(String grupo) {
		super("Grupo nao encontrado: " + grupo);
		logger.error("Grupo nao encontrado: " + grupo);
	}

}
