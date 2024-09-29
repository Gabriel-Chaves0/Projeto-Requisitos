package lp.adocao.dominio.pessoa;

import lp.adocao.dominio.animal.IdAnimal;
import lp.adocao.dominio.comuns.Contato;
import lp.adocao.dominio.comuns.Endereco;
import lp.adocao.dominio.comuns.Personalidade;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.util.Date;
import java.util.List;

@AggregateRoot
public class Pessoa {

    @Identity
    private final IdPessoa idPessoa;
    private Endereco enderecoPessoa;
    private Contato contatoPessoa;
    private List<IdAnimal> animaisAdotados;
    private String nomePessoa;
    private String cpf;
    private Date dataPessoa;
    private List<Personalidade> tagsPersonalidadeDesejada;

    public Pessoa(IdPessoa idPessoa, Endereco endereco, Contato contato, String nome, String cpf, Date data, List<Personalidade> personalidades) {
        this.idPessoa = idPessoa;
        this.enderecoPessoa = endereco;
        this.contatoPessoa = contato;
        this.nomePessoa = nome;
        this.cpf = cpf;
        this.dataPessoa = data;
        this.tagsPersonalidadeDesejada = personalidades;
    }


    public void inserirPersonalidades(List<Personalidade> tags) {
        this.tagsPersonalidadeDesejada.addAll(tags);
    }

    public void removerPersonalidades(List<Personalidade> tags) {
        this.tagsPersonalidadeDesejada.removeAll(tags);
    }

    public IdPessoa getIdPessoa() {
        return idPessoa;
    }

    public Endereco getEnderecoPessoa() {
        return enderecoPessoa;
    }

    public void setEnderecoPessoa(Endereco enderecoPessoa) {
        this.enderecoPessoa = enderecoPessoa;
    }

    public Contato getContatoPessoa() {
        return contatoPessoa;
    }

    public void setContatoPessoa(Contato contatoPessoa) {
        this.contatoPessoa = contatoPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }
}
