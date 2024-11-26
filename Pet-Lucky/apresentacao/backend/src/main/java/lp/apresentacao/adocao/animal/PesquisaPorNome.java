package lp.apresentacao.adocao.animal;
import lp.adocao.dominio.animal.Animal;
import java.util.List;

public class PesquisaPorNome implements PesquisarAnimalStrategy {
    private final String nome;

    public PesquisaPorNome(String nome) {
        this.nome = nome;
    }

    @Override
    public List<Animal> pesquisar(List<Animal> animais) {
        return animais.stream()
                .filter(animal -> animal.getNomeAnimal().equalsIgnoreCase(nome))
                .toList();
    }
}