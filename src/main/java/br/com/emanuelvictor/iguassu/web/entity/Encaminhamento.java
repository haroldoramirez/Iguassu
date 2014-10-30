package br.com.emanuelvictor.iguassu.web.entity;

import java.util.Calendar;

import javax.persistence.*;

import br.com.emanuelvictor.iguassu.web.entity.base.SpringData;
import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"candidato_id" , "vaga_id"})
})
public class Encaminhamento extends SpringData<Long> {

	private static final long serialVersionUID = 3509884558935107381L;
	// @OneToOne(cascade = CascadeType.ALL, optional = false)
	// private Usuario usuario;

	@ManyToOne(optional = false)
	private Vaga vaga;

	@ManyToOne(optional = false)
	private Candidato candidato;

    @ManyToOne(optional = false)
    private Usuario usuario;

    @ManyToOne(optional = true)
    private Lancamento lancamento;

	@Column(length = 100, nullable = false)
	private SituacaoEncaminhamento situacao;

	@Column(length = 100)
	private String observacoes;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar dataDeCadastro = Calendar.getInstance();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

    public SituacaoEncaminhamento getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEncaminhamento situacao) {
        this.situacao = situacao;
    }

    public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Calendar getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Calendar dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

}
