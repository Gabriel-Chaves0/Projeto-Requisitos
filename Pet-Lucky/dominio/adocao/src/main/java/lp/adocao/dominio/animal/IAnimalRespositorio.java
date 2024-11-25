package lp.adocao.dominio.animal;

import java.util.List;

public interface IAnimalRespositorio {
    void salvar(Animal animal);
    Animal obterPorId(IdAnimal id);
    void remover(IdAnimal id);
    void editar(Animal animal);
    List<Animal> listarAnimais();
}
