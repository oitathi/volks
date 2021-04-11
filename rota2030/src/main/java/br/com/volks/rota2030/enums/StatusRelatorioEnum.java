package br.com.volks.rota2030.enums;

public enum StatusRelatorioEnum {
	
	CRIADO("Criado"),
	ENVIADO_PARA_FILA ("Enviado para a fila"),
	PROCESSANDO ("Processando"),
	DISPONIVEL ("Disponivel"),
	BAIXADO("Baixado");
	
	private final String STATUS;
	
	private StatusRelatorioEnum(final String STATUS) {
		this.STATUS = STATUS;
	}

	public String getSTATUS() {
		return STATUS;
	}
	
	

}
