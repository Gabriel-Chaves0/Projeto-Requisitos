package lp.adocao.dominio.animal;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public class SaudeAnimal {
    private boolean vacinaRaiva;
    private boolean vermifugado;
    private boolean castrado;

    public SaudeAnimal(boolean vacinaRaiva, boolean vermifugado, boolean castrado) {
        this.vacinaRaiva = vacinaRaiva;
        this.vermifugado = vermifugado;
        this.castrado = castrado;
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
}
