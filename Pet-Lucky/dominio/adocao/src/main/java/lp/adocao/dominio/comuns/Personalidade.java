package lp.adocao.dominio.comuns;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.List;

@ValueObject
public class Personalidade {
    private final List<Boolean> tagsPersonalidade;

    public Personalidade(List<Boolean> tagsPersonalidade) {
        this.tagsPersonalidade = tagsPersonalidade;
    }

    public List<Boolean> getTagsPersonalidade() {
        return tagsPersonalidade;
    }
}
