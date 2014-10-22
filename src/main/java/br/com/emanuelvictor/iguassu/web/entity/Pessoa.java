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

    @Column(length = 20)
    protected String telefoneResidencial;

    @Column(length = 20)
    protected String telefoneComercial;

    @Column(length = 20)
    protected String telefoneCelular;

    @Column(length = 50)
    protected String email;

	@Column(nullable = false, length = 100)
	protected String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}