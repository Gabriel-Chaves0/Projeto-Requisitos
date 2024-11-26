package lp.apresentacao.adocao.animal;
import lp.adocao.dominio.animal.Animal;
import java.util.List;

public class ContextoPesquisaAnimal {

    private final List<PesquisarAnimalStrategy> estrategias;

    public ContextoPesquisaAnimal(List<PesquisarAnimalStrategy> estrategias) {
        this.estrategias = estrategias;
    }

    public List<Animal> executar(List<Animal> animais) {
        List<Animal> resultado = animais;
        for (PesquisarAnimalStrategy estrategia : estrategias) {
            resultado = estrategia.pesquisar(resultado);
        }
        return resultado;
    }
}