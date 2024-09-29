package lp.adocao.dominio.pessoa;

public interface PessoaRepositorio {
    void salvar(Pessoa pessoa);
    void editar(Pessoa pessoa);
    Pessoa obterPorId(IdPessoa idPessoa);
}
