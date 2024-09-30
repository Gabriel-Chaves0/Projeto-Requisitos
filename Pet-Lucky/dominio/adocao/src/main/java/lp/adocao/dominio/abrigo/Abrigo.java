package lp.adocao.dominio.abrigo;

import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.comuns.Contato;
import lp.adocao.dominio.comuns.Endereco;
import lp.adocao.dominio.pessoa.Pessoa;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.util.List;

@AggregateRoot
public class Abrigo {

    @Identity
    private final IdAbrigo idAbrigo;
    private String nomeAbrigo;
    private Endereco enderecoAbrigo;
    private Contato contatoAbrigo;
    private int capacidadeAbrigo;
    private List<Pessoa> funcionariosAbrigo;
    private List<Animal> animaisAbrigo;


    public Abrigo(IdAbrigo idAbrigo, String nome, Endereco endereco, Contato contato, int capacidade) {
        this.idAbrigo = idAbrigo;
        this.nomeAbrigo = nome;
        this.enderecoAbrigo = endereco;
        this.contatoAbrigo = contato;
        this.capacidadeAbrigo = capacidade;
    }

    public boolean isMaxCpacity () {
        return animaisAbrigo.size() >= capacidadeAbrigo;
    }

    public IdAbrigo getIdAbrigo() {
        return idAbrigo;
    }

    public String getNomeAbrigo() {
        return nomeAbrigo;
    }

    public void setNomeAbrigo(String nomeAbrigo) {
        this.nomeAbrigo = nomeAbrigo;
    }

    public Endereco getEnderecoAbrigo() {
        return enderecoAbrigo;
    }

    public void setEnderecoAbrigo(Endereco enderecoAbrigo) {
        this.enderecoAbrigo = enderecoAbrigo;
    }

    public Contato getContatoAbrigo() {
        return contatoAbrigo;
    }

    public void setContatoAbrigo(Contato contatoAbrigo) {
        this.contatoAbrigo = contatoAbrigo;
    }

    public int getCapacidadeAbrigo() {
        return capacidadeAbrigo;
    }

    public void setCapacidadeAbrigo(int capacidadeAbrigo) {
        this.capacidadeAbrigo = capacidadeAbrigo;
    }

    public List<Pessoa> getFuncionariosAbrigo() {
        return funcionariosAbrigo;
    }

    public List<Animal> getAnimaisAbrigo() {
        return animaisAbrigo;
    }

}
