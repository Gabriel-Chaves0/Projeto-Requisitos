package lp.apresentacao.adocao.abrigo;

import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.pessoa.Pessoa;
import lp.jpa.adocao.animal.AnimalImpl;
import lp.jpa.adocao.pessoa.PessoaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdocaoService {
    @Autowired
    private AnimalImpl animalRepository;

    @Autowired
    private PessoaImpl pessoaRepository;

    public List<Animal> recomendarAnimais(IdPessoa idPessoa) {
        Pessoa pessoa = pessoaRepository.obterPorId(idPessoa);
        if (pessoa == null) {
            return null;
        }

        List<Animal> animaisDisponiveis = animalRepository.listarAnimais();

        return animaisDisponiveis.stream()
                .filter(animal -> pessoa.getPreferencias().getEspecies().contains(animal.getEspecie()))
                .filter(animal -> pessoa.getPreferencias().getRacas().contains(animal.getRaca()))
                .filter(animal -> pessoa.getPreferencias().getPortes().contains(animal.getPorte()))
                .collect(Collectors.toList());
    }
}
