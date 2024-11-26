package lp.apresentacao.adocao.animal;
import lp.adocao.dominio.animal.Animal;
import java.util.List;

public class PesquisaPorRaca implements PesquisarAnimalStrategy {
    private final String raca;

    public PesquisaPorRaca(String raca) {
        this.raca = raca;
    }

    @Override
    public List<Animal> pesquisar(List<Animal> animais) {
        return animais.stream()
                .filter(animal -> animal.getRaca().equalsIgnoreCase(raca))
                .toList();
    }
}