package lp.adocao.dominio.comuns;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public class Contato {
    private final String email;
    private final String telefone;

    public Contato(String email, String telefone) {
        this.email = email;
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
}
