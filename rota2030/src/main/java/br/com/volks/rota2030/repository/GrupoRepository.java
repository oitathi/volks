package br.com.volks.rota2030.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.sun.xml.bind.v2.model.core.ID;

import br.com.volks.rota2030.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long>,PagingAndSortingRepository<Grupo, Long>, QueryByExampleExecutor<Grupo> {
	
	List<Grupo>findByDescricao(String descricao);

}
