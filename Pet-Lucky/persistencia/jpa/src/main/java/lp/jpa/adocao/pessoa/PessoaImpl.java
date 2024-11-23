package lp.jpa.adocao.pessoa;

import lp.adocao.dominio.pessoa.IPessoaRepositorio;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaImpl implements IPessoaRepositorio {
    @Autowired
    PessoaJpaRepositorio repositorio;

    @Override
    public void salvar(Pessoa pessoa) {

    }

    @Override
    public void editar(Pessoa pessoa) {

    }

    @Override
    public Pessoa obterPorId(IdPessoa idPessoa) {
        return null;
    }

    @Override
    public Pessoa buscarPorCPF(String cpf) {
        return null;
    }
}
