package br.com.emanuelvictor.iguassu.web.entity.job.vacancy;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.emanuelvictor.iguassu.web.entity.Empresa;
import br.com.emanuelvictor.iguassu.web.entity.SituacaoVaga;
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

	@Column(length = 20000)
	private String observacoes;

	@Column(length = 50, nullable = false)
	private SituacaoVaga situacao;

	@Column(nullable = false, length = 100)
	private Double salario;

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

    public SituacaoVaga getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoVaga situacao) {
        this.situacao = situacao;
    }

    public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Calendar getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Calendar dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

}
