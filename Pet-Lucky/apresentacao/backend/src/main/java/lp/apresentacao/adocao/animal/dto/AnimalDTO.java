package lp.apresentacao.adocao.animal.dto;

import jakarta.validation.constraints.NotNull;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IdAnimal;
import lp.adocao.dominio.animal.SaudeAnimal;
import lp.adocao.dominio.pessoa.IdPessoa;

import java.lang.annotation.Native;

public class AnimalDTO {

    @NotNull(message = "O id do animal não pode ser nulo")
    private int id;

    @NotNull(message = "O id do abrigo não pode ser nulo")
    private int idAbrigo;

    private int idAdotante;

    private boolean vacinaRaiva;
    private boolean vermifugado;
    private boolean castrado;
    private String nomeAnimal;
    private String idadeAnimal;
    private String especie;
    private String raca;
    private String porte;
    private String sexo;
    private Boolean adotado;

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAbrigo() {
        return idAbrigo;
    }

    public void setIdAbrigo(int idAbrigo) {
        this.idAbrigo = idAbrigo;
    }

    public int getIdAdotante() {
        return idAdotante;
    }

    public void setIdAdotante(int idAdotante) {
        this.idAdotante = idAdotante;
    }

    public boolean isVacinaRaiva() {
        return vacinaRaiva;
    }

    public void setVacinaRaiva(boolean vacinaRaiva) {
        this.vacinaRaiva = vacinaRaiva;
    }

    public boolean isVermifugado() {
        return vermifugado;
    }

    public void setVermifugado(boolean vermifugado) {
        this.vermifugado = vermifugado;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getIdadeAnimal() {
        return idadeAnimal;
    }

    public void setIdadeAnimal(String idadeAnimal) {
        this.idadeAnimal = idadeAnimal;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Boolean getAdotado() {
        return adotado;
    }

    public void setAdotado(Boolean adotado) {
        this.adotado = adotado;
    }

    public Animal toAnimal(){
        return new Animal(
                new IdAnimal(id),
                new IdAbrigo(idAbrigo),
                new IdPessoa(idAdotante),
                new SaudeAnimal(vacinaRaiva, vermifugado, castrado),
                nomeAnimal,
                idadeAnimal,
                especie,
                raca,
                porte,
                sexo);

    }


}
