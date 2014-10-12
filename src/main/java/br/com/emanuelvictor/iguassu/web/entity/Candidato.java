package br.com.emanuelvictor.iguassu.web.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import br.com.emanuelvictor.iguassu.web.entity.address.Pais;
import br.com.emanuelvictor.iguassu.web.entity.job.Experiencia;
import br.com.emanuelvictor.iguassu.web.entity.schooling.CandidatoCurso;

@Entity
public class Candidato extends Pessoa {

	private static final long serialVersionUID = 699441248742650338L;
	private String pathFoto;
	// @CPF
	@Column(length = 15, unique = true, nullable = true)
	private String cpf;
	@Column(length = 30)
	private String rg;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dataNasc;


    @Column(length = 20)
    protected String telefoneResidencial;

    @Column(length = 20)
    protected String telefoneComercial;

    @Column(length = 20)
    protected String telefoneCelular;

    @Column(length = 50)
    protected String email;

//    @JsonManagedReference

//    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "candidato")
//    private List<Experiencia> experiencias;
//
////    @JsonManagedReference
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "candidato")
//    private List<CandidatoCurso> cursos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private NecessidadeEspecial necessidadeEspecial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SituacaoDoCandidato situacao;

    @ManyToOne(optional = true)
	private Pais nacionalidade;

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = true, length = 30)
	private String estadoCivil;

	@Column(nullable = true, length = 200)
	private String observacoes;

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Calendar getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Calendar dataNasc) {
		this.dataNasc = dataNasc;
	}

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
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

    public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

}