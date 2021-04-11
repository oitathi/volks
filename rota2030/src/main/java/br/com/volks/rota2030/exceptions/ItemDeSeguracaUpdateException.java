package br.com.volks.rota2030.exceptions;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemDeSeguracaUpdateException extends RuntimeException {
	
	private Logger logger = LogManager.getLogger(ItemDeSeguracaUpdateException.class);
	
	public ItemDeSeguracaUpdateException(Exception e) {
		super("Item de seguranca nao editado: " + ExceptionUtils.getFullStackTrace(e));
		logger.error("Item de seguranca nao editado: " + ExceptionUtils.getFullStackTrace(e));
	}

}
