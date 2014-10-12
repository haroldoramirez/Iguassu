package br.com.emanuelvictor.iguassu.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Empresa extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6267143079274386878L;

	@Column(length = 20, unique = true, nullable = true)
	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


    @Column(length = 20)
    protected String telefoneResidencial;

    @Column(length = 20)
    protected String telefoneComercial;

    @Column(length = 20)
    protected String telefoneCelular;

    @Column(length = 50)
    protected String email;

}
