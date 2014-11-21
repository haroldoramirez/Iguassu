package br.com.emanuelvictor.iguassu.web.entity.address;

import javax.persistence.*;

import br.com.emanuelvictor.iguassu.web.entity.base.SpringData;


@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome" , "cidade_id"})
})
public class Bairro extends SpringData<Long> {


	private static final long serialVersionUID = -1238093511306157850L;

	@Column(nullable = false, length = 30)
	private String nome;

	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private Cidade cidade;

	public Bairro(String nome, Cidade cidade) {
		super();
		this.nome = nome;
		this.cidade = cidade;
	}



	public Bairro() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cidade getCidade() {
        if (cidade==null){
            cidade = new Cidade();
        }
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}