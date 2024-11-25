package lp.apresentacao.adocao.abrigo;

import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.apresentacao.adocao.abrigo.AdocaoService;
import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.jpa.adocao.abrigo.AbrigoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adocao")
public class AdocaoControler {

    @Autowired
    private AbrigoImpl abrigoImpl;

    @Autowired
    private AdocaoService adocaoService;

    @PostMapping("/abrigo")
    public ResponseEntity<Abrigo> salvarAbrigo(@RequestBody Abrigo abrigo) {
        abrigoImpl.salvar(abrigo);
        return new ResponseEntity<>(abrigo, HttpStatus.CREATED);
    }


    @PutMapping("/abrigo/{id}")
    public ResponseEntity<Abrigo> editarAbrigo(@PathVariable IdAbrigo id, @RequestBody Abrigo abrigo) {
        Abrigo abrigoExistente = abrigoImpl.obterPorId(id);
        if (abrigoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        abrigoImpl.editar(abrigo);
        return new ResponseEntity<>(abrigo, HttpStatus.OK);
    }


    @DeleteMapping("/abrigo/{id}")
    public ResponseEntity<Void> removerAbrigo(@PathVariable IdAbrigo id) {
        Abrigo abrigoExistente = abrigoImpl.obterPorId(id);
        if (abrigoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        abrigoImpl.remover(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/abrigos")
    public List<Abrigo> listarAbrigos() {
        return abrigoImpl.listarAbrigos();
    }


    @GetMapping("/abrigos/{id}")
    public List<Animal> recomendarAnimais(@PathVariable IdPessoa idPessoa) {
        return adocaoService.recomendarAnimais(idPessoa);
    }

}