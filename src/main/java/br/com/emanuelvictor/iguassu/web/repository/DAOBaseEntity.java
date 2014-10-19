package br.com.emanuelvictor.iguassu.web.repository;

import br.com.emanuelvictor.iguassu.web.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DAOBaseEntity extends JpaRepository<BaseEntity, Long> {

	@Transactional
	@Query("select b from BaseEntity b WHERE b.cep =:cep")
	public BaseEntity find(@Param("cep") String cep);

}