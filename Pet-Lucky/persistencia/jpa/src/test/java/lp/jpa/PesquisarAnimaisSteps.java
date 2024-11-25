package lp.jpa;

import io.cucumber.spring.CucumberContextConfiguration;
import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IdAnimal;
import lp.adocao.dominio.comuns.Contato;
import lp.adocao.dominio.comuns.Endereco;
import lp.jpa.adocao.JpaMapeador;
import lp.jpa.adocao.abrigo.AbrigoImpl;
import lp.jpa.adocao.animal.AnimalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.stream.Collectors;

import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest
public class PesquisarAnimaisSteps {

    @Autowired
    private AnimalImpl repositorio;

    @Autowired
    private AbrigoImpl repositorioAbrigo;

    @Autowired
    private JpaMapeador mapeador;

    private List<Animal> resultadoPesquisa;

    @Given("existem animais disponíveis para adoção")
    public void existemAnimaisDisponiveisParaAdocao() {
        // Criando abrigos manualmente
        IdAbrigo idAbrigoSaoPaulo = new IdAbrigo(1);
        IdAbrigo idAbrigoRio = new IdAbrigo(2);

        Endereco enderecoSaoPaulo = new Endereco("Rua 1", "São Paulo");
        Endereco enderecoRio = new Endereco("Rua 2", "Rio de Janeiro");

        Contato contatoSaoPaulo = new Contato("fcoantonio47@gmail.com", "81981521144");
        Contato contatoRio = new Contato("falf@cesar.school", "81981521144");

        Abrigo abrigoSaoPaulo = new Abrigo(idAbrigoSaoPaulo, "Abrigo São Paulo", enderecoSaoPaulo, contatoSaoPaulo, 50);
        Abrigo abrigoRio = new Abrigo(idAbrigoRio, "Abrigo Rio de Janeiro", enderecoRio, contatoRio, 50);

        // Salvando abrigos no repositório
        repositorioAbrigo.salvar(abrigoSaoPaulo);
        repositorioAbrigo.salvar(abrigoRio);

        // Criando animais manualmente
        Animal rex = new Animal(new IdAnimal(1), idAbrigoSaoPaulo, null, null, "Rex", "2 anos", "Cachorro", "Labrador", "Grande", "Macho");
        Animal mia = new Animal(new IdAnimal(2), idAbrigoRio, null, null, "Mia", "3 anos", "Gato", "Siamês", "Pequeno", "Fêmea");

        // Salvando animais no repositório
        repositorio.salvar(rex);
        repositorio.salvar(mia);
    }

    @When("o usuário pesquisa por {string} em {string}")
    public void oUsuarioPesquisaPorEm(String especie, String localizacao) {
        resultadoPesquisa = repositorio.listarAnimais().stream()
                .filter(animal -> animal.getEspecie().equals(especie))
                .filter(animal -> repositorioAbrigo.obterPorId(animal.getIdAbrigo()).getEnderecoAbrigo().getCidade().equals(localizacao))
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
