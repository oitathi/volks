package br.com.volks.rota2030.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.volks.rota2030.model.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Long> {

	List<Tipo> findByDescricao(String descricao);

}

