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

    @Column(nullable = false, length = 100)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataDePagamento;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar dataDeVencimento;

	@Column(nullable = false)
	private Boolean pago = false;

	@Column(nullable = false)
	private Float valor;
	
	@Column(nullable = false)
	private Boolean entrada = false;

	@ManyToOne(optional = true)
	private Pessoa pessoa;

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

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Boolean getEntrada() {
		return entrada;
	}

	public void setEntrada(Boolean entrada) {
		this.entrada = entrada;
	}

	
}