package br.com.volks.rota2030.exceptions;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;

public class RabbitException extends RuntimeException {
	
	private Logger logger = LogManager.getLogger(RabbitException.class);
	
	public RabbitException(Exception e) {
		super("Erro ao produzir mensagem no rabbit. Motivo: " + ExceptionUtils.getFullStackTrace(e));
		logger.error("Erro ao produzir mensagem no rabbit. Motivo: " + ExceptionUtils.getFullStackTrace(e));
		
		throw new AmqpRejectAndDontRequeueException(e);
	}

}
