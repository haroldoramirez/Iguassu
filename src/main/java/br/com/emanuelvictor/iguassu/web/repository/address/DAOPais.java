package br.com.emanuelvictor.iguassu.web.repository.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.address.Pais;

@Transactional
public interface DAOPais extends JpaRepository<Pais, Long> {

    @Transactional(readOnly = true)
    @Query("select p from Pais p WHERE p.nome LIKE %:nome% or :nome is null")
	public Pais[] findPaisesByNomePais(@Param("nome") String nome);

}