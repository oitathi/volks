package br.com.volks.rota2030.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.volks.rota2030.service.RabbitService;

@RestController
@RequestMapping(path = "rabbit")
public class RabbitController {
	
	@Autowired
	private RabbitService service;

	@ResponseStatus(HttpStatus.ACCEPTED)
	@GetMapping(path = "/send", produces = "application/json")
	public boolean sendToConsumer(@RequestParam long token) {
		return service.sendToConsumerSuccess(token);
	}
}
