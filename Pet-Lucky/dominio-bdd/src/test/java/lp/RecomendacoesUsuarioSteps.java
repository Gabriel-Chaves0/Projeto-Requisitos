package lp;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecomendacoesUsuarioSteps {

    private List<Animal> animaisDisponiveis = new ArrayList<>();
    private List<Animal> animaisRecomendados = new ArrayList<>();
    private Usuario usuario;

    @Given("o usuário {string} tem as seguintes preferências:")
    public void oUsuarioTemAsSeguintesPreferencias(String nomeUsuario, DataTable dataTable) {
        Map<String, String> preferencias = dataTable.asMap(String.class, String.class);
        usuario = new Usuario(nomeUsuario, preferencias);
    }

    @Given("existem os seguintes animais disponíveis:")
    public void existemOsSeguintesAnimaisDisponiveis(DataTable dataTable) {
        List<Map<String, String>> animais = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dadosAnimal : animais) {
            Animal animal = new Animal(
                    dadosAnimal.get("Nome"),
                    dadosAnimal.get("Espécie"),
                    dadosAnimal.get("Raça"),
                    dadosAnimal.get("Porte")
            );
            animaisDisponiveis.add(animal);
        }
    }

    @When("o usuário {string} acessa as recomendações")
    public void oUsuarioAcessaAsRecomendacoes(String nomeUsuario) {
        Map<String, String> prefs = usuario.getPreferencias();
        animaisRecomendados = animaisDisponiveis.stream()
                .filter(animal ->
                        animal.getEspecie().equalsIgnoreCase(prefs.get("Espécie")) &&
                                animal.getRaca().equalsIgnoreCase(prefs.get("Raça")) &&
                                animal.getPorte().equalsIgnoreCase(prefs.get("Porte"))
                ).collect(Collectors.toList());
    }

    @Then("o sistema deve recomendar os seguintes animais:")
    public void oSistemaDeveRecomendarOsSeguintesAnimais(DataTable dataTable) {
        List<String> nomesEsperados = dataTable.asList(String.class);
        List<String> nomesRecomendados = animaisRecomendados.stream()
                .map(Animal::getNome)
                .collect(Collectors.toList());
        Assert.assertTrue(nomesRecomendados.containsAll(nomesEsperados));
    }

    @Then("o sistema não deve recomendar os seguintes animais:")
    public void oSistemaNaoDeveRecomendarOsSeguintesAnimais(DataTable dataTable) {
        List<String> nomesNaoEsperados = dataTable.asList(String.class);
        List<String> nomesRecomendados = animaisRecomendados.stream()
                .map(Animal::getNome)
                .collect(Collectors.toList());
        for (String nome : nomesNaoEsperados) {
            Assert.assertFalse(nomesRecomendados.contains(nome));
        }
    }
}
