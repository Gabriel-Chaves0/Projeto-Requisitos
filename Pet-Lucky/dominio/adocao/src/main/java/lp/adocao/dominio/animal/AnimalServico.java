package lp.adocao.dominio.animal;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class AnimalServico {
    private final AnimalRespositorio animalRespositorio;

    public AnimalServico(AnimalRespositorio animalRespositorio) {
        this.animalRespositorio = animalRespositorio;
    }

    public void salvar(Animal animal) {
        notNull(animal, "Animal não pode ser nulo");
        animalRespositorio.salvar(animal);
    }

    public void editar(Animal animal) {
        notNull(animal, "Animal não pode ser nulo");
        animalRespositorio.editar(animal);
    }

    public Animal obterPorId(IdAnimal id) {
        notNull(id, "Id da pessoa não pode ser nulo");
        notEmpty(id.toString(), "Id da pessoa não pode ser vazio");

        return animalRespositorio.obterPorId(id);
    }

    public void remover(IdAnimal id) {
        notNull(id, "Id da pessoa não pode ser nulo");
        notEmpty(id.toString(), "Id da pessoa não pode ser vazio");

        animalRespositorio.remover(id);
    }
}
