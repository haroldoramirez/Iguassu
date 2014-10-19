package br.com.emanuelvictor.iguassu.web.entity.address;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Endereco{

    @ManyToOne(/* cascade = CascadeType.DETACH, */optional = true)
    private Bairro bairro;

    @Column(length = 50)
    private String rua;

    @Column(length = 8)
    private String numero;

    @Column(length = 15)
    private String cep;

    @Column(length = 50)
    private String complemento;


}
