package br.com.volks.rota2030.dto;

public class ItemDeSegurancaResponseDto {
	
	private long id;
	private String descricao;
	private String norma;
	private String grupo;
	private String tipo;
	private String usuario;
	private boolean obrigatorio;
	
	
	public ItemDeSegurancaResponseDto() {
		
	}
	
	
	public ItemDeSegurancaResponseDto(long id, String descricao, String norma, String grupo, String tipo,	boolean obrigatorio) {
		this.id = id;
		this.descricao = descricao;
		this.norma = norma;
		this.grupo = grupo;
		this.tipo = tipo;
		this.obrigatorio = obrigatorio;
	}



	public ItemDeSegurancaResponseDto(long id, String descricao, String norma, String grupo, String tipo, boolean obrigatorio, String usuario) {
		this.id = id;
		this.descricao = descricao;
		this.norma = norma;
		this.grupo = grupo;
		this.tipo = tipo;
		this.obrigatorio = obrigatorio;
		this.usuario = usuario;
	}


	public String toCsv() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append(";");
		sb.append(descricao);
		sb.append(";");
		sb.append(norma);
		sb.append(";");
		sb.append(grupo);
		sb.append(";");
		sb.append(tipo);
		sb.append(";");
		sb.append(obrigatorio);
		sb.append(System.lineSeparator());
		
		return sb.toString();
		
	}
	


	public long getId() {
		return id;
	}


	public String getUsuario() {
		return usuario;
	}


	public String getDescricao() {
		return descricao;
	}


	public String getNorma() {
		return norma;
	}


	public String getGrupo() {
		return grupo;
	}


	public String getTipo() {
		return tipo;
	}


	public boolean isObrigatorio() {
		return obrigatorio;
	}


	
	
	

}
