package br.com.volks.rota2030.excel;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.volks.rota2030.exceptions.ExcelException;
import br.com.volks.rota2030.model.Relatorio;
import br.com.volks.rota2030.repository.RelatorioRepository;

public class ExcelWritter {
	
	@Autowired
	private RelatorioRepository relatorioRepository;
	
	private String conteudoRelatorio;
	
	public ExcelWritter(long token) {
		recuperaConteudo(token);
		criaArquivo();
	}
	
	private void recuperaConteudo(long token)  {
		try {
			
			Optional<Relatorio> relatorioOp = relatorioRepository.findById(token);
			if(relatorioOp.isPresent()) {
				Relatorio relatorio = relatorioOp.get();
				conteudoRelatorio = relatorio.getConteudo();
			}
			
			
		}catch (Exception e) {
			throw new ExcelException(e);
		}
	}
	
	private void criaArquivo() {
		
	}

}
