package lb.voluntariado.dominio.voluntario;

import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.comuns.Contato;
import lp.adocao.dominio.comuns.Endereco;
import lp.adocao.dominio.comuns.Preferencias;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.pessoa.Pessoa;

import java.util.Date;
import java.util.List;

public class Voluntario extends Pessoa {

    private List<Abrigo> abrigosAssociados;

    public Voluntario(IdPessoa idPessoa, Endereco endereco, Contato contato, String nome, String cpf, Date data, Preferencias preferencias, List<Abrigo> abrigosAssociados) {
        super(idPessoa, endereco, contato, nome, cpf, data, preferencias);
        this.abrigosAssociados = abrigosAssociados;
    }

    public Voluntario(Pessoa pessoa, List<Abrigo> abrigosAssociados) {
        super(pessoa.getIdPessoa(), pessoa.getEnderecoPessoa(), pessoa.getContatoPessoa(), pessoa.getNomePessoa(), pessoa.getCpf(), pessoa.getDataPessoa(), pessoa.getTagsPersonalidadeDesejada());
        this.abrigosAssociados = abrigosAssociados;
    }

    public void associarAbrigo(Abrigo abrigo) {
        abrigosAssociados.add(abrigo);
    }

    public void desassociarAbrigo(Abrigo abrigo) {
        abrigosAssociados.remove(abrigo);
    }

    public List<Abrigo> getAbrigosAssociados() {
        return abrigosAssociados;
    }
}
