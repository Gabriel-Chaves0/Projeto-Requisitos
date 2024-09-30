package lp.adocao.dominio.pessoa;

public interface IPessoaRepositorio {
    void salvar(Pessoa pessoa);
    void editar(Pessoa pessoa);
    Pessoa obterPorId(IdPessoa idPessoa);

    Pessoa buscarPorCPF(String cpf);
}
