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
import lp.voluntariado.dominio.voluntario.Voluntario;
import lp.voluntariado.dominio.voluntario.IVoluntarioRepositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class repositorioGenerico implements IAbrigoRepositorio, IAnimalRespositorio, IPessoaRepositorio, IVoluntarioRepositorio {

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
        return List.copyOf(abrigos.values());
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

    private Map<IdPessoa, Voluntario> voluntarios = new HashMap<>();

    @Override
    public void salvar(Voluntario voluntario) {
        voluntarios.put(voluntario.getIdPessoa(), voluntario);
    }

    @Override
    public void editar(Voluntario voluntario) {
        voluntarios.replace(voluntario.getIdPessoa(), voluntario);
    }

    @Override
    public Voluntario obterVoluntarioPorId(IdPessoa idVoluntario) {
        return voluntarios.get(idVoluntario);
    }

    @Override
    public List listarVoluntarios() {
        return List.copyOf(voluntarios.values());
    }

    @Override
    public Pessoa obterPorId(IdPessoa idPessoa) {
        return pessoas.get(idPessoa);
    }

    @Override
    public Pessoa buscarPorCPF(String cpf) {
        return pessoas.values().stream().filter(pessoa -> pessoa.getCpf().equals(cpf)).findFirst().orElse(null);
    }

    @Override
    public Abrigo obterPorNome(String nomeAbrigo) {
        return abrigos.values().stream().filter(abrigo -> abrigo.getNomeAbrigo().equals(nomeAbrigo)).findFirst().orElse(null);
    }


}
