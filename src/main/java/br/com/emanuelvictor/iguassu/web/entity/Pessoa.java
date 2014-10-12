package br.com.emanuelvictor.iguassu.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.com.emanuelvictor.iguassu.web.entity.base.BaseEntity;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Pessoa extends BaseEntity {

	private static final long serialVersionUID = 6598446511490737297L;

	@Column(nullable = false, length = 100)
	protected String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}