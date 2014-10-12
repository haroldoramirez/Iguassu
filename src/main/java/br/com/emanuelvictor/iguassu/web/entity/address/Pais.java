package br.com.emanuelvictor.iguassu.web.entity.address;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.emanuelvictor.iguassu.web.entity.base.SpringData;

@Entity
public class Pais extends SpringData<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -421522803783661954L;
	@Column(nullable = false, length = 30, unique = true)
	private String nome;

	public Pais(String nome) {
		super();
		this.nome = nome;
	}

	public Pais() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}