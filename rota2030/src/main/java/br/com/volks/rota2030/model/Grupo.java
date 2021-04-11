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
@Table(name = "GRUPO")
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GRUPO_ID")
	private Long id;
	
	@Column(name = "GRUPO_DESCRICAO")
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
	private Set<ItemDeSeguranca> itensDeSeguranca;
	
	

	public Grupo() {
		
	}


	public Grupo(Long id, String descricao) {
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
