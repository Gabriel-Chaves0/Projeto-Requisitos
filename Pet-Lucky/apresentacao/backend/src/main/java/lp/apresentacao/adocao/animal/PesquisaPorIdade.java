package lp.apresentacao.adocao.animal;
import lp.adocao.dominio.animal.Animal;
import java.util.List;

public class PesquisaPorIdade implements PesquisarAnimalStrategy {
    private final int idade;

    public PesquisaPorIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public List<Animal> pesquisar(List<Animal> animais) {
        return animais.stream()
                .filter(animal -> animal.getIdadeAnimal().equals(idade+""))
                .toList();
    }
}
