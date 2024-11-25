package lp.adocao.dominio.pessoa;

import java.util.List;

public interface IPessoaRepositorio {
    void salvar(Pessoa pessoa);
    void editar(Pessoa pessoa);
    Pessoa obterPorId(IdPessoa idPessoa);

    Pessoa buscarPorCPF(String cpf);
    List<Pessoa> listarPessoas();
}
