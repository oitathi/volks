package br.com.volks.rota2030.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.volks.rota2030.main.ItemDeSegurancaJsonFactory;
import br.com.volks.rota2030.service.RabbitService;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class RabbitControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private RabbitController controller;
	
	@Mock
	private RabbitService service;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void deveEnviarMensagemParaConsumer() throws Exception {
		Mockito.when(service.sendToConsumerSuccess(Mockito.anyLong())).thenReturn(true);
		
		mockMvc.perform(get("/rabbit/send")
				.param("token", "1")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

	}

}
