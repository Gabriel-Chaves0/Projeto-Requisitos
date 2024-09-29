package lp.adocao.dominio.comuns;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public class Endereco {
    private final String rua;
    private final String cidade;

    public Endereco(String rua, String cidade) {
        this.rua = rua;
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public String getCidade() {
        return cidade;
    }
}
