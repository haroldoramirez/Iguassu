package br.com.emanuelvictor.iguassu.web.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.Lancamento;


import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Transactional
public interface DAOLancamento extends JpaRepository<Lancamento, Long> {

	@Transactional(readOnly = true)
    @Query("select l from Lancamento l where l.pessoa.id = :id")
	public LinkedList<Lancamento> getByIdPessoa(@Param("id") Long id);
//
//	@Transactional(readOnly = true)
//	@Query("select e from Empresa e where e.nome like %?1%")
//	public List<Empresa> getByEmpresa(String nome);
//
//	public List<Empresa> findByNomeContainingAndCNPJContaining(String nome, String CNPJ);
//	
	@Transactional(readOnly = true)
    @Query("select l from Lancamento l WHERE ((l.dataDeCadastro =:dataDeCadastro or :dataDeCadastro is null) and (l.dataDeVencimento =:dataDeVencimento or :dataDeVencimento is null) and (l.dataDePagamento =:dataDePagamento or :dataDePagamento is null))")
	public List<Lancamento> query(@Param("dataDeCadastro") Calendar dataDeCadastro, @Param("dataDeVencimento") Calendar dataDeVencimento, @Param("dataDePagamento") Calendar dataDePagamento, Pageable pageable);

//    @Transactional(readOnly = true)
//    @Query("select l from Lancamento l WHERE (l.dataDeCadastro =:dataDeCadastro)")
//    public List<Lancamento> testQuery(@Param("dataDeCadastro") Calendar dataDeCadastro, Pageable pageable);


}