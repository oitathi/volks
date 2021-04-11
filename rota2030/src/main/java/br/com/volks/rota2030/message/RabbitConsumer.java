package br.com.volks.rota2030.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.volks.rota2030.exceptions.RabbitException;
import br.com.volks.rota2030.service.RabbitService;

@Component
public class RabbitConsumer {
	
	
	@Autowired
	private RabbitService service;
	
	@RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
	public void consume(long token) {
		try {
			service.receiveFromConsumer(token);
		}catch(Exception e) {
			throw new RabbitException(e);
		}
	}

}
