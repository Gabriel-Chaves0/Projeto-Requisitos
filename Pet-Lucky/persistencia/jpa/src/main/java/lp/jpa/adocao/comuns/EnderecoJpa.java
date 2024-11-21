package lp.jpa.adocao.comuns;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class EnderecoJpa {

    @Column(name = "rua", nullable = false)
    private String rua;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    // Construtor padrão necessário para JPA
    public EnderecoJpa() {
    }

    public EnderecoJpa(String rua, String cidade) {
        this.rua = rua;
        this.cidade = cidade;
    }

    // Getters e Setters
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
