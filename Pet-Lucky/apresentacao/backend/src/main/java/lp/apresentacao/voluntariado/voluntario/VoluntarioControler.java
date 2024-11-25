package lp.apresentacao.voluntariado.voluntario;

import lp.adocao.dominio.pessoa.IdPessoa;
import lp.voluntariado.dominio.voluntario.Voluntario;
import lp.jpa.voluntariado.VoluntarioImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/voluntarios")
public class VoluntarioControler {

    private VoluntarioImpl voluntarioImpl;

    public VoluntarioControler(VoluntarioImpl voluntarioImpl) {
        this.voluntarioImpl = voluntarioImpl;
    }

    @PostMapping
    public ResponseEntity<String> salvarVoluntario(@RequestBody Voluntario voluntario) {
        voluntarioImpl.salvar(voluntario);
        return ResponseEntity.ok("Voluntario salvo com sucesso!");
    }

    @PutMapping
    public ResponseEntity<String> editarVoluntario(@PathVariable("id") int id, @RequestBody Voluntario voluntario) {
        if (voluntarioImpl.obterVoluntarioPorId(new IdPessoa(id)) != null) {
            voluntarioImpl.editar(voluntario);
            return ResponseEntity.ok("Voluntario editado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Voluntario nao encontrado");

    }

    @GetMapping
    public ResponseEntity<List<Voluntario>> listarVoluntarios() {
        List<Voluntario> voluntarios = voluntarioImpl.listarVoluntarios();
        return ResponseEntity.ok(voluntarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voluntario> obterVoluntarioPorId(@PathVariable("id") int id) {
        Voluntario voluntario = voluntarioImpl.obterVoluntarioPorId(new IdPessoa(id));

        if (voluntario != null) {
            return ResponseEntity.ok(voluntario);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}