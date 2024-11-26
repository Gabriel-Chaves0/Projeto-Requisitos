package lp.apresentacao.voluntariado.voluntario.dto;


import jakarta.validation.constraints.NotNull;
import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.comuns.Contato;
import lp.adocao.dominio.comuns.Endereco;
import lp.adocao.dominio.comuns.Preferencias;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.voluntariado.dominio.voluntario.Voluntario;


import java.util.Date;
import java.util.List;


public class VoluntarioDTO {


    @NotNull(message = "id não pode ser nulo")
    private int idVoluntario;

    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @NotNull(message = "CPF não pode ser nulo")
    private String cpf;

    @NotNull(message = "dataNascimento não pode ser nula")
    private Date dataNascimento;

    @NotNull(message = "Rua não pode ser nula")
    private String rua;

    @NotNull(message = "Cidade não pode ser nula")
    private String cidade;

    @NotNull(message = "Telefone não pode ser nulo")
    private String telefone;

    @NotNull(message = "Email não pode ser nulo")
    private String email;

    private List<String> especie;
    private List<String> raca;
    private List<String> porte;
    private List<String> sexo;
    List<Abrigo> abrigosAssociados;

    public int getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(int idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Abrigo> getAbrigosAssociados() {
        return abrigosAssociados;
    }

    public void setAbrigosAssociados(List<Abrigo> abrigosAssociados) {
        this.abrigosAssociados = abrigosAssociados;
    }

    @Override
    public String toString() {
        return "VoluntarioDTO{" +
                "idPessoa=" + idVoluntario +
                ", nomePessoa='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataPessoa=" + dataNascimento +
                ", rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", emailPessoa='" + email + '\'' +
                ", preferencias='" + especie + '\'' +
                '}';
    }

    public Voluntario toVoluntario() {
        Voluntario voluntario = new Voluntario(
                new IdPessoa(idVoluntario),
                new Endereco(rua, cidade),
                new Contato(email, telefone),
                nome, cpf, dataNascimento,
                new Preferencias(especie, raca, porte, sexo),
                abrigosAssociados
        );
        return voluntario;
    }
}