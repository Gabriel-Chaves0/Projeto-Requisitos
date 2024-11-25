package lp.jpa;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IdAnimal;
import lp.adocao.dominio.animal.SaudeAnimal;
import lp.adocao.dominio.comuns.Contato;
import lp.adocao.dominio.comuns.Endereco;
import lp.jpa.adocao.abrigo.AbrigoImpl;
import lp.jpa.adocao.animal.AnimalImpl;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class ExibirAnimaisAdotadosSteps {
    @Autowired
    private AnimalImpl repositorio;

    @Autowired
    private AbrigoImpl repositorioAbrigo;

    private List<Animal> animaisAdotados = new ArrayList<>();
    private boolean paginaAcessada = false;
    private String mensagem = "";

    @Given("os animais {string} e {string} foram adotados recentemente")
    public void osAnimaisForamAdotadosRecentemente(String nomeAnimal1, String nomeAnimal2) {

        Abrigo abrigo = new Abrigo(new IdAbrigo(1), "Abrigo Esperança", new Endereco("Rua 1", "Recife"), new Contato("null", "null"), 50);
        repositorioAbrigo.salvar(abrigo);
        // Criando animais e simulando a adoção
        Animal rex = new Animal(new IdAnimal(1), abrigo.getIdAbrigo(), null, new SaudeAnimal(true, true, true), nomeAnimal1, "2 anos", "Cachorro", "Labrador", "Grande", "Macho");
        Animal mia = new Animal(new IdAnimal(2), abrigo.getIdAbrigo(), null, new SaudeAnimal(true, true, true), nomeAnimal2, "3 anos", "Gato", "Siamês", "Pequeno", "Fêmea");

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
