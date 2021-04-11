package br.com.volks.rota2030.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;

import br.com.volks.rota2030.enums.StatusRelatorioEnum;
import br.com.volks.rota2030.exceptions.ItemDeSegurancaNotSalvedException;

public class RelatorioTest {
	
	@Test
	public void loadClass() {
		Relatorio r = new Relatorio();
		
		assertNull(r.getId());
		assertNull(r.getStatus());
		assertNull(r.getConteudo());
		assertNull(r.getDataCriacao());
		assertNull(r.getDataDownload());
	}
	
	@Test
	public void loadClass2() {
		Relatorio r2 = new Relatorio(StatusRelatorioEnum.BAIXADO, "conteudo", new Date());
		r2.setConteudo("");
		r2.setStatus(StatusRelatorioEnum.CRIADO.getSTATUS());
		r2.setDataDownload(new Date());
		
		assertNull(r2.getId());
	}

}
