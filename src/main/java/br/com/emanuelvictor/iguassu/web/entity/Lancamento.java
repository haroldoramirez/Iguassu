package br.com.emanuelvictor.iguassu.web.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.emanuelvictor.iguassu.web.entity.base.Pessoa;
import br.com.emanuelvictor.iguassu.web.entity.base.SpringData;

@Entity
public class Lancamento extends SpringData<Long> {


	private static final long serialVersionUID = -2120199211795566673L;

    @Column(nullable = false, length = 2000)
	private String descricao;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar dataDeCadastro = Calendar.getInstance();

    @Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Calendar dataDePagamento;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dataDeVencimento;

	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false)
	private TipoLancamento tipoLancamento = TipoLancamento.ENTRADA;

	@ManyToOne(optional = true)
	private Pessoa pessoa;

    @ManyToOne(optional = false)
    private Usuario usuario;

    public Calendar getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Calendar dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDataDePagamento() {
        return dataDePagamento;
	}

	public void setDataDePagamento(Calendar dataDePagamento) {
		this.dataDePagamento = dataDePagamento;
	}

	public Calendar getDataDeVencimento() {
		return dataDeVencimento;
	}

	public void setDataDeVencimento(Calendar dataDeVencimento) {
		this.dataDeVencimento = dataDeVencimento;
	}

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }
}