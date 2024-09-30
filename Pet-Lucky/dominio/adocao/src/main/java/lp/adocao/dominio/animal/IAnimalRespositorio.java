package lp.adocao.dominio.animal;

public interface IAnimalRespositorio {
    void salvar(Animal animal);
    Animal obterPorId(IdAnimal id);
    void remover(IdAnimal id);
    void editar(Animal animal);
}
