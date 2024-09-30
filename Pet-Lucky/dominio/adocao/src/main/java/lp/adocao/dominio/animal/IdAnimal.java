package lp.adocao.dominio.animal;

import org.jmolecules.ddd.annotation.ValueObject;

import static org.apache.commons.lang3.Validate.isTrue;

@ValueObject
public class IdAnimal {
    private final int id;

    public IdAnimal(int id) {
        isTrue(id > 0, "Id deve ser maior que zero");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof IdAnimal) {
            var animalId = (IdAnimal) o;
            return id == animalId.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
