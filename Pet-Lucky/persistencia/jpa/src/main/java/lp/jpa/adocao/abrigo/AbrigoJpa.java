package lp.jpa.adocao.abrigo;

import jakarta.persistence.*;
import lp.jpa.adocao.animal.AnimalJpa;
import lp.jpa.adocao.comuns.ContatoJpa;
import lp.jpa.adocao.comuns.EnderecoJpa;
import lp.jpa.adocao.pessoa.PessoaJpa;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "abrigo")
public class AbrigoJpa {

    @Id
    private Long id;



    @Column(name = "nome_abrigo")
    private String nomeAbrigo;

    @Embedded
    private EnderecoJpa enderecoAbrigo;

    @Embedded
    private ContatoJpa contatoAbrigo;

    @Column(name = "capacidade_abrigo", nullable = false)
    private int capacidadeAbrigo;

    @OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaJpa> funcionariosAbrigo = new ArrayList<>();

    @OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL)
    private List<AnimalJpa> animaisAbrigo;

    // Construtores, Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAbrigo() {
        return nomeAbrigo;
    }

    public void setNomeAbrigo(String nomeAbrigo) {
        this.nomeAbrigo = nomeAbrigo;
    }

    public EnderecoJpa getEnderecoAbrigo() {
        return enderecoAbrigo;
    }

    public void setEnderecoAbrigo(EnderecoJpa enderecoAbrigo) {
        this.enderecoAbrigo = enderecoAbrigo;
    }

    public ContatoJpa getContatoAbrigo() {
        return contatoAbrigo;
    }

    public void setContatoAbrigo(ContatoJpa contatoAbrigo) {
        this.contatoAbrigo = contatoAbrigo;
    }

    public int getCapacidadeAbrigo() {
        return capacidadeAbrigo;
    }

    public void setCapacidadeAbrigo(int capacidadeAbrigo) {
        this.capacidadeAbrigo = capacidadeAbrigo;
    }

    public List<PessoaJpa> getFuncionariosAbrigo() {
        return funcionariosAbrigo;
    }

    public void setFuncionariosAbrigo(List<PessoaJpa> funcionariosAbrigo) {
        this.funcionariosAbrigo = funcionariosAbrigo;
    }

    public List<AnimalJpa> getAnimaisAbrigo() {
        return animaisAbrigo;
    }

    public void setAnimaisAbrigo(List<AnimalJpa> animaisAbrigo) {
        this.animaisAbrigo = animaisAbrigo;
    }
}
