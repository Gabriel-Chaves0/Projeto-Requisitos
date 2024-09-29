package lp;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostrarAnimaisAdotadosSteps {

    private List<AnimalAdotado> animaisAdotados = new ArrayList<>();
    private List<AnimalAdotado> animaisExibidos = new ArrayList<>();
    private String mensagemSistema;

    @Given("os seguintes animais foram adotados recentemente:")
    public void osSeguintesAnimaisForamAdotadosRecentemente(DataTable dataTable) {
        List<Map<String, String>> animais = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dados : animais) {
            AnimalAdotado animal = new AnimalAdotado(
                    dados.get("Nome"),
                    dados.get("Espécie"),
                    dados.get("Adotante")
            );
            animaisAdotados.add(animal);
        }
    }

    @Given("nenhum animal foi adotado recentemente")
    public void nenhumAnimalFoiAdotadoRecentemente() {
        animaisAdotados.clear();
    }

    @When("o usuário acessa a página de animais adotados")
    public void oUsuarioAcessaAPaginaDeAnimaisAdotados() {
        if (animaisAdotados.isEmpty()) {
            mensagemSistema = "Nenhum animal foi adotado recentemente";
        } else {
            animaisExibidos.addAll(animaisAdotados);
        }
    }

    @Then("o sistema deve exibir os seguintes animais adotados:")
    public void oSistemaDeveExibirOsSeguintesAnimaisAdotados(DataTable dataTable) {
        List<Map<String, String>> animaisEsperados = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> esperado : animaisEsperados) {
            boolean encontrado = animaisExibidos.stream().anyMatch(animal ->
                    animal.getNome().equals(esperado.get("Nome")) &&
                            animal.getAdotante().equals(esperado.get("Adotante"))
            );
            Assert.assertTrue(encontrado);
        }
    }

    @Then("o sistema deve exibir a mensagem {string}")
    public void oSistemaDeveExibirAMensagem(String mensagemEsperada) {
        Assert.assertEquals(mensagemEsperada, mensagemSistema);
    }
}
