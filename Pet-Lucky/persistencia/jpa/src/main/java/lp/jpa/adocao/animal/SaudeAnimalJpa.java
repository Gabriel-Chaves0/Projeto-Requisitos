package lp.jpa.adocao.animal;

import jakarta.persistence.*;

@Entity
@Table(name = "saude_animal")
public class SaudeAnimalJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vacina_raiva", nullable = false)
    private boolean vacinaRaiva;

    @Column(name = "vermifugado", nullable = false)
    private boolean vermifugado;

    @Column(name = "castrado", nullable = false)
    private boolean castrado;

    // Construtores, Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
