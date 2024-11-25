package lp.jpa;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.adocao.dominio.comuns.Contato;
import lp.adocao.dominio.comuns.Endereco;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.pessoa.Pessoa;
import lp.jpa.adocao.abrigo.AbrigoImpl;
import lp.jpa.adocao.pessoa.PessoaImpl;
import lp.jpa.voluntariado.VoluntarioImpl;
import lp.voluntariado.dominio.voluntario.Voluntario;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@SpringBootTest
public class IncreverVoluntario {

    @Autowired
    private VoluntarioImpl repositorio;

    @Autowired
    private AbrigoImpl repositorioAbrigo;

    @Autowired
    private PessoaImpl repositorioPessoa;



    @Given("existe um abrigo chamado {string}")
    public void existeUmAbrigoChamado(String nomeAbrigo) {
        // Criando abrigo
        IdAbrigo idAbrigo = new IdAbrigo(3);

        Contato contato = new Contato("fcoantonio47@gmail.com", "81981521144");

        Endereco endereco = new Endereco("Rua 1", "Recife");

        Abrigo abrigo = new Abrigo(idAbrigo, "Abrigo Esperança", endereco, contato, 50);

        // Salvando abrigo no repositório
        repositorioAbrigo.salvar(abrigo);

        // Verificar se o abrigo foi salvo
        Abrigo abrigoSalvo = repositorioAbrigo.listarAbrigos().stream()
                .filter(a -> a.getNomeAbrigo().equals(nomeAbrigo))
                .findFirst()
                .orElse(null);
        Assert.assertNotNull("Abrigo não foi salvo corretamente", abrigoSalvo);
    }

    @Given("o usuário {string} está cadastrado no sistema")
    public void oUsuarioEstaCadastradoNoSistema(String nomeUsuario) {
        Pessoa pessoa = new Pessoa(
                new IdPessoa(1),
                new Endereco("rua 1", "Recife"),
                new Contato("email", "telefone"),
                nomeUsuario,
                "123456789",
                new Date(),
                null
        );

        repositorioPessoa.salvar(pessoa);



        // Validar se a pessoa foi salva
        Pessoa pessoaSalva = repositorioPessoa.buscarPorCPF("123456789");
        Assert.assertNotNull("Pessoa não foi encontrada", pessoaSalva);
    }

    @When("o usuário {string} do CPF {string} se inscreve como voluntária no {string}")
    public void oUsuarioDoCPFSeInscreveComoVoluntariaNo(String nomeUsuario, String cpf, String nomeAbrigo) {
        // Recuperando abrigo
        Abrigo abrigo = repositorioAbrigo.listarAbrigos().stream()
                .filter(a -> a.getNomeAbrigo() != null && a.getNomeAbrigo().equalsIgnoreCase(nomeAbrigo))
                .findFirst()
                .orElse(null);

        System.out.println("Abrigo encontrado: " + (abrigo != null ? abrigo.getNomeAbrigo() : "null"));
        Assert.assertNotNull("Abrigo não encontrado: " + nomeAbrigo, abrigo);

        // Recuperando usuário
        Pessoa pessoa = repositorioPessoa.buscarPorCPF(cpf);
        System.out.println("Pessoa encontrada: " + (pessoa != null ? pessoa.getNomePessoa() : "null"));
        Assert.assertNotNull("Pessoa não encontrada: " + cpf, pessoa);

        // Criando voluntário
        Voluntario voluntario = new Voluntario(pessoa, List.of(abrigo));

        // Salvando voluntário no repositório
        repositorio.salvar(voluntario);
    }

    @Then("o sistema deve registrar a inscrição de {string} do CPF {string} como voluntária no {string}")
    public void oUsuarioEstaInscritoNoAbrigo(String nomeUsuario,String cpf, String nomeAbrigo){
        // Recuperando abrigo
        Abrigo abrigo = repositorioAbrigo.listarAbrigos().stream()
                .filter(a -> a.getNomeAbrigo().equalsIgnoreCase(nomeAbrigo))
                .findFirst()
                .orElse(null);

        Assert.assertNotNull(abrigo);

        // Recuperando usuário
        Pessoa pessoa = repositorioPessoa.buscarPorCPF(cpf);
        System.out.println("Pessoa encontrada: " + (pessoa != null ? pessoa.getNomePessoa() : "null"));

        // Verificando se o usuário está associado ao abrigo
        if(repositorio.obterVoluntarioPorId(pessoa.getIdPessoa()) != null){
            Voluntario voluntario = repositorio.obterVoluntarioPorId(pessoa.getIdPessoa());
            System.out.println("Voluntário encontrado: " + voluntario.getNomePessoa());
            System.out.println("Abrigos associados: " + voluntario.getAbrigosAssociados().getFirst().getNomeAbrigo());
            Assert.assertTrue(voluntario.getAbrigosAssociados().getFirst().getNomeAbrigo().equals(nomeAbrigo));
        }
        else{
            Assert.fail("Voluntário não encontrado");
        }
    }


}
