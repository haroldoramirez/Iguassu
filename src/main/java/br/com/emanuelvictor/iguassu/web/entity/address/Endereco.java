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

    private String getLocale(){
        String str = new String();
        if (bairro!=null){
            str =   "\n " + bairro.getNome()
                    + ", " + bairro.getCidade().getNome()
                    + ", " + bairro.getCidade().getEstado().getSigla()
                    + ", " + bairro.getCidade().getEstado().getPais().getNome();
        }
        return str;
    }
    @Override
    public String toString() {

        String str = new String();
        if (rua!=null&&rua.trim()!=""){
            str = str + "\n Rua " + rua;
        }
        if (numero!=null&&numero.trim()!=""){
            str = str + "\n nº " + numero;
        }
        if(cep!=null&&cep.trim()!=""){
            str = str + "\n CEP " + cep;
        }
        if (complemento!=null&&complemento.trim()!=""){
            str = str + "\n " + complemento;
        }
        return  str + getLocale();
    }
}
