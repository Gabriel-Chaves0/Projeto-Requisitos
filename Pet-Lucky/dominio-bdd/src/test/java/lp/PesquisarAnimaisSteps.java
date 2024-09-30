package lp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IdAnimal;
import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.adocao.dominio.comuns.Endereco;
import lp.repositorioGenerico;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PesquisarAnimaisSteps {

    private repositorioGenerico repositorio = new repositorioGenerico();
    private List<Animal> resultadoPesquisa;

    @Given("existem animais disponíveis para adoção")
    public void existemAnimaisDisponiveisParaAdocao() {
        // Criando abrigos manualmente
        IdAbrigo idAbrigoSaoPaulo = new IdAbrigo(1);
        IdAbrigo idAbrigoRio = new IdAbrigo(2);

        Endereco enderecoSaoPaulo = new Endereco("Rua 1", "São Paulo");
        Endereco enderecoRio = new Endereco("Rua 2", "Rio de Janeiro");

        Abrigo abrigoSaoPaulo = new Abrigo(idAbrigoSaoPaulo, "Abrigo São Paulo", enderecoSaoPaulo, null, 50);
        Abrigo abrigoRio = new Abrigo(idAbrigoRio, "Abrigo Rio de Janeiro", enderecoRio, null, 50);

        // Salvando abrigos no repositório
        repositorio.salvar(abrigoSaoPaulo);
        repositorio.salvar(abrigoRio);

        // Criando animais manualmente
        Animal rex = new Animal(new IdAnimal(1), idAbrigoSaoPaulo, null, null, "Rex", "2 anos", "Cachorro", "Labrador", "Grande", "Macho");
        Animal mia = new Animal(new IdAnimal(2), idAbrigoRio, null, null, "Mia", "3 anos", "Gato", "Siamês", "Pequeno", "Fêmea");

        // Salvando animais no repositório
        repositorio.salvar(rex);
        repositorio.salvar(mia);
    }

    @When("o usuário pesquisa por {string} em {string}")
    public void oUsuarioPesquisaPorEm(String especie, String localizacao) {
        // Recuperando todos os animais disponíveis
        List<Animal> animaisDisponiveis = List.copyOf(repositorio.animais.values());

        // Filtrando os animais pela espécie e pela cidade do abrigo
        resultadoPesquisa = animaisDisponiveis.stream()
                .filter(animal -> animal.getEspecie().equalsIgnoreCase(especie))
                .filter(animal -> {
                    Abrigo abrigo = repositorio.obterPorId(animal.getIdAbrigo());
                    return abrigo.getEnderecoAbrigo().getCidade().equalsIgnoreCase(localizacao);
                })
                .collect(Collectors.toList());
    }

    @Then("o sistema deve exibir o animal {string}")
    public void oSistemaDeveExibirOAnimal(String nomeAnimalEsperado) {
        List<String> nomesResultados = resultadoPesquisa.stream()
                .map(Animal::getNomeAnimal)
                .collect(Collectors.toList());
        Assert.assertTrue(nomesResultados.contains(nomeAnimalEsperado));
    }

    @Then("o sistema não deve exibir o animal {string}")
    public void oSistemaNaoDeveExibirOAnimal(String nomeAnimalNaoEsperado) {
        List<String> nomesResultados = resultadoPesquisa.stream()
                .map(Animal::getNomeAnimal)
                .collect(Collectors.toList());
        Assert.assertFalse(nomesResultados.contains(nomeAnimalNaoEsperado));
    }
}
