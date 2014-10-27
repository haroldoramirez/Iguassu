package br.com.emanuelvictor.iguassu.web.entity;

import java.util.Calendar;

import javax.persistence.*;

import br.com.emanuelvictor.iguassu.web.entity.address.Pais;
import br.com.emanuelvictor.iguassu.web.entity.base.Pessoa;
import br.com.emanuelvictor.iguassu.web.entity.base.PessoaFisica;

@Entity
public class Candidato extends PessoaFisica {

	private static final long serialVersionUID = 699441248742650338L;
	private String pathFoto;


    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private NecessidadeEspecial necessidadeEspecial;


    @Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SituacaoDoCandidato situacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private EstadoCivil estadoCivil;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
	private Pais nacionalidade;

    @Column(nullable = true, length = 200)
    private String observacoes;

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}


    public SituacaoDoCandidato getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoDoCandidato situacao) {
        this.situacao = situacao;
    }

    public Pais getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Pais nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

    public NecessidadeEspecial getNecessidadeEspecial() {
        return necessidadeEspecial;
    }

    public void setNecessidadeEspecial(NecessidadeEspecial necessidadeEspecial) {
        this.necessidadeEspecial = necessidadeEspecial;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

}