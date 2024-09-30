package lp;
import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IAbrigoRepositorio;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IAnimalRespositorio;
import lp.adocao.dominio.animal.IdAnimal;
import lp.adocao.dominio.pessoa.IPessoaRepositorio;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.pessoa.Pessoa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class repositorioGenerico implements IAbrigoRepositorio, IAnimalRespositorio, IPessoaRepositorio {

    private Map<IdAbrigo, Abrigo> abrigos = new HashMap<>();

    @Override
    public void salvar(Abrigo abrigo) {
        abrigos.put(abrigo.getIdAbrigo(), abrigo);
    }

    @Override
    public void editar(Abrigo abrigo) {
        abrigos.replace(abrigo.getIdAbrigo(), abrigo);
    }

    @Override
    public void remover(IdAbrigo idAbrigo) {
        abrigos.remove(idAbrigo);
    }

    @Override
    public Abrigo obterPorId(IdAbrigo idAbrigo) {
        return abrigos.get(idAbrigo);
    }


    @Override
    public List<Abrigo> listarAbrigos() {
        return List.of();
    }

    Map<IdAnimal, Animal> animais = new HashMap<>();

    @Override
    public void salvar(Animal animal) {
        animais.put(animal.getIdAnimal(), animal);
    }

    @Override
    public Animal obterPorId(IdAnimal id) {
        return animais.get(id);
    }

    @Override
    public void remover(IdAnimal id) {
        animais.remove(id);
    }

    @Override
    public void editar(Animal animal) {
        animais.replace(animal.getIdAnimal(), animal);
    }

    private Map<IdPessoa, Pessoa> pessoas = new HashMap<>();

    @Override
    public void salvar(Pessoa pessoa) {
        pessoas.put(pessoa.getIdPessoa(), pessoa);
    }

    @Override
    public void editar(Pessoa pessoa) {
        pessoas.replace(pessoa.getIdPessoa(), pessoa);
    }

    @Override
    public Pessoa obterPorId(IdPessoa idPessoa) {
        return pessoas.get(idPessoa);
    }
}
