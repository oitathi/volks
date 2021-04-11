package br.com.volks.rota2030.exceptions;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToCsvException extends RuntimeException {
	
	private Logger logger = LogManager.getLogger(ToCsvException.class);
	
	public ToCsvException(Exception e) {
		super("Erro ao converter item do relatorio para csv: " + ExceptionUtils.getFullStackTrace(e));
		logger.error("Erro ao converter item do relatorio para csv: " + ExceptionUtils.getFullStackTrace(e));
	}
		

}
