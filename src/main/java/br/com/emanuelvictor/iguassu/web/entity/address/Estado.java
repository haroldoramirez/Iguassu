package br.com.emanuelvictor.iguassu.web.entity.address;

import javax.persistence.*;

import br.com.emanuelvictor.iguassu.web.entity.base.SpringData;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome" , "pais_id"})
})
public class Estado extends SpringData<Long> {

	private static final long serialVersionUID = -8453641720904222519L;

	@Column(nullable = false, length = 30)
	private String nome;

    @Column(length = 2)
    private String sigla;

	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private Pais pais;

	public Estado() {
		super();
	}

	public Estado(String nome, Pais pais) {
		super();
		this.nome = nome;
		this.pais = pais;
	}

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pais getPais() {
        if (pais==null){
            pais = new Pais();
        }
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}