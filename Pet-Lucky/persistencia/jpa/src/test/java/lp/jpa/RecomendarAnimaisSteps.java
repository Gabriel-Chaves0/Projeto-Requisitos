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
import lp.adocao.dominio.comuns.Preferencias;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.pessoa.Pessoa;
import lp.jpa.adocao.abrigo.AbrigoImpl;
import lp.jpa.adocao.animal.AnimalImpl;
import lp.jpa.adocao.pessoa.PessoaImpl;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class RecomendarAnimaisSteps {

    @Autowired
    private AnimalImpl repositorio;

    @Autowired
    private PessoaImpl repositorioPessoa;

    @Autowired
    private AbrigoImpl repositorioAbrigo;

    private List<Animal> animaisRecomendados = new ArrayList<>();

    private Pessoa usuario;

    @Given("o usuário {string} prefere animais da espécie {string}, da raça {string} e de porte {string}")
    public void oUsuarioPrefereAnimais(String nomeUsuario, String especie, String raca, String porte) {
        // Cria a pessoa com as preferências
        Preferencias preferencias = new Preferencias(
                List.of(especie),
                List.of(raca),
                List.of(porte),
                List.of() // Deixando vazio para o sexo (não especificado neste cenário)
        );

        // Salvando a pessoa no repositório com as preferências definidas
        usuario = new Pessoa(new IdPessoa(1), new Endereco("Rua 2", "Recife"), new Contato("null", "null"), nomeUsuario, "12345678900", new Date(), preferencias);
        repositorioPessoa.salvar(usuario);
    }

    @Given("existem animais disponíveis como {string}, {string} e {string}")
    public void existemAnimaisDisponiveis(String nomeAnimal1, String nomeAnimal2, String nomeAnimal3) {

        Abrigo abrigo = new Abrigo(new IdAbrigo(1), "Abrigo Esperança", new Endereco("Rua 1", "Recife"), new Contato("null", "null"), 50);
        repositorioAbrigo.salvar(abrigo);

        // Criando os animais disponíveis
        Animal rex = new Animal(new IdAnimal(1), abrigo.getIdAbrigo(), null, new SaudeAnimal(true, true, true), nomeAnimal1, "2 anos", "Cachorro", "Labrador", "Grande", "Macho");
        Animal bob = new Animal(new IdAnimal(2), abrigo.getIdAbrigo(), null, new SaudeAnimal(true, true, true), nomeAnimal2, "3 anos", "Cachorro", "Poodle", "Pequeno", "Macho");
        Animal mia = new Animal(new IdAnimal(3), abrigo.getIdAbrigo(), null, new SaudeAnimal(true, true, true), nomeAnimal3, "3 anos", "Gato", "Siamês", "Pequeno", "Fêmea");

        // Salvando os animais no repositório
        repositorio.salvar(rex);
        repositorio.salvar(bob);
        repositorio.salvar(mia);
    }

    @When("o usuário {string} acessa as recomendações")
    public void oUsuarioAcessaAsRecomendacoes(String nomeUsuario) {
        // Buscando os animais disponíveis
        List<Animal> animaisDisponiveis = repositorio.listarAnimais();

        // Buscando as preferências do usuário
        Preferencias preferenciasUsuario = repositorioPessoa.obterPorId(usuario.getIdPessoa()).getPreferencias();

        // Recomendando animais com base nas preferências do usuário
        animaisRecomendados = animaisDisponiveis.stream()
                .filter(animal -> preferenciasUsuario.getEspecies().contains(animal.getEspecie()))
                .filter(animal -> preferenciasUsuario.getRacas().contains(animal.getRaca()))
                .filter(animal -> preferenciasUsuario.getPortes().contains(animal.getPorte()))
                .collect(Collectors.toList());
    }

    @Then("o sistema deve recomendar o animal {string}")
    public void oSistemaDeveRecomendarOAnimal(String nomeAnimalEsperado) {
        // Verificando se o animal esperado está na lista de recomendações
        boolean animalEncontrado = animaisRecomendados.stream()
                .anyMatch(animal -> animal.getNomeAnimal().equalsIgnoreCase(nomeAnimalEsperado));

        Assert.assertTrue("O animal " + nomeAnimalEsperado + " não foi recomendado.", animalEncontrado);
    }

    @Then("o sistema não deve recomendar os animais {string} e {string}")
    public void oSistemaNaoDeveRecomendarOsAnimais(String nomeAnimal1, String nomeAnimal2) {
        // Verificando se os animais não recomendados estão fora da lista de recomendações
        List<String> animaisNaoRecomendados = List.of(nomeAnimal1, nomeAnimal2);

        List<String> animaisRecomendadosNomes = animaisRecomendados.stream()
                .map(Animal::getNomeAnimal)
                .collect(Collectors.toList());

        for (String nomeAnimal : animaisNaoRecomendados) {
            Assert.assertFalse("O animal " + nomeAnimal + " foi recomendado, mas não deveria.",
                    animaisRecomendadosNomes.contains(nomeAnimal));
        }
    }
}
