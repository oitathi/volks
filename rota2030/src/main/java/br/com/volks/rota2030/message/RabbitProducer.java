package br.com.volks.rota2030.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.volks.rota2030.exceptions.RabbitException;

@Component
public class RabbitProducer  {
	
	@Autowired
	private RabbitTemplate template;

	@Value("${spring.rabbitmq.request.routing-key.producer}")
	private String queue;
	
	@Value("${spring.rabbitmq.request.exchange.producer}")
	private String exchange;
	
	
	public void producer(long token)  {
		try {
			template.convertAndSend(exchange, queue, token );
		}catch(Exception e) {
			throw new RabbitException(e);
		}
		
	}

}
