package lp;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PesquisarAnimaisSteps {

    private List<Animal> animaisDisponiveis = new ArrayList<>();
    private List<Animal> resultadoPesquisa = new ArrayList<>();

    @Given("existem os seguintes animais disponíveis para adoção:")
    public void existemOsSeguintesAnimaisDisponiveisParaAdocao(DataTable dataTable) {
        List<Map<String, String>> animais = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dadosAnimal : animais) {
            Animal animal = new Animal(
                    dadosAnimal.get("Nome"),
                    dadosAnimal.get("Espécie"),
                    dadosAnimal.get("Raça"),
                    dadosAnimal.get("Porte"),
                    dadosAnimal.get("Localização")
            );
            animaisDisponiveis.add(animal);
        }
    }

    @When("o usuário pesquisa por {string} em {string}")
    public void oUsuarioPesquisaPorEm(String especie, String localizacao) {
        resultadoPesquisa = animaisDisponiveis.stream()
                .filter(animal -> animal.getEspecie().equalsIgnoreCase(especie)
                        && animal.getLocalizacao().equalsIgnoreCase(localizacao))
                .collect(Collectors.toList());
    }

    @When("o usuário pesquisa por {string} da raça {string} de porte {string} em {string}")
    public void oUsuarioPesquisaPorMultiplosFiltros(String especie, String raca, String porte, String localizacao) {
        resultadoPesquisa = animaisDisponiveis.stream()
                .filter(animal -> animal.getEspecie().equalsIgnoreCase(especie)
                        && animal.getRaca().equalsIgnoreCase(raca)
                        && animal.getPorte().equalsIgnoreCase(porte)
                        && animal.getLocalizacao().equalsIgnoreCase(localizacao))
                .collect(Collectors.toList());
    }

    @Then("o sistema deve exibir os seguintes animais:")
    public void oSistemaDeveExibirOsSeguintesAnimais(DataTable dataTable) {
        List<String> nomesEsperados = dataTable.asList(String.class);
        List<String> nomesResultados = resultadoPesquisa.stream()
                .map(Animal::getNome)
                .collect(Collectors.toList());
        Assert.assertTrue(nomesResultados.containsAll(nomesEsperados));
    }

    @Then("o sistema não deve exibir os seguintes animais:")
    public void oSistemaNaoDeveExibirOsSeguintesAnimais(DataTable dataTable) {
        List<String> nomesNaoEsperados = dataTable.asList(String.class);
        List<String> nomesResultados = resultadoPesquisa.stream()
                .map(Animal::getNome)
                .collect(Collectors.toList());
        for (String nome : nomesNaoEsperados) {
            Assert.assertFalse(nomesResultados.contains(nome));
        }
    }
}
