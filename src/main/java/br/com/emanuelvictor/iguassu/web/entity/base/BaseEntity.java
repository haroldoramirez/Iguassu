package br.com.emanuelvictor.iguassu.web.entity.base;

import br.com.emanuelvictor.iguassu.web.entity.address.Bairro;
import br.com.emanuelvictor.iguassu.web.entity.address.Endereco;

import javax.persistence.*;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class BaseEntity extends SpringData<Long> {

	private static final long serialVersionUID = -1161960291273622919L;

    @Embedded
    protected Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}