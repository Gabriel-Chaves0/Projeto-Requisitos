package lp.jpa.adocao.dominio.animal;

import jakarta.persistence.*;
import lp.jpa.adocao.dominio.abrigo.AbrigoJpa;
import org.jmolecules.ddd.annotation.Identity;


@Entity
@Table(name = "ANIMAL")
public class AnimalJpa {

    @Identity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAnimal;

    @ManyToOne
    @JoinColumn(name = "ID_ABRIGO")
    private AbrigoJpa abrigo;


    @ManyToOne
    private int idAdotante = Integer.parseInt(null);

    private SaudeAnimal saudeAnimal = null;
    private String nomeAnimal;
    private String idadeAnimal;
    private String especie;
    private String raca;
    private String porte; // TODO: implementar enum com os portes (pequeno, médio, grande)
    private String sexo; // TODO: implementar enum com os sexos (macho, fêmea)
    private Boolean adotado;
}