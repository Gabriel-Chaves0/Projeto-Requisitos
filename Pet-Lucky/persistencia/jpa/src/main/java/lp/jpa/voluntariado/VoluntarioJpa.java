package lp.jpa.voluntariado;

import jakarta.persistence.*;
import lp.jpa.adocao.abrigo.AbrigoJpa;
import lp.jpa.adocao.comuns.EnderecoJpa;

import java.util.List;

@Entity
public class VoluntarioJpa {

    @Id
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String rua;
    private String cidade;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "voluntario_abrigos",
            joinColumns = @JoinColumn(name = "voluntario_id"),
            inverseJoinColumns = @JoinColumn(name = "abrigo_id")
    )
    private List<AbrigoJpa> abrigosAssociados;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<AbrigoJpa> getAbrigosAssociados() {
        return abrigosAssociados;
    }

    public void setAbrigosAssociados(List<AbrigoJpa> abrigosAssociados) {
        this.abrigosAssociados = abrigosAssociados;
    }
}