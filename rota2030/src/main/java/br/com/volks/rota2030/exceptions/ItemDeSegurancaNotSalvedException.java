package br.com.volks.rota2030.exceptions;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemDeSegurancaNotSalvedException extends RuntimeException {
	
	private Logger logger = LogManager.getLogger(ItemDeSegurancaNotSalvedException.class);
	
	public ItemDeSegurancaNotSalvedException(Exception e) {
		super("Item de seguranca nao foi salvo. Motivo: " + ExceptionUtils.getFullStackTrace(e));
		logger.error("Item de seguranca nao foi salvo. Motivo: " + ExceptionUtils.getFullStackTrace(e));
	}

}
