package lp.apresentacao.adocao.pessoa.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lp.adocao.dominio.comuns.Contato;
import lp.adocao.dominio.comuns.Endereco;
import lp.adocao.dominio.comuns.Preferencias;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.pessoa.Pessoa;

import java.util.List;
import java.util.Date;

public class PessoaDTO {

    @NotNull(message = "ID da pessoa não pode ser nulo")
    private int idPessoa;  // Substituído o tipo complexo por um tipo simples (Long, por exemplo)

    @NotNull(message = "Nome não pode ser nulo")
    private String nomePessoa;

    @NotNull(message = "CPF não pode ser nulo")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos numéricos")
    private String cpf;

    @NotNull(message = "Data de nascimento não pode ser nula")
    private Date dataPessoa;

    @NotNull(message = "Rua não pode ser nula")
    private String rua;  // Substituindo o tipo complexo por String

    @NotNull(message = "Cidade não pode ser nula")
    private String cidade;  // Substituindo o tipo complexo por String

    @NotNull(message = "Telefone não pode ser nulo")
    private String telefone;  // Substituindo o tipo complexo por String

    @NotNull(message = "Email não pode ser nulo")
    private String emailPessoa;  // Novo campo para email

    private List<String> especie;
    private List<String> raca;
    private List<String> porte;
    private List<String> sexo;

    // Getters e Setters

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmailPessoa() {
        return emailPessoa;
    }

    public void setEmailPessoa(String emailPessoa) {
        this.emailPessoa = emailPessoa;
    }


    public List<String> getEspecie() {
        return especie;
    }

    public void setEspecie(List<String> especie) {
        this.especie = especie;
    }

    public List<String> getRaca() {
        return raca;
    }

    public void setRaca(List<String> raca) {
        this.raca = raca;
    }

    public List<String> getPorte() {
        return porte;
    }

    public void setPorte(List<String> porte) {
        this.porte = porte;
    }

    public List<String> getSexo() {
        return sexo;
    }

    public void setSexo(List<String> sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "PessoaDTO{" +
                "idPessoa=" + idPessoa +
                ", nomePessoa='" + nomePessoa + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataPessoa=" + dataPessoa +
                ", rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", emailPessoa='" + emailPessoa + '\'' +
                ", preferencias='" + especie + '\'' +
                '}';
    }

    // Método para converter para a entidade Pessoa
    public Pessoa toPessoa() {
        Pessoa pessoa = new Pessoa(
                new IdPessoa(idPessoa),
                new Endereco(rua, cidade),
                new Contato(telefone, emailPessoa),
                nomePessoa,
                cpf,
                dataPessoa,
                new Preferencias(especie, raca, porte, sexo)
        );
        return pessoa;
    }
}
