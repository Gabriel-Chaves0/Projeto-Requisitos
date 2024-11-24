package lp.apresentacao.adocao.abrigo;


import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.repositorioGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/petLuck")
public class AdocaoControler {

    @Autowired
    private repositorioGenerico abrigoGenericRepository;

    @PostMapping("/abrigo")
    public ResponseEntity<Abrigo> salvarAbrigo(@RequestBody Abrigo abrigo) {
        abrigoGenericRepository.salvar(abrigo);
        return new ResponseEntity<>(abrigo, HttpStatus.CREATED);
    }

    @PutMapping("/abrigo/{id}")
    public ResponseEntity<Abrigo> editarAbrigo(@PathVariable IdAbrigo id, @RequestBody Abrigo abrigo) {
        Abrigo abrigoExistente = abrigoGenericRepository.obterPorId(id);
        if (abrigoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        abrigoGenericRepository.editar(abrigo);
        return new ResponseEntity<>(abrigo, HttpStatus.OK);
    }

    @DeleteMapping("/abrigo/{id}")
    public ResponseEntity<Void> removerAbrigo(@PathVariable IdAbrigo id) {
        Abrigo abrigoExistente = abrigoGenericRepository.obterPorId(id);
        if (abrigoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        abrigoGenericRepository.remover(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/abrigos")
    public List<Abrigo> listarAbrigos() {
        return abrigoGenericRepository.listarAbrigos();
    }
}