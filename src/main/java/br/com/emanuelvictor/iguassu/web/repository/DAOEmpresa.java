package br.com.emanuelvictor.iguassu.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.Empresa;

@Transactional
public interface DAOEmpresa extends JpaRepository<Empresa, Long> {

	@Transactional(readOnly = true)
	@Query("select e from Empresa e WHERE ((e.nome LIKE %:nome% or :nome is null) and (e.cnpj LIKE %:cnpj% or :cnpj is null) and (e.rua LIKE %:rua% or :rua is null) and (e.numero LIKE %:numero% or :numero is null) and (e.cep LIKE %:cep% or :cep is null) and (e.complemento LIKE %:complemento% or :complemento is null))")
	public List<Empresa> find(@Param("nome") String nome,
			@Param("cnpj") String cnpj, @Param("rua") String rua,
			@Param("numero") String numero, @Param("cep") String cep,
			@Param("complemento") String complemento);

	@Transactional(readOnly = true)
	@Query("select e from Empresa e WHERE ((e.nome LIKE %:nome% or :nome is null) and (e.cnpj LIKE %:cnpj% or :cnpj is null) and (e.rua LIKE %:rua% or :rua is null) and (e.numero LIKE %:numero% or :numero is null) and (e.cep LIKE %:cep% or :cep is null) and (e.complemento LIKE %:complemento% or :complemento is null) and (e.bairro.id =:bairro_id or :bairro_id is null) and (e.bairro.cidade.id =:cidade_id or :cidade_id is null) and (e.bairro.cidade.estado.id =:estado_id or :estado_id is null) and (e.bairro.cidade.estado.pais.id =:pais_id or :pais_id is null))")
	public List<Empresa> find(@Param("nome") String nome,
			@Param("cnpj") String cnpj, @Param("rua") String rua,
			@Param("numero") String numero, @Param("cep") String cep,
			@Param("complemento") String complemento,
			@Param("bairro_id") Long bairro_id,
			@Param("cidade_id") Long cidade_id,
			@Param("estado_id") Long estado_id, @Param("pais_id") Long pais_id);
}