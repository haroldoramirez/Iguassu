package br.com.emanuelvictor.iguassu.web.entity.address;

import javax.persistence.*;

import br.com.emanuelvictor.iguassu.web.entity.base.SpringData;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome" , "estado_id"})
})
public class Cidade extends SpringData<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6980683787673905850L;

	@Column(nullable = false, length = 30)
	private String nome;

	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private Estado estado;

	public Cidade() {
		super();
	}

	public Cidade(String nome, Estado estado) {
		super();
		this.nome = nome;
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	

}