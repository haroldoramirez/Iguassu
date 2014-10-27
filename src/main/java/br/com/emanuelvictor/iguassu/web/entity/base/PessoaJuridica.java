package br.com.emanuelvictor.iguassu.web.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by emanuel on 27/10/14.
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class PessoaJuridica extends Pessoa {

    private static final long serialVersionUID = 6598446511490737297L;

    @Column(length = 20, unique = true, nullable = true)
    protected String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
