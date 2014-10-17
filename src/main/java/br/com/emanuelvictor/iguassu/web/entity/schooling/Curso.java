package br.com.emanuelvictor.iguassu.web.entity.schooling;

import javax.persistence.*;

import br.com.emanuelvictor.iguassu.web.entity.base.SpringData;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome" , "CategoriaCurso_id"})
})
public class Curso extends SpringData<Long> {

	// Esse é o CRUD de curso, cada curso se enquadra em uma cateogria, se é
	// ensino médio, superior, fundamental, profissionalizante, técnico,
	// técnólogo e etc.

	private static final long serialVersionUID = 2783191947729894654L;

	// Nome
	@Column(nullable = false, length = 50)
	private String nome;

	@ManyToOne(optional = false)
	private CategoriaCurso categoriaCurso;

	@Column(length = 1000)
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CategoriaCurso getCategoriaCurso() {
		return categoriaCurso;
	}

	public void setCategoriaCurso(CategoriaCurso categoriaCurso) {
		this.categoriaCurso = categoriaCurso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
