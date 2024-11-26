package lp.apresentacao.adocao.abrigo;

import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.apresentacao.adocao.abrigo.dto.AbrigoDTO;
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
    public ResponseEntity<Abrigo> salvarAbrigo(@RequestBody AbrigoDTO abrigoDto) {
        var abrigo = abrigoDto.toAbrigo();
        abrigoImpl.salvar(abrigo);
        return new ResponseEntity<>(abrigo, HttpStatus.CREATED);
    }


    @PutMapping("/abrigo/{id}")
    public ResponseEntity<Abrigo> editarAbrigo(@PathVariable int id, @RequestBody AbrigoDTO abrigoDTO) {
        Abrigo abrigoExistente = abrigoImpl.obterPorId(new IdAbrigo(id));
        if (abrigoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var abrigo = abrigoDTO.toAbrigo();
        abrigoImpl.editar(abrigo);
        return new ResponseEntity<>(abrigo, HttpStatus.OK);
    }


    @DeleteMapping("/abrigo/{id}")
    public ResponseEntity<Void> removerAbrigo(@PathVariable int id) {
        Abrigo abrigoExistente = abrigoImpl.obterPorId(new IdAbrigo(id));
        if (abrigoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        abrigoImpl.remover(new IdAbrigo(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/abrigos")
    public List<Abrigo> listarAbrigos() {
        return abrigoImpl.listarAbrigos();
    }


    @GetMapping("/abrigos/{id}")
    public List<Animal> recomendarAnimais(@PathVariable int id) {
        return adocaoService.recomendarAnimais(new IdPessoa(id));
    }

}