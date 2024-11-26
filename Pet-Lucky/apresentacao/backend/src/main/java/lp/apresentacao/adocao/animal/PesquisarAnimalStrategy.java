package lp.apresentacao.adocao.animal;
import lp.adocao.dominio.animal.Animal;
import java.util.List;

public interface PesquisarAnimalStrategy {
    List<Animal> pesquisar(List<Animal> animais);
}
