package lp.apresentacao.adocao.pessoa;


import lp.adocao.dominio.pessoa.Pessoa;
import lp.adocao.dominio.pessoa.IdPessoa;

import lp.repositorioGenerico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pessoas")
public class PessoaControler {

    private repositorioGenerico repositorio;

    public PessoaControler(repositorioGenerico repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    public ResponseEntity<String> salvarPessoa(@RequestBody Pessoa pessoa) {
        repositorio.salvar(pessoa);
        return ResponseEntity.ok("Pessoa Salvo com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId(@PathVariable("id") int id) {
        Pessoa pessoa = repositorio.obterPorId(new IdPessoa(id));

        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarPessoa(@PathVariable("id") int id, @RequestBody Pessoa pessoa) {
        if (repositorio.obterPorId(new IdPessoa(id)) != null) {
            repositorio.editar(pessoa);
            return ResponseEntity.ok("Pessoa Editado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Pessoa> obterPessoaPorCpf(@PathVariable("cpf") String cpf) {
        Pessoa pessoa = repositorio.buscarPorCPF(cpf);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
