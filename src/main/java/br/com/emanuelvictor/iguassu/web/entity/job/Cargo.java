package br.com.emanuelvictor.iguassu.web.entity.job;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

import br.com.emanuelvictor.iguassu.web.entity.base.SpringData;

@Entity
public class Cargo extends SpringData<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5183749530891212557L;

	@Column(nullable = false, length = 30, unique = true)
	private String nome;

	@Column(length = 100)
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(@NotBlank String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(@NotBlank String descricao) {
		this.descricao = descricao;
	}

}
