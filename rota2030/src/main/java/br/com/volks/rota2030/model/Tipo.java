package br.com.volks.rota2030.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO")
public class Tipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TIPO_ID")
	private Long id;
	
	@Column(name = "TIPO_DESCRICAO")
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipo")
	private Set<ItemDeSeguranca> itensDeSeguranca;
	
	public Tipo() {
		
	}
	
	public Tipo(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}


	public Long getId() {
		return id;
	}

	
	public String getDescricao() {
		return descricao;
	}


	public Set<ItemDeSeguranca> getItensDeSeguranca() {
		return itensDeSeguranca;
	}
	
	
	
	
	
	
	

}
