package lp.apresentacao.adocao.animal;


import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IdAnimal;
import lp.jpa.adocao.animal.AnimalImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animais")
public class AnimalControler {

    private AnimalImpl animalImpl;

    public AnimalControler(AnimalImpl animalImpl) {
        this.animalImpl = animalImpl;
    }

    @PostMapping
    public ResponseEntity<String> salvarAnimal(@RequestBody Animal animal) {
        animalImpl.salvar(animal);
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

    @PutMapping("/{id}")
    public ResponseEntity<String> editarAnimal(@PathVariable("id") int id, @RequestBody Animal animal) {
        if (animalImpl.obterPorId(new IdAnimal(id)) != null) {
            animalImpl.editar(animal);
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
}