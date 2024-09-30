package lp.adocao.dominio.animal;

import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.comuns.Personalidade;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@AggregateRoot
public class Animal {

    @Identity
    private final IdAnimal idAnimal;
    private IdAbrigo idAbrigo;
    private IdPessoa idAdotante;
    private SaudeAnimal saudeAnimal;
    private String nomeAnimal;
    private String idadeAnimal;
    private String especie;
    private String raca;
    private String porte; // TODO: implementar enum com os portes (pequeno, médio, grande)
    private String sexo; // TODO: implementar enum com os sexos (macho, fêmea)
    private List<Personalidade> tagsPersonalidade;
    private Boolean adotado;

    public Animal(IdAnimal idAnimal, IdAbrigo idAbrigo, @Nullable IdPessoa idAdotante, @Nullable SaudeAnimal saudeAnimal, String nomeAnimal, String idadeAnimal, String especie, String raca, String porte, String sexo) {
        this.idAnimal = idAnimal;
        this.idAbrigo = idAbrigo;
        this.idAdotante = idAdotante;
        this.saudeAnimal = saudeAnimal;
        this.nomeAnimal = nomeAnimal;
        this.idadeAnimal = idadeAnimal;
        this.especie = especie;
        this.raca = raca;
        this.porte = porte;
        this.sexo = sexo;
    }

    public IdAnimal getIdAnimal() {
        return idAnimal;
    }

    public void inserirPersonalidades(List<Personalidade> tags) {
        this.tagsPersonalidade.addAll(tags);
    }

    public void removerPersonalidades(List<Personalidade> tags) {
        this.tagsPersonalidade.removeAll(tags);
    }

    public String getIdadeAnimal() {
        return idadeAnimal;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public String getPorte() {
        return porte;
    }

    public String getSexo() {
        return sexo;
    }

    public List<Personalidade> getTagsPersonalidade() {
        return tagsPersonalidade;
    }

    public Boolean getAdotado() {
        return adotado;
    }

    public void setAdotado(Boolean adotado) {
        this.adotado = adotado;
    }

    public IdPessoa getIdAdotante() {
        return idAdotante;
    }

    public void setIdAdotante(IdPessoa idAdotante) {
        this.idAdotante = idAdotante;
    }

    public SaudeAnimal getSaudeAnimal() {
        return saudeAnimal;
    }

    public void setSaudeAnimal(SaudeAnimal saudeAnimal) {
        this.saudeAnimal = saudeAnimal;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }
}
