package lp.adocao.dominio.comuns;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.List;

@ValueObject
public class Preferencias {
    private List<String> especies;
    private List<String> racas;
    private List<String> portes;
    private List<String> sexos;

    public Preferencias(List<String> especies, List<String> racas, List<String> portes, List<String> sexos) {
        this.especies = especies;
        this.racas = racas;
        this.portes = portes;
        this.sexos = sexos;
    }

    public Preferencias() {
    }

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
