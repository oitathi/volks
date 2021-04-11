package br.com.volks.rota2030.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.volks.rota2030.enums.StatusRelatorioEnum;
import br.com.volks.rota2030.excel.ExcelWritter;
import br.com.volks.rota2030.message.RabbitProducer;
import br.com.volks.rota2030.model.Relatorio;
import br.com.volks.rota2030.repository.RelatorioRepository;

@Service
public class RabbitService {

	@Autowired
	private RabbitProducer rabbit;

	@Autowired
	private RelatorioRepository relatorioRepository;

	
	public boolean sendToConsumerSuccess(long token) {
		rabbit.producer(token);
		atualizaStatusRelatorio(token, StatusRelatorioEnum.ENVIADO_PARA_FILA, false);
		return true;
	}

	public void receiveFromConsumer(long token) {
		atualizaStatusRelatorio(token, StatusRelatorioEnum.PROCESSANDO, false);
		new ExcelWritter(token);
		atualizaStatusRelatorio(token, StatusRelatorioEnum.DISPONIVEL, true);
	}

	private void atualizaStatusRelatorio(long token, StatusRelatorioEnum statusEnum, boolean jaProcessdo) {
		Optional<Relatorio> relatorioOp = relatorioRepository.findById(token);

		if (relatorioOp.isPresent()) {
			Relatorio relatorio = relatorioOp.get();
			relatorio.setStatus(statusEnum.getSTATUS());

			if (jaProcessdo) {
				relatorio.setConteudo("");
			}

			relatorioRepository.save(relatorio);
		}
	}
}
