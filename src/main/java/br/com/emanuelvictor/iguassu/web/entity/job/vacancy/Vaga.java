package br.com.emanuelvictor.iguassu.web.entity.job.vacancy;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.emanuelvictor.iguassu.web.entity.Empresa;
import br.com.emanuelvictor.iguassu.web.entity.base.BaseEntity;
import br.com.emanuelvictor.iguassu.web.entity.job.Cargo;

//Tabela responsável por armazenar os dados de vagas no banco de dados
@Entity
public class Vaga extends BaseEntity {

	private static final long serialVersionUID = 8892556262733668538L;

	// dados da vaga

	@ManyToOne(optional = true)
	private Empresa empresa;

	@NotNull
	@ManyToOne(optional = false)
	private Cargo cargo;

	@Column(length = 100)
	private String observacoes;

	@Column(length = 50)
	private String situacao;

	@Column(nullable = true, length = 100)
	private Double salario;

	// Preferencias do candidato

	@Column(length = 50)
	private String preferenciaSexoMasculino;

	@Column(length = 50)
	private Integer idadeInicialDoCandidato;

	@Column(length = 50)
	private Integer idadeFinalDoCandidato;

	@Column(length = 50)
	private String estadoCivil;

	// TODO deve ter o handles de necessidades especiais, cadastrar quais
	// necessidades especiais ele quer na query


    @Column(length = 20)
    protected String telefoneResidencial;

    @Column(length = 20)
    protected String telefoneComercial;

    @Column(length = 20)
    protected String telefoneCelular;

    @Column(length = 50)
    protected String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar dataDeCadastro = Calendar.getInstance();

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getPreferenciaSexoMasculino() {
		return preferenciaSexoMasculino;
	}

	public void setPreferenciaSexoMasculino(String preferenciaSexoMasculino) {
		this.preferenciaSexoMasculino = preferenciaSexoMasculino;
	}

	public Integer getIdadeInicialDoCandidato() {
		return idadeInicialDoCandidato;
	}

	public void setIdadeInicialDoCandidato(Integer idadeInicialDoCandidato) {
		this.idadeInicialDoCandidato = idadeInicialDoCandidato;
	}

	public Integer getIdadeFinalDoCandidato() {
		return idadeFinalDoCandidato;
	}

	public void setIdadeFinalDoCandidato(Integer idadeFinalDoCandidato) {
		this.idadeFinalDoCandidato = idadeFinalDoCandidato;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Calendar getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Calendar dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

}
