package lp;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InscreverVoluntarioSteps {

    private Map<String, Abrigo> abrigos = new HashMap<>();
    private Map<String, Usuario> usuarios = new HashMap<>();
    private String mensagemSistema;

    @Given("existe um abrigo chamado {string}")
    public void existeUmAbrigoChamado(String nomeAbrigo) {
        abrigos.put(nomeAbrigo, new Abrigo(nomeAbrigo));
    }

    @Given("o usuário {string} está cadastrado no sistema")
    public void oUsuarioEstaCadastradoNoSistema(String nomeUsuario) {
        usuarios.put(nomeUsuario, new Usuario(nomeUsuario));
    }

    @When("o usuário {string} se inscreve como voluntária no {string}")
    public void oUsuarioSeInscreveComoVoluntariaNo(String nomeUsuario, String nomeAbrigo) {
        Abrigo abrigo = abrigos.get(nomeAbrigo);
        Usuario usuario = usuarios.get(nomeUsuario);
        if (abrigo != null && usuario != null) {
            abrigo.adicionarVoluntario(usuario);
            usuario.inscreverComoVoluntario(abrigo);
        } else {
            mensagemSistema = "Abrigo ou usuário não encontrado";
        }
    }

    @Then("o sistema deve registrar a inscrição de {string} como voluntária no {string}")
    public void oSistemaDeveRegistrarAInscricao(String nomeUsuario, String nomeAbrigo) {
        Abrigo abrigo = abrigos.get(nomeAbrigo);
        Usuario usuario = usuarios.get(nomeUsuario);
        Assert.assertTrue(abrigo.getVoluntarios().contains(usuario));
    }

    @Then("o abrigo deve ser notificado sobre o novo voluntário")
    public void oAbrigoDeveSerNotificadoSobreONovoVoluntario() {
        // Implementação da notificação (pode ser mockada)
    }

    @Given("não existe um abrigo com o nome {string}")
    public void naoExisteUmAbrigoComONome(String nomeAbrigo) {
        Assert.assertFalse(abrigos.containsKey(nomeAbrigo));
    }

    @When("o usuário {string} tenta se inscrever como voluntário no {string}")
    public void oUsuarioTentaSeInscreverComoVoluntarioNo(String nomeUsuario, String nomeAbrigo) {
        Abrigo abrigo = abrigos.get(nomeAbrigo);
        if (abrigo == null) {
            mensagemSistema = "Abrigo não encontrado";
        }
    }

    @Then("o sistema deve informar que o abrigo não foi encontrado")
    public void oSistemaDeveInformarQueOAbrigoNaoFoiEncontrado() {
        Assert.assertEquals("Abrigo não encontrado", mensagemSistema);
    }
}
