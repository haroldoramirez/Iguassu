package br.com.emanuelvictor.iguassu.web.entity.schooling;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.emanuelvictor.iguassu.web.entity.base.SpringData;

@Entity
public class CategoriaCurso extends SpringData<Long> {

	// Esse é o CRUD de CategoriaCurso, cada curso se enquadra em uma cateogria,
	// se é
	// ensino médio, superior, fundamental, profissionalizante, técnico,
	// técnólogo e etc.

	private static final long serialVersionUID = 2783191947729894654L;

	@Column(nullable = false, length = 20, unique = true)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
