package br.com.volks.rota2030.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RabbitConfiguration {
	
	@Value("${spring.rabbitmq.request.routing-key.producer}")
	private String queue;
	
	@Value("${spring.rabbitmq.request.exchange.producer}")
	private String exchange;
	
	@Value("${spring.rabbitmq.request.deadletter.producer}")
	private String deadLetter;
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Bean
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(jacksonConverter());
		return factory;
	}
	
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(jacksonConverter());
		return template;
	}
	
	@Bean
	public Jackson2JsonMessageConverter jacksonConverter() {
		final ObjectMapper mapper = Jackson2ObjectMapperBuilder
				.json()
				.modules(new JavaTimeModule())
				.dateFormat(new StdDateFormat())
				.build();
		
		return new Jackson2JsonMessageConverter(mapper);
	}
	
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchange);
	}
	
	@Bean
	public Queue deadLetter() {
		return new 	Queue(deadLetter);
	}
	
	@Bean
	public Queue queue() {
		Map<String, Object> args = new HashMap<>();
		args.put("x-dead-letter-exchange", exchange);
		args.put("x-dead-letter-routing-key", deadLetter);
		return new Queue(queue, true, false, false, args);
	}
	
	@Bean
	public Binding bindingQueue() {
		return BindingBuilder.bind(queue())
				.to(exchange()).with(queue);
	}
	
	@Bean
	public Binding bindingDeadLetter() {
		return BindingBuilder.bind(deadLetter())
				.to(exchange()).with(deadLetter);
	}

}
