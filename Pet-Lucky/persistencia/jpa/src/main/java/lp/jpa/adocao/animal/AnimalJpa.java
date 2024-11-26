package lp.jpa.adocao.animal;

import jakarta.persistence.*;
import lp.jpa.adocao.abrigo.AbrigoJpa;
import lp.jpa.adocao.pessoa.PessoaJpa;

@Entity
@Table(name = "animal")
public class AnimalJpa {

    @Id
    private Long id; // Identificador único no banco

    @ManyToOne
    @JoinColumn(name = "id_abrigo", nullable = false)
    private AbrigoJpa abrigo; // Relacionamento com a tabela de abrigo

    @OneToOne
    @JoinColumn(name = "id_adotante")
    private PessoaJpa adotante; // Relacionamento com a tabela de pessoa

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_saude")
    private SaudeAnimalJpa saude; // Relacionamento com a tabela de saúde

    @Column(name = "nome_animal", nullable = false)
    private String nomeAnimal;

    @Column(name = "idade_animal", nullable = false)
    private String idadeAnimal;

    @Column(name = "especie", nullable = false)
    private String especie;

    @Column(name = "raca", nullable = false)
    private String raca;

    @Column(name = "porte", nullable = false)
    private String porte;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "adotado")
    private Boolean adotado;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbrigoJpa getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(AbrigoJpa abrigo) {
        this.abrigo = abrigo;
    }

    public PessoaJpa getAdotante() {
        return adotante;
    }

    public void setAdotante(PessoaJpa adotante) {
        this.adotante = adotante;
    }

    public SaudeAnimalJpa getSaude() {
        return saude;
    }

    public void setSaude(SaudeAnimalJpa saude) {
        this.saude = saude;
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
}
