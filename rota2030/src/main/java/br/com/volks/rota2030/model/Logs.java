package br.com.volks.rota2030.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.volks.rota2030.enums.AcoesEnum;
import br.com.volks.rota2030.enums.TabelasEnum;

@Entity
@Table(name = "LOGS")
public class Logs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOGS_ID")
	private long id;
	@Column(name = "LOGS_USUARIO")
	private String usuario;
	@Column(name = "LOGS_ACAO")
	private String acao;
	@Column(name = "LOGS_TABELA")
	private String tabela;
	@Column(name = "LOGS_ID_AFETADO")
	private long idAfetado;
	@Column(name = "LOGS_MUDANCAS")
	private String  mudancas;
	@Column(name = "LOGS_DATA_MODIFICACAO")
	private Date dataModificacao;
	
		
	
	public Logs() {
	}
	
	
	
	public Logs(String usuario, AcoesEnum acaoEnum, TabelasEnum tabelaEnum, long idAfetado, String mudancas, Date dataModificacao) {
		super();
		this.usuario = usuario;
		this.acao = acaoEnum.name();
		this.tabela = tabelaEnum.name();
		this.idAfetado = idAfetado;
		this.mudancas = mudancas;
		this.dataModificacao = dataModificacao;
		
	}



	public long getId() {
		return id;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getAcao() {
		return acao;
	}
	public String getTabela() {
		return tabela;
	}
	
	public long getIdAfetado() {
		return idAfetado;
	}

	public String getMudancas() {
		return mudancas;
	}
	public Date getDataModificacao() {
		return dataModificacao;
	}

	
	

}
