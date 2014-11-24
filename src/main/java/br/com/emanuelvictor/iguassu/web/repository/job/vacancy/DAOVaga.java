package br.com.emanuelvictor.iguassu.web.repository.job.vacancy;

import br.com.emanuelvictor.iguassu.web.entity.Empresa;
import br.com.emanuelvictor.iguassu.web.entity.SituacaoVaga;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;

import java.util.List;

@Transactional
public interface DAOVaga extends JpaRepository<Vaga, Long> {

    @Transactional(readOnly = true)
    @Query("select v from Vaga v WHERE ((v.id =:id or :id is null) and (v.situacao =:situacao or :situacao is null) and (v.cargo.id =:cargo_id or :cargo_id is null) and (v.salario LIKE %:salario% or :salario is null) and (v.empresa.id =:empresa_id or :empresa_id is null) and (v.observacoes LIKE %:observacoes% or :observacoes is null) and (v.cnpj LIKE %:cnpj% or :cnpj is null) and (v.endereco.rua LIKE %:rua% or :rua is null) and (v.endereco.numero LIKE %:numero% or :numero is null) and (v.endereco.cep LIKE %:cep% or :cep is null) and (v.endereco.complemento LIKE %:complemento% or :complemento is null))")
    public List<Vaga> find(@Param("id") Long id, @Param("situacao") SituacaoVaga situacao,
                           @Param("cargo_id") Long cargo_id, @Param("salario") Double salario,
                           @Param("empresa_id") Long empresa_id, @Param("observacoes") String observacoes,
                           @Param("rua") String rua, @Param("numero") String numero,
                           @Param("cep") String cep, @Param("complemento") String complemento,
                           Pageable pageable);


    @Transactional(readOnly = true)
    @Query("select v from Vaga v WHERE ((v.id =:id or :id is null) and (v.situacao =:situacao or :situacao is null) and (v.cargo.id =:cargo_id or :cargo_id is null) and (v.salario LIKE %:salario% or :salario is null) and (v.empresa.id =:empresa_id or :empresa_id is null) and (v.observacoes LIKE %:observacoes% or :observacoes is null) and (v.cnpj LIKE %:cnpj% or :cnpj is null) and (v.endereco.rua LIKE %:rua% or :rua is null) and (v.endereco.numero LIKE %:numero% or :numero is null) and (v.endereco.cep LIKE %:cep% or :cep is null) and (v.endereco.complemento LIKE %:complemento% or :complemento is null)" +
            " and (((:bairro_id is not null and v.endereco.bairro.id =:bairro_id)" +
            "  or ((:bairro_id is null) and :cidade_id is not null and v.endereco.bairro.cidade.id =:cidade_id)" +
            "  or ((:cidade_id is null) and :estado_id is not null and v.endereco.bairro.cidade.estado.id =:estado_id)" +
            "  or ((:estado_id is null) and :pais_id is not null and v.endereco.bairro.cidade.estado.pais.id =:pais_id " +
            "  ))))")
    public List<Vaga> find(@Param("id") Long id, @Param("situacao") SituacaoVaga situacao,
                           @Param("cargo_id") Long cargo_id, @Param("salario") Double salario,
                           @Param("empresa_id") Long empresa_id, @Param("observacoes") String observacoes,
                           @Param("rua") String rua, @Param("numero") String numero,
                           @Param("cep") String cep, @Param("complemento") String complemento,
                           @Param("bairro_id") Long bairro_id,
                           @Param("cidade_id") Long cidade_id,
                           @Param("estado_id") Long estado_id,
                           @Param("pais_id") Long pais_id,
                           Pageable pageable);



}