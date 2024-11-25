package lp.jpa.adocao.comuns;

import jakarta.persistence.*;
import java.util.List;

@Embeddable
public class PreferenciasJpa {

    @Convert(converter = StringListConverter.class)
    @Column(name = "especies_preferidas", columnDefinition = "TEXT")
    private List<String> especies;

    @Convert(converter = StringListConverter.class)
    @Column(name = "racas_preferidas", columnDefinition = "TEXT")
    private List<String> racas;

    @Convert(converter = StringListConverter.class)
    @Column(name = "portes_preferidos", columnDefinition = "TEXT")
    private List<String> portes;

    @Convert(converter = StringListConverter.class)
    @Column(name = "sexos_preferidos", columnDefinition = "TEXT")
    private List<String> sexos;

    // Construtor padrão para JPA
    public PreferenciasJpa() {}

    public PreferenciasJpa(List<String> especies, List<String> racas, List<String> portes, List<String> sexos) {
        this.especies = especies;
        this.racas = racas;
        this.portes = portes;
        this.sexos = sexos;
    }

    // Getters e Setters
    public List<String> getEspecies() {
        return especies;
    }

    public void setEspecies(List<String> especies) {
        this.especies = especies;
    }

    public List<String> getRacas() {
        return racas;
    }

    public void setRacas(List<String> racas) {
        this.racas = racas;
    }

    public List<String> getPortes() {
        return portes;
    }

    public void setPortes(List<String> portes) {
        this.portes = portes;
    }

    public List<String> getSexos() {
        return sexos;
    }

    public void setSexos(List<String> sexos) {
        this.sexos = sexos;
    }
}
