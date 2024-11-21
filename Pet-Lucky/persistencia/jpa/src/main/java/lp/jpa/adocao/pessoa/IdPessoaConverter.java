package lp.jpa.adocao.pessoa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lp.adocao.dominio.pessoa.IdPessoa;

@Converter(autoApply = true)
public class IdPessoaConverter implements AttributeConverter<IdPessoa, Integer> {

    @Override
    public Integer convertToDatabaseColumn(IdPessoa idPessoa) {
        return idPessoa != null ? idPessoa.getId() : null;
    }

    @Override
    public IdPessoa convertToEntityAttribute(Integer id) {
        return id != null ? new IdPessoa(id) : null;
    }
}
