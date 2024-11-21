package lp.jpa.adocao.comuns;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class ContatoJpa {

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    // Construtor padrão necessário para JPA
    public ContatoJpa() {
    }

    public ContatoJpa(String email, String telefone) {
        this.email = email;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
