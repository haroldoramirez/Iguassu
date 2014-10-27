package br.com.emanuelvictor.iguassu.web.entity.base;

import br.com.emanuelvictor.iguassu.web.entity.Genero;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by emanuel on 27/10/14.
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class PessoaFisica extends Pessoa {

    private static final long serialVersionUID = 6598446511490737297L;

    @Column(length = 20)
    protected String telefoneCelular;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    protected Genero genero;

    // @CPF
    @Column(length = 15, unique = true, nullable = true)
    protected String cpf;

    @Column(length = 30)
    protected String rg;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    protected Calendar dataNasc;

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Calendar getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Calendar dataNasc) {
        this.dataNasc = dataNasc;
    }
}
