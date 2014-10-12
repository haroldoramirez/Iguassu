package br.com.emanuelvictor.iguassu.web.entity.schooling;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.com.emanuelvictor.iguassu.web.entity.Candidato;
import br.com.emanuelvictor.iguassu.web.entity.Empresa;
import br.com.emanuelvictor.iguassu.web.entity.base.SpringData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CandidatoCurso extends SpringData<Long> {

	private static final long serialVersionUID = 494223559648070114L;

	@Column(length = 20, nullable = false)
	private String situacaoDoCurso;

	@Column(length = 500)
	private String outrasInformacoes;
	@NotNull(message = "Você deve inserir quantos períodos o candidato já cursou")
	@Column(nullable = false)
	private Integer periodosConcluidos;
	@NotNull(message = "Você deve inserir um regime")
	@Column(nullable = false, length = 30)
	private String regime;

	@NotNull(message = "Você deve inserir uma quantidade total de períodos")
	@Column(nullable = false)
	private Integer quantidadeDePeriodos;

	@ManyToOne(optional = false)
	private Curso curso;

    //GAMBIA
//    @JsonIgnore
    @ManyToOne(optional = false)
//    @JsonBackReference
	private Candidato candidato;


    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

	@ManyToOne(optional = true)
	private Empresa instituicaoDeEnsino;

	public String getOutrasInformacoes() {
		return outrasInformacoes;
	}

	public void setOutrasInformacoes(String outrasInformacoes) {
		this.outrasInformacoes = outrasInformacoes;
	}

	public String getRegime() {
		return regime;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}

	public Integer getQuantidadeDePeriodos() {
		return quantidadeDePeriodos;
	}

	public void setQuantidadeDePeriodos(Integer quantidadeDePeriodos) {
		this.quantidadeDePeriodos = quantidadeDePeriodos;
	}

	public String getSituacaoDoCurso() {
		return situacaoDoCurso;
	}

	public void setSituacaoDoCurso(String situacaoDoCurso) {
		this.situacaoDoCurso = situacaoDoCurso;
	}

	public Integer getPeriodosConcluidos() {
		return periodosConcluidos;
	}

	public void setPeriodosConcluidos(Integer periodosConcluidos) {
		this.periodosConcluidos = periodosConcluidos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Empresa getInstituicaoDeEnsino() {
		return instituicaoDeEnsino;
	}

	public void setInstituicaoDeEnsino(Empresa instituicaoDeEnsino) {
		this.instituicaoDeEnsino = instituicaoDeEnsino;
	}

}
