package br.com.volks.rota2030.exceptions;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExcelException extends RuntimeException {
	
	private Logger logger = LogManager.getLogger(ExcelException.class);
	
	public ExcelException(Exception e) {
		super("Erro ao criar arquivo Excel: " + ExceptionUtils.getStackTrace(e));
		logger.error("Erro ao criar arquivo Excel: " + ExceptionUtils.getStackTrace(e));
	}

}
