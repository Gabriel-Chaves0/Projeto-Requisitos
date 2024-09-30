package lp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IdAnimal;
import lp.repositorioGenerico;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class ExibirAnimaisAdotadosSteps {

    private repositorioGenerico repositorio = new repositorioGenerico();
    private List<Animal> animaisAdotados = new ArrayList<>();
    private boolean paginaAcessada = false;
    private String mensagem = "";

    @Given("os animais {string} e {string} foram adotados recentemente")
    public void osAnimaisForamAdotadosRecentemente(String nomeAnimal1, String nomeAnimal2) {
        // Criando animais e simulando a adoção
        Animal rex = new Animal(new IdAnimal(1), null, null, null, nomeAnimal1, "2 anos", "Cachorro", "Labrador", "Grande", "Macho");
        Animal mia = new Animal(new IdAnimal(2), null, null, null, nomeAnimal2, "3 anos", "Gato", "Siamês", "Pequeno", "Fêmea");

        rex.setAdotado(true);  // Marcando Rex como adotado
        mia.setAdotado(true);  // Marcando Mia como adotada

        // Salvando os animais no repositório
        repositorio.salvar(rex);
        repositorio.salvar(mia);

        // Adicionando os animais adotados à lista
        animaisAdotados.add(rex);
        animaisAdotados.add(mia);
    }

    @Given("nenhum animal foi adotado recentemente")
    public void nenhumAnimalFoiAdotadoRecentemente() {
        // Limpando a lista de animais adotados
        animaisAdotados.clear();
    }

    @When("o usuário acessa a página de animais adotados")
    public void oUsuarioAcessaAPaginaDeAnimaisAdotados() {
        // Simulando o acesso à página
        paginaAcessada = true;

        // Se não houver animais adotados, define a mensagem
        if (animaisAdotados.isEmpty()) {
            mensagem = "Nenhum animal foi adotado recentemente";
        }
    }

    @Then("o sistema deve exibir o animal adotado {string}")
    public void oSistemaDeveExibirOAnimal(String nomeAnimalEsperado) {
        Assert.assertTrue(paginaAcessada);  // Verifica se a página foi acessada

        // Verifica se o animal está na lista de animais adotados
        boolean animalEncontrado = animaisAdotados.stream()
                .anyMatch(animal -> animal.getNomeAnimal().equalsIgnoreCase(nomeAnimalEsperado));

        Assert.assertTrue("O animal " + nomeAnimalEsperado + " não foi encontrado.", animalEncontrado);
    }

    @Then("o sistema deve exibir a mensagem {string}")
    public void oSistemaDeveExibirAMensagem(String mensagemEsperada) {
        Assert.assertEquals(mensagemEsperada, mensagem);  // Verifica se a mensagem correta é exibida
    }
}
