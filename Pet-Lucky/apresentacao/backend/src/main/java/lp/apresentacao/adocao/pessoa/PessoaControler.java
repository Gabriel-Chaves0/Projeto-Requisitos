package lp.apresentacao.adocao.pessoa;


import lp.adocao.dominio.pessoa.Pessoa;
import lp.adocao.dominio.pessoa.IdPessoa;

import lp.apresentacao.adocao.pessoa.dto.PessoaDTO;
import lp.jpa.adocao.pessoa.PessoaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pessoas")
public class PessoaControler {

    @Autowired
    private PessoaImpl pessoaImpl;

    @Autowired
    public PessoaControler(PessoaImpl pessoaImpl) {
        this.pessoaImpl = pessoaImpl;
    }

    @PostMapping
    public ResponseEntity<String> salvarPessoa(@RequestBody PessoaDTO pessoa) {
        pessoaImpl.salvar(pessoa.toPessoa());
        return ResponseEntity.ok("Pessoa Salvo com sucesso!");
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId(@PathVariable("id") int id) {
        Pessoa pessoa = pessoaImpl.obterPorId(new IdPessoa(id));

        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity<String> editarPessoa(@PathVariable("id") int id, @RequestBody PessoaDTO pessoa) {
        if (pessoaImpl.obterPorId(new IdPessoa(id)) != null) {
            pessoaImpl.editar(pessoa.toPessoa());
            return ResponseEntity.ok("Pessoa Editado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
    }

    @GetMapping("cpf/{cpf}")
    public ResponseEntity<Pessoa> obterPessoaPorCpf(@PathVariable("cpf") String cpf) {
        Pessoa pessoa = pessoaImpl.buscarPorCPF(cpf);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> obterPessoas() {
        List<Pessoa> pessoas = pessoaImpl.listarPessoas();
        return ResponseEntity.ok(pessoas);
    }

}
