package lp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.adocao.dominio.pessoa.Pessoa;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.comuns.Endereco;
import lp.adocao.dominio.comuns.Contato;
import lp.jpa.adocao.abrigo.AbrigoImpl;
import lp.jpa.adocao.pessoa.PessoaImpl;
import lp.jpa.voluntariado.VoluntarioImpl;
import lp.voluntariado.dominio.voluntario.Voluntario;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class IncreverVoluntario {
    private VoluntarioImpl repositorio = new VoluntarioImpl();
    private AbrigoImpl repositorioAbrigo = new AbrigoImpl();
    private PessoaImpl repositorioPessoa = new PessoaImpl();

    @Given("existe um abrigo chamado {string}")
    public void existeUmAbrigoChamado(String nomeAbrigo) {
        // Criando abrigo
        Abrigo abrigo = new Abrigo(new IdAbrigo(1), nomeAbrigo, null, null, 50);

        // Salvando abrigo no repositório
        repositorioAbrigo.salvar(abrigo);
    }

    @Given("o usuário {string} está cadastrado no sistema")
    public void oUsuarioEstaCadastradoNoSistema(String nomeUsuario) {
        // Usuário cadastrado
        Pessoa pessoa = new Pessoa(new IdPessoa(1), new Endereco("rua 1", "Recife"), new Contato("email", "telefone"), nomeUsuario, "123456789", null, null);
        repositorioPessoa.salvar(pessoa);

        Assert.assertNotNull(repositorioPessoa.obterPorId(pessoa.getIdPessoa()));
    }

    @When("o usuário {string} do CPF {string} se inscreve como voluntária no {string}")
    public void oUsuarioDoCPFSeInscreveComoVoluntariaNo(String nomeUsuario, String cpf, String nomeAbrigo) {
        // Recuperando abrigo
        Abrigo abrigo = repositorioAbrigo.listarAbrigos().stream()
                .filter(a -> a.getNomeAbrigo().equalsIgnoreCase(nomeAbrigo))
                .findFirst()
                .orElse(null);

        Assert.assertNotNull(abrigo);

        // Recuperando usuário
        Pessoa pessoa = repositorioPessoa.buscarPorCPF(cpf);

        Assert.assertNotNull(pessoa);

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

        // Verificando se o usuário está associado ao abrigo
        if(repositorio.obterVoluntarioPorId(pessoa.getIdPessoa()) != null){
            Voluntario voluntario = repositorio.obterVoluntarioPorId(pessoa.getIdPessoa());
            Assert.assertTrue(voluntario.getAbrigosAssociados().contains(abrigo));
        }
        else{
            Assert.fail("Voluntário não encontrado");
        }
    }


}
