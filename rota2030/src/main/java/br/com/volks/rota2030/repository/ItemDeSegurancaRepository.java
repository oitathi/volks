package br.com.volks.rota2030.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.volks.rota2030.model.Grupo;
import br.com.volks.rota2030.model.ItemDeSeguranca;
import br.com.volks.rota2030.model.Tipo;

public interface ItemDeSegurancaRepository extends JpaRepository<ItemDeSeguranca, Long>,PagingAndSortingRepository<ItemDeSeguranca, Long>, ItemDeSegurancaRepositoryCustom {

	@Transactional
	@Modifying
	@Query("UPDATE ItemDeSeguranca iseg SET"
				+ " iseg.descricao = ?2,"
				+ " iseg.norma = ?3,"
				+ " iseg.isObrigatorio =?4,"
				+ " iseg.grupo ="
				+ 		" (SELECT g FROM Grupo g WHERE g.descricao =?5),"
				+ " iseg.tipo ="
				+ 		" (SELECT t FROM Tipo t WHERE t.descricao = ?6)"
				+ " WHERE iseg.id =?1")
	int update(long isegId, String descricao, String norma, boolean isObrigatorio, String grupoDescricao, String tipoDescricao);

	
}
