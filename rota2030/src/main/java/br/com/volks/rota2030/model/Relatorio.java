package br.com.volks.rota2030.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import br.com.volks.rota2030.enums.StatusRelatorioEnum;

@Entity
@Table(name = "RELATORIO")
public class Relatorio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RELATORIO_ID")
	private Long id;
	
	@Column(name = "RELATORIO_STATUS")
	private String status;
	
	@Column(name="RELATORIO_CONTEUDO") 
    @Lob 
    private String conteudo;
	
	@Column(name="RELATORIO_DATA_CRIACAO") 
	private Date dataCriacao;
	
	@Column(name="RELATORIO_DATA_DOWNLOAD") 
	private Date dataDownload;
		

	public Relatorio() {
		
	}
	
	
	public Relatorio(StatusRelatorioEnum statusEnum, String conteudo, Date dataCriacao) {
		this.status = statusEnum.getSTATUS();
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
	}




	public Long getId() {
		return id;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public Date getDataDownload() {
		return dataDownload;
	}

	public void setDataDownload(Date dataDownload) {
		this.dataDownload = dataDownload;
	}
	
	

}
