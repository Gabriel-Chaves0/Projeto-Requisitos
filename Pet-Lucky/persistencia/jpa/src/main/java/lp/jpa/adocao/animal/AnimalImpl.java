package lp.jpa.adocao.animal;

import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IAnimalRespositorio;
import lp.adocao.dominio.animal.IdAnimal;
import lp.jpa.adocao.JpaMapeador;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalImpl implements IAnimalRespositorio {

    @Autowired
    private AnimalJpaRepositorio repositorio;

    @Autowired
    JpaMapeador mapeador = JpaMapeador.getInstance();

    @Override
    public void salvar(Animal animal) {
        var animalJpa = mapeador.map(animal, AnimalJpa.class);
        repositorio.save(animalJpa);
    }

    @Override
    public Animal obterPorId(IdAnimal id) {
        var animalJpa = repositorio.findById(id.getId());
        return mapeador.map(animalJpa, Animal.class);
    }

    @Override
    public void remover(IdAnimal id) {
        repositorio.deleteById(id.getId());
    }

    @Override
    public void editar(Animal animal) {
        var animalJpa = mapeador.map(animal, AnimalJpa.class);
        repositorio.save(animalJpa);
    }

    @Override
    public List<Animal> listarAnimais() {
        var animaisJpa = repositorio.findAll();
        return mapeador.map(animaisJpa, new TypeToken<List<Animal>>() {
        }.getType());
    }
}
