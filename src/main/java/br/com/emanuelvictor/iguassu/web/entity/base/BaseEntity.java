package br.com.emanuelvictor.iguassu.web.entity.base;

import br.com.emanuelvictor.iguassu.web.entity.address.Bairro;

import javax.persistence.*;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class BaseEntity extends SpringData<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1161960291273622919L;


    @ManyToOne(/* cascade = CascadeType.DETACH, */optional = true)
    protected Bairro bairro;

    @Column(length = 50)
    protected String rua;

    @Column(length = 8)
    protected String numero;

    @Column(length = 8)
    protected String cep;

    @Column(length = 50)
    protected String complemento;




    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}