package lp.apresentacao.adocao.abrigo.dto;

import jakarta.validation.constraints.NotNull;
import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.adocao.dominio.comuns.Contato;
import lp.adocao.dominio.comuns.Endereco;

import java.util.List;

public class AbrigoDTO {

    @NotNull(message = "ID da abrigo não pode ser nulo")
    private int idAbrigo;

    @NotNull(message = "Nome do abrigo não pode ser nulo")
    private String nomeAbrigo;

    @NotNull(message = "Rua não pode ser nula")
    private String rua;

    @NotNull(message = "Cidade não pode ser nula")
    private String cidade;

    @NotNull(message = "Telefone não pode ser nulo")
    private String telefone;

    @NotNull(message = "Email não pode ser nulo")
    private String email;

    @NotNull(message = "Capacidade do abrigo não pode ser nulo")
    private int capacidadeAbrigo;

    @NotNull(message = "ID da abrigo não pode ser nulo")
    public int getIdAbrigo() {
        return idAbrigo;
    }

    public void setIdAbrigo(@NotNull(message = "ID da abrigo não pode ser nulo") int idAbrigo) {
        this.idAbrigo = idAbrigo;
    }

    public @NotNull(message = "Nome do abrigo não pode ser nulo") String getNomeAbrigo() {
        return nomeAbrigo;
    }

    public void setNomeAbrigo(@NotNull(message = "Nome do abrigo não pode ser nulo") String nomeAbrigo) {
        this.nomeAbrigo = nomeAbrigo;
    }

    public @NotNull(message = "Rua não pode ser nula") String getRua() {
        return rua;
    }

    public void setRua(@NotNull(message = "Rua não pode ser nula") String rua) {
        this.rua = rua;
    }

    public @NotNull(message = "Cidade não pode ser nula") String getCidade() {
        return cidade;
    }

    public void setCidade(@NotNull(message = "Cidade não pode ser nula") String cidade) {
        this.cidade = cidade;
    }

    public @NotNull(message = "Telefone não pode ser nulo") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotNull(message = "Telefone não pode ser nulo") String telefone) {
        this.telefone = telefone;
    }

    public @NotNull(message = "Email não pode ser nulo") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Email não pode ser nulo") String email) {
        this.email = email;
    }

    @NotNull(message = "Capacidade do abrigo não pode ser nulo")
    public int getCapacidadeAbrigo() {
        return capacidadeAbrigo;
    }

    public void setCapacidadeAbrigo(@NotNull(message = "Capacidade do abrigo não pode ser nulo") int capacidadeAbrigo) {
        this.capacidadeAbrigo = capacidadeAbrigo;
    }

    public Abrigo toAbrigo(){
        Abrigo abrigo = new Abrigo(
                new IdAbrigo(idAbrigo),
                nomeAbrigo,
                new Endereco(rua, cidade),
                new Contato(telefone, email),
                capacidadeAbrigo
        );

        return abrigo;
    }
}
