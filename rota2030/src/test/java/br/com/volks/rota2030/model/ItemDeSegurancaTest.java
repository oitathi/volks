package br.com.volks.rota2030.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ItemDeSegurancaTest {
	
	@Test
	public void loadClass() {
		ItemDeSeguranca iseg = new ItemDeSeguranca();
		assertNull(iseg.getId());
		assertNull(iseg.getDescricao());
		assertNull(iseg.getNorma());
		assertEquals(false,iseg.isObrigatorio());
		assertNull(iseg.getGrupo());
		assertNull(iseg.getTipo());
	}

}
