package lp.apresentacao.adocao.animal;


import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IdAnimal;
import lp.apresentacao.adocao.animal.dto.AnimalDTO;
import lp.jpa.adocao.animal.AnimalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animais")
public class AnimalControler {

    @Autowired
    private AnimalImpl animalImpl;

    @Autowired
    public AnimalControler(AnimalImpl animalImpl) {
        this.animalImpl = animalImpl;
    }

    @PostMapping
    public ResponseEntity<String> salvarAnimal(@RequestBody AnimalDTO animal) {

        animalImpl.salvar(animal.toAnimal());
        return ResponseEntity.ok("Animal salvo com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> obterAnimalPorId(@PathVariable("id") int id) {
        Animal animal = animalImpl.obterPorId(new IdAnimal(id));
        if (animal != null) {
            return ResponseEntity.ok(animal);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping


    @PutMapping("/{id}")
    public ResponseEntity<String> editarAnimal(@PathVariable("id") int id, @RequestBody AnimalDTO animal) {
        if (animalImpl.obterPorId(new IdAnimal(id)) != null) {
            animalImpl.editar(animal.toAnimal());
            return ResponseEntity.ok("Animal atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerAnimal(@PathVariable("id") int id) {
        if (animalImpl.obterPorId(new IdAnimal(id)) != null) {
            animalImpl.remover(new IdAnimal(id));
            return ResponseEntity.ok("Animal removido com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal não encontrado.");
        }
    }

    //funcionalidade de ExibirAnimaisAdotados
    @GetMapping("/adotados")
    public ResponseEntity<List<Animal>> exibirAnimaisAdotados() {
        List<Animal> animaisAdotados = animalImpl.listarAnimais().stream()
                .filter(Animal::getAdotado)
                .toList();

        if (animaisAdotados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(animaisAdotados);
    }

    //funcionalidade de PesquisarAnimais
    @GetMapping("/pesquisar")
    public ResponseEntity<List<Animal>> pesquisarAnimais( @RequestParam(required = false) String nome,  @RequestParam(required = false) String raca,
                                                          @RequestParam(required = false) Integer idade) {

        List<Animal> animais = animalImpl.listarAnimais().stream()
                .filter(animal -> (nome == null || animal.getNomeAnimal().equalsIgnoreCase(nome)))
                .filter(animal -> (raca == null || animal.getRaca().equalsIgnoreCase(raca)))
                .filter(animal -> (idade == null || animal.getIdadeAnimal().equals(idade+"")))
                .toList();

        if (animais.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/")
    public ResponseEntity<List<Animal>> listarAnimais() {
        List<Animal> animais = animalImpl.listarAnimais();
        if (animais.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(animais);
    }
}