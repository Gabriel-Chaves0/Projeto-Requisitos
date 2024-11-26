package lp.jpa.adocao.pessoa;

import jakarta.persistence.*;
import lp.jpa.adocao.animal.AnimalJpa;
import lp.jpa.adocao.comuns.ContatoJpa;
import lp.jpa.adocao.comuns.EnderecoJpa;
import lp.jpa.adocao.comuns.PreferenciasJpa;
import lp.jpa.adocao.abrigo.AbrigoJpa;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pessoa")
public class PessoaJpa {

    @Id
    @Column(name = "id_pessoa", nullable = false)
    private long id;


    @ManyToOne
    @JoinColumn(name = "abrigo_id")
    private AbrigoJpa abrigo;


    @Embedded
    private EnderecoJpa enderecoPessoa;

    @Embedded
    private ContatoJpa contatoPessoa;

    @OneToMany
    @JoinTable(
            name = "pessoa_animais_adotados",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "animal_id")
    )
    private List<AnimalJpa> animaisAdotados;

    @Column(name = "nome", nullable = false)
    private String nomePessoa;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_pessoa", nullable = false)
    private Date dataPessoa;

    @Embedded
    private PreferenciasJpa preferencias;

    // Construtores, Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EnderecoJpa getEnderecoPessoa() {
        return enderecoPessoa;
    }

    public void setEnderecoPessoa(EnderecoJpa enderecoPessoa) {
        this.enderecoPessoa = enderecoPessoa;
    }

    public ContatoJpa getContatoPessoa() {
        return contatoPessoa;
    }

    public void setContatoPessoa(ContatoJpa contatoPessoa) {
        this.contatoPessoa = contatoPessoa;
    }

    public List<AnimalJpa> getAnimaisAdotados() {
        return animaisAdotados;
    }

    public void setAnimaisAdotados(List<AnimalJpa> animaisAdotados) {
        this.animaisAdotados = animaisAdotados;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataPessoa() {
        return dataPessoa;
    }

    public void setDataPessoa(Date dataPessoa) {
        this.dataPessoa = dataPessoa;
    }

    public PreferenciasJpa getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(PreferenciasJpa preferencias) {
        this.preferencias = preferencias;
    }
}
