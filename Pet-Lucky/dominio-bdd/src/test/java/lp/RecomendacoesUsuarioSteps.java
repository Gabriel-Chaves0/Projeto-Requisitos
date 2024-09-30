package lp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IdAnimal;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.adocao.dominio.pessoa.Pessoa;
import lp.repositorioGenerico;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecomendacoesUsuarioSteps {

    private repositorioGenerico repositorio = new repositorioGenerico();
    private List<Animal> animaisRecomendados = new ArrayList<>();
    private Pessoa usuario;

    @Given("o usuário {string} tem as seguintes preferências:")
    public void oUsuarioTemAsSeguintesPreferencias(String nomeUsuario, DataTable dataTable) {
        Map<String, String> preferencias = dataTable.asMap(String.class, String.class);
        usuario = new Pessoa(null, null, null, nomeUsuario, "123.456.789-00", null, null);
        repositorio.salvar(usuario); // Salva o usuário no repositório
    }

    @Given("existem os seguintes animais disponíveis:")
    public void existemOsSeguintesAnimaisDisponiveis(DataTable dataTable) {
        List<Map<String, String>> animais = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dadosAnimal : animais) {
            Animal animal = new Animal(
                    new IdAnimal(1),
                    new IdAbrigo(1),
                    null,
                    null,
                    dadosAnimal.get("Nome"),
                    "2 anos",
                    dadosAnimal.get("Especie"),
                    dadosAnimal.get("Raca"),
                    dadosAnimal.get("Porte"),
                    "Macho"
            );
            repositorio.salvar(animal); // Salva o animal no repositório
        }
    }

    @When("o usuário {string} acessa as recomendações")
    public void oUsuarioAcessaAsRecomendacoes(String nomeUsuario) {
        List<Animal> animaisDisponiveis = List.copyOf(repositorio.animais.values());
        // Exemplo de filtro de recomendação por espécie "Cachorro"
        animaisRecomendados = animaisDisponiveis.stream()
                .filter(animal -> animal.getEspecie().equalsIgnoreCase("Cachorro"))
                .collect(Collectors.toList());
    }

    @Then("o sistema deve recomendar os seguintes animais:")
    public void oSistemaDeveRecomendarOsSeguintesAnimais(DataTable dataTable) {
        List<String> nomesEsperados = dataTable.asList(String.class);
        List<String> nomesRecomendados = animaisRecomendados.stream()
                .map(Animal::getNomeAnimal)
                .collect(Collectors.toList());
        Assert.assertTrue(nomesRecomendados.containsAll(nomesEsperados));
    }

    @Then("o sistema não deve recomendar os seguintes animais:")
    public void oSistemaNaoDeveRecomendarOsSeguintesAnimais(DataTable dataTable) {
        List<String> nomesNaoEsperados = dataTable.asList(String.class);
        List<String> nomesRecomendados = animaisRecomendados.stream()
                .map(Animal::getNomeAnimal)
                .collect(Collectors.toList());
        for (String nome : nomesNaoEsperados) {
            Assert.assertFalse(nomesRecomendados.contains(nome));
        }
    }
}
