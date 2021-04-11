package br.com.volks.rota2030.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.volks.rota2030.model.Relatorio;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long>,PagingAndSortingRepository<Relatorio, Long>, JpaSpecificationExecutor<Relatorio> {

}
