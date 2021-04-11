package br.com.volks.rota2030.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class LogsTest {
	
	@Test
	public void loadClass() {
		Logs log = new Logs();
		assertEquals(0,log.getId());
		assertNull(log.getUsuario());
		assertNull(log.getAcao());
		assertNull(log.getTabela());
		assertEquals(0,log.getIdAfetado());
		assertNull(log.getMudancas());
		assertNull(log.getDataModificacao());
	}

}
