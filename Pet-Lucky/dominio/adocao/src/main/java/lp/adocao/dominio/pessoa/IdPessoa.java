package lp.adocao.dominio.pessoa;

import static org.apache.commons.lang3.Validate.isTrue;

import org.jmolecules.ddd.annotation.ValueObject;
import org.jmolecules.ddd.types.Identifier;

import java.util.Objects;

@ValueObject
public class IdPessoa implements Identifier {
    private final int id;

    public IdPessoa(int id) {
        isTrue(id > 0, "Id deve ser maior que zero");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof IdPessoa) {
            var autorId = (IdPessoa) o;
            return id == autorId.id;
        }
        return false;
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
