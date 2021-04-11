package br.com.volks.rota2030.model;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class GrupoTest {
	
	@Test
	public void loadClass() {
		Grupo grupo = new Grupo();
		assertNull(grupo.getId());
		assertNull(grupo.getDescricao());
		assertNull(grupo.getItensDeSeguranca());
	}

}
