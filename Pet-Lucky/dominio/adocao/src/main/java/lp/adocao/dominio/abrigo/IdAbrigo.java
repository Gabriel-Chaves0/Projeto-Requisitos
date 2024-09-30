package lp.adocao.dominio.abrigo;

import static org.apache.commons.lang3.Validate.isTrue;

import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;

import java.util.Objects;

@ValueObject
public class IdAbrigo implements Identifier {
    private final int id;

    public IdAbrigo(int id) {
        isTrue(id > 0, "Id deve ser maior que zero");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdAbrigo idAbrigo = (IdAbrigo) o;

        return id == idAbrigo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
