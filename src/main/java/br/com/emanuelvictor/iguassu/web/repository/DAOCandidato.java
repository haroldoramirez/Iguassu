package br.com.emanuelvictor.iguassu.web.repository;

import br.com.emanuelvictor.iguassu.web.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Transactional
public interface DAOCandidato extends JpaRepository<Candidato, Long> {

    @Transactional(readOnly = true)
    @Query("select c from Candidato c WHERE ((c.id =:id or :id is null) and (c.nome LIKE %:nome% or :nome is null) and (c.cpf LIKE %:cpf% or :cpf is null) and (c.rg LIKE %:rg% or :rg is null) and (c.telefoneCelular LIKE %:telefoneCelular% or :telefoneCelular is null) and (c.telefoneResidencial LIKE %:telefoneResidencial% or :telefoneResidencial is null) and (c.telefoneComercial LIKE %:telefoneComercial% or :telefoneComercial is null) and (c.email LIKE %:email% or :email is null) and (c.observacoes LIKE %:observacoes% or :observacoes is null) and (c.situacao =:situacao or :situacao is null) and (c.genero =:genero or :genero is null) and (c.necessidadeEspecial =:necessidadeEspecial or :necessidadeEspecial is null) and (c.estadoCivil =:estadoCivil or :estadoCivil is null) and (c.dataNasc =:dataNasc or :dataNasc is null)  and (c.nacionalidade.id =:nacionalidade_id or :nacionalidade_id is null)  and (c.endereco.rua LIKE %:rua% or :rua is null) and (c.endereco.numero LIKE %:numero% or :numero is null) and (c.endereco.cep LIKE %:cep% or :cep is null) and (c.endereco.complemento LIKE %:complemento% or :complemento is null))")
    public List<Candidato> find(@Param("id") Long id, @Param("nome") String nome,
                                @Param("cpf") String cpf, @Param("rg") String rg,
                                @Param("telefoneCelular") String telefoneCelular,
                                @Param("telefoneComercial") String telefoneComercial,
                                @Param("telefoneResidencial") String telefoneResidencial,
                                @Param("email") String email, @Param("observacoes") String observacoes,
                                @Param("situacao") SituacaoCandidato situacao, @Param("genero") Genero genero,
                                @Param("necessidadeEspecial") NecessidadeEspecial necessidadeEspecial,
                                @Param("estadoCivil") EstadoCivil estadoCivil,
                                @Param("dataNasc") Calendar dataNasc,
                                @Param("nacionalidade_id") Long nacionalidade_id,
                                @Param("rua") String rua, @Param("numero") String numero,
                                @Param("cep") String cep, @Param("complemento") String complemento,
                                Pageable pageable);

    @Transactional(readOnly = true)
    @Query("select c from Candidato c WHERE ((c.id =:id or :id is null) and (c.nome LIKE %:nome% or :nome is null) and (c.cpf LIKE %:cpf% or :cpf is null) and (c.rg LIKE %:rg% or :rg is null) and (c.telefoneCelular LIKE %:telefoneCelular% or :telefoneCelular is null) and (c.telefoneResidencial LIKE %:telefoneResidencial% or :telefoneResidencial is null) and (c.telefoneComercial LIKE %:telefoneComercial% or :telefoneComercial is null) and (c.email LIKE %:email% or :email is null) and (c.observacoes LIKE %:observacoes% or :observacoes is null) and (c.situacao =:situacao or :situacao is null) and (c.genero =:genero or :genero is null) and (c.necessidadeEspecial =:necessidadeEspecial or :necessidadeEspecial is null) and (c.estadoCivil =:estadoCivil or :estadoCivil is null) and (c.dataNasc =:dataNasc or :dataNasc is null)  and (c.nacionalidade.id =:nacionalidade_id or :nacionalidade_id is null) and (c.endereco.rua LIKE %:rua% or :rua is null) and (c.endereco.numero LIKE %:numero% or :numero is null) and (c.endereco.cep LIKE %:cep% or :cep is null) and (c.endereco.complemento LIKE %:complemento% or :complemento is null)" +
                                        " and (((:bairro_id is not null and c.endereco.bairro.id =:bairro_id)" +
                                        "  or ((:bairro_id is null) and :cidade_id is not null and c.endereco.bairro.cidade.id =:cidade_id)" +
                                        "  or ((:cidade_id is null) and :estado_id is not null and c.endereco.bairro.cidade.estado.id =:estado_id)" +
                                        "  or ((:estado_id is null) and :pais_id is not null and c.endereco.bairro.cidade.estado.pais.id =:pais_id " +
                                        "  ))))")
    public List<Candidato> find(@Param("id") Long id, @Param("nome") String nome,
                                @Param("cpf") String cpf, @Param("rg") String rg,
                                @Param("telefoneCelular") String telefoneCelular,
                                @Param("telefoneComercial") String telefoneComercial,
                                @Param("telefoneResidencial") String telefoneResidencial,
                                @Param("email") String email, @Param("observacoes") String observacoes,
                                @Param("situacao") SituacaoCandidato situacao, @Param("genero") Genero genero,
                                @Param("necessidadeEspecial") NecessidadeEspecial necessidadeEspecial,
                                @Param("estadoCivil") EstadoCivil estadoCivil,
                                @Param("dataNasc") Calendar dataNasc,
                                @Param("nacionalidade_id") Long nacionalidade_id,
                                @Param("rua") String rua, @Param("numero") String numero,
                                @Param("cep") String cep, @Param("complemento") String complemento,
                                @Param("bairro_id") Long bairro_d,
                                @Param("cidade_id") Long cidade_id,
                                @Param("estado_id") Long estado_id,
                                @Param("pais_id") Long pais_id,
                                Pageable pageable);


//    @Transactional(readOnly = true)
//    @Query("select c from Candidato c WHERE ((c.id =:id or :id is null) and (c.nome LIKE %:nome% or :nome is null) and (c.cpf LIKE %:cpf% or :cpf is null) and (c.rg LIKE %:rg% or :rg is null) and (c.telefoneCelular LIKE %:telefoneCelular% or :telefoneCelular is null) and (c.observacoes LIKE %:observacoes% or :observacoes is null) and (c.situacao =:situacao or :situacao is null) and (c.genero :genero or :genero is null) and (c.necessidadeEspecial :necessidadeEspecial or :necessidadeEspecial is null) and (c.estadoCivil :estadoCivil or :estadoCivil is null) and (c.dataNasc :dataNasc or :dataNasc is null))")
//    public List<Empresa> find(
//            @Param("id") Long id,
//            @Param("nome") String nome,
//            @Param("cnpj") String cnpj, @Param("rua") String rua,
//            @Param("numero") String numero, @Param("cep") String cep,
//            @Param("complemento") String complemento,
//            @Param("bairro_id") Long bairro_d,
//            @Param("cidade_id") Long cidade_id,
//            @Param("estado_id") Long estado_id,
//            @Param("pais_id") Long pais_id,
//            Pageable pageable);

}