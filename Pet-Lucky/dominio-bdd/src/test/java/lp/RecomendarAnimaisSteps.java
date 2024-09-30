package lp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IdAnimal;
import lp.adocao.dominio.comuns.Preferencias;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.pessoa.Pessoa;
import lp.repositorioGenerico;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecomendarAnimaisSteps {

    private repositorioGenerico repositorio = new repositorioGenerico();
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
        usuario = new Pessoa(new IdPessoa(1), null, null, nomeUsuario, "12345678900", null, preferencias);
        repositorio.salvar(usuario);
    }

    @Given("existem animais disponíveis como {string}, {string} e {string}")
    public void existemAnimaisDisponiveis(String nomeAnimal1, String nomeAnimal2, String nomeAnimal3) {
        // Criando os animais disponíveis
        Animal rex = new Animal(new IdAnimal(1), null, null, null, nomeAnimal1, "2 anos", "Cachorro", "Labrador", "Grande", "Macho");
        Animal bob = new Animal(new IdAnimal(2), null, null, null, nomeAnimal2, "3 anos", "Cachorro", "Poodle", "Pequeno", "Macho");
        Animal mia = new Animal(new IdAnimal(3), null, null, null, nomeAnimal3, "3 anos", "Gato", "Siamês", "Pequeno", "Fêmea");

        // Salvando os animais no repositório
        repositorio.salvar(rex);
        repositorio.salvar(bob);
        repositorio.salvar(mia);
    }

    @When("o usuário {string} acessa as recomendações")
    public void oUsuarioAcessaAsRecomendacoes(String nomeUsuario) {
        // Filtrando os animais com base nas preferências do usuário
        Preferencias preferencias = usuario.getPreferencias();
        List<Animal> animaisDisponiveis = List.copyOf(repositorio.animais.values());

        animaisRecomendados = animaisDisponiveis.stream()
                .filter(animal -> preferencias.getEspecies().contains(animal.getEspecie()))
                .filter(animal -> preferencias.getRacas().contains(animal.getRaca()))
                .filter(animal -> preferencias.getPortes().contains(animal.getPorte()))
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
