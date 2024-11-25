package lp.jpa.adocao;

import lp.jpa.adocao.pessoa.IdPessoaConverter;
import lp.jpa.voluntariado.VoluntarioJpa;
import lp.voluntariado.dominio.voluntario.Voluntario;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.adocao.dominio.animal.Animal;
import lp.adocao.dominio.animal.IdAnimal;
import lp.adocao.dominio.animal.SaudeAnimal;
import lp.adocao.dominio.comuns.Contato;
import lp.adocao.dominio.comuns.Endereco;
import lp.adocao.dominio.comuns.Preferencias;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.pessoa.Pessoa;
import lp.jpa.adocao.abrigo.AbrigoJpa;
import lp.jpa.adocao.animal.AnimalJpa;
import lp.jpa.adocao.animal.SaudeAnimalJpa;
import lp.jpa.adocao.comuns.ContatoJpa;
import lp.jpa.adocao.comuns.EnderecoJpa;
import lp.jpa.adocao.comuns.PreferenciasJpa;
import lp.jpa.adocao.pessoa.PessoaJpa;

import java.util.Date;
import java.util.List;

@Component
public class JpaMapeador extends ModelMapper {


    JpaMapeador() {
        var configuracao = getConfiguration();
        configuracao.setFieldMatchingEnabled(true);
        configuracao.setFieldAccessLevel(AccessLevel.PRIVATE);
        IdPessoaConverter idPessoaConverter = new IdPessoaConverter();


        // Conversores para Abrigo
        addConverter(new AbstractConverter<AbrigoJpa, Abrigo>() {
            @Override
            protected Abrigo convert(AbrigoJpa source) {
                return new Abrigo(
                        new IdAbrigo(source.getId().intValue()),
                        source.getNomeAbrigo(),
                        map(source.getEnderecoAbrigo(), Endereco.class),
                        map(source.getContatoAbrigo(), Contato.class),
                        source.getCapacidadeAbrigo()
                );
            }
        });

        addConverter(new AbstractConverter<Abrigo, AbrigoJpa>() {
            @Override
            protected AbrigoJpa convert(Abrigo source) {
                AbrigoJpa abrigoJpa = new AbrigoJpa();
                abrigoJpa.setId((long) source.getIdAbrigo().getId());
                abrigoJpa.setNomeAbrigo(source.getNomeAbrigo());
                abrigoJpa.setEnderecoAbrigo(map(source.getEnderecoAbrigo(), EnderecoJpa.class));
                abrigoJpa.setContatoAbrigo(map(source.getContatoAbrigo(), ContatoJpa.class));
                abrigoJpa.setCapacidadeAbrigo(source.getCapacidadeAbrigo());
                return abrigoJpa;
            }
        });

        // Conversores para Animal
        addConverter(new AbstractConverter<AnimalJpa, Animal>() {
            @Override
            protected Animal convert(AnimalJpa source) {
                return new Animal(
                        new IdAnimal(source.getId().intValue()),
                        new IdAbrigo(source.getAbrigo().getId().intValue()),
                        source.getAdotante() != null ? new IdPessoa((int) source.getAdotante().getId()) : null,
                        map(source.getSaude(), SaudeAnimal.class),
                        source.getNomeAnimal(),
                        source.getIdadeAnimal(),
                        source.getEspecie(),
                        source.getRaca(),
                        source.getPorte(),
                        source.getSexo()
                );
            }
        });

        addConverter(new AbstractConverter<Animal, AnimalJpa>() {
            @Override
            protected AnimalJpa convert(Animal source) {
                AnimalJpa animalJpa = new AnimalJpa();
                animalJpa.setId((long) source.getIdAnimal().getId());
                animalJpa.setAbrigo(map(source.getIdAbrigo(), AbrigoJpa.class));
                animalJpa.setAdotante(source.getIdAdotante() != null ? map(source.getIdAdotante(), PessoaJpa.class) : null);
                animalJpa.setSaude(map(source.getSaudeAnimal(), SaudeAnimalJpa.class));
                animalJpa.setNomeAnimal(source.getNomeAnimal());
                animalJpa.setIdadeAnimal(source.getIdadeAnimal());
                animalJpa.setEspecie(source.getEspecie());
                animalJpa.setRaca(source.getRaca());
                animalJpa.setPorte(source.getPorte());
                animalJpa.setSexo(source.getSexo());
                return animalJpa;
            }
        });

        // Conversores para Pessoa
        addConverter(new AbstractConverter<PessoaJpa, Pessoa>() {
            @Override
            protected Pessoa convert(PessoaJpa source) {
                return new Pessoa(
                        new IdPessoa((int) source.getId()),
                        map(source.getEnderecoPessoa(), Endereco.class),
                        map(source.getContatoPessoa(), Contato.class),
                        source.getNomePessoa(),
                        source.getCpf(),
                        source.getDataPessoa(),
                        map(source.getPreferencias(), Preferencias.class)
                );
            }
        });

        addConverter(new AbstractConverter<Pessoa, PessoaJpa>() {
            @Override
            protected PessoaJpa convert(Pessoa source) {
                PessoaJpa pessoaJpa = new PessoaJpa();
                pessoaJpa.setId((long) source.getIdPessoa().getId());
                pessoaJpa.setEnderecoPessoa(map(source.getEnderecoPessoa(), EnderecoJpa.class));
                pessoaJpa.setContatoPessoa(map(source.getContatoPessoa(), ContatoJpa.class));
                pessoaJpa.setNomePessoa(source.getNomePessoa());
                pessoaJpa.setCpf(source.getCpf());
                pessoaJpa.setDataPessoa(source.getDataPessoa());
                pessoaJpa.setPreferencias(map(source.getPreferencias(), PreferenciasJpa.class));
                return pessoaJpa;
            }
        });

        // Conversores para Endereco e Contato
        addConverter(new AbstractConverter<EnderecoJpa, Endereco>() {
            @Override
            protected Endereco convert(EnderecoJpa source) {
                return new Endereco(source.getRua(), source.getCidade());
            }
        });

        addConverter(new AbstractConverter<Endereco, EnderecoJpa>() {
            @Override
            protected EnderecoJpa convert(Endereco source) {
                return new EnderecoJpa(source.getRua(), source.getCidade());
            }
        });

        addConverter(new AbstractConverter<ContatoJpa, Contato>() {
            @Override
            protected Contato convert(ContatoJpa source) {
                return new Contato(source.getEmail(), source.getTelefone());
            }
        });

        addConverter(new AbstractConverter<Contato, ContatoJpa>() {
            @Override
            protected ContatoJpa convert(Contato source) {
                return new ContatoJpa(source.getEmail(), source.getTelefone());
            }
        });

        // Conversores para Preferencias
        addConverter(new AbstractConverter<PreferenciasJpa, Preferencias>() {
            @Override
            protected Preferencias convert(PreferenciasJpa source) {
                return new Preferencias(
                        source.getEspecies(),
                        source.getRacas(),
                        source.getPortes(),
                        source.getSexos()
                );
            }
        });

        addConverter(new AbstractConverter<Preferencias, PreferenciasJpa>() {
            @Override
            protected PreferenciasJpa convert(Preferencias source) {
                return new PreferenciasJpa(
                        source.getEspecies(),
                        source.getRacas(),
                        source.getPortes(),
                        source.getSexos()
                );
            }
        });

        addConverter(new AbstractConverter<VoluntarioJpa, Voluntario>() {
            @Override
            protected Voluntario convert(VoluntarioJpa source) {
                Pessoa pessoa = new Pessoa(
                        new IdPessoa((int) source.getId()),
                        new Endereco("Rua desconhecida", "Cidade desconhecida"),
                        new Contato(source.getEmail(), source.getTelefone()),
                        source.getNome(),
                        "000000000",
                        new Date(),
                        null
                );

                // Certifique-se de inicializar a lista de abrigos associados corretamente
                List<Abrigo> abrigosAssociados = source.getAbrigosAssociados() != null
                        ? source.getAbrigosAssociados().stream()
                        .map(abrigoJpa -> map(abrigoJpa, Abrigo.class))
                        .toList()
                        : List.of();

                return new Voluntario(pessoa, abrigosAssociados);
            }
        });

        addConverter(new AbstractConverter<Voluntario, VoluntarioJpa>() {
            @Override

            protected VoluntarioJpa convert(Voluntario source) {
                // Mapear campos obrigatórios
                VoluntarioJpa voluntarioJpa = new VoluntarioJpa();
                voluntarioJpa.setId(idPessoaConverter.convertToDatabaseColumn(source.getIdPessoa()));
                voluntarioJpa.setNome(source.getNomePessoa());
                voluntarioJpa.setEmail(source.getContatoPessoa().getEmail());
                voluntarioJpa.setTelefone(source.getContatoPessoa().getTelefone());
                // Mapear abrigos associados
                List<AbrigoJpa> abrigosAssociados = source.getAbrigosAssociados() != null
                        ? source.getAbrigosAssociados().stream()
                        .map(abrigo -> map(abrigo, AbrigoJpa.class))
                        .toList()
                        : List.of();
                voluntarioJpa.setAbrigosAssociados(abrigosAssociados);
                return voluntarioJpa;
            }
        });

        addConverter(new AbstractConverter<SaudeAnimalJpa, SaudeAnimal>() {
            @Override
            protected SaudeAnimal convert(SaudeAnimalJpa source) {
                return new SaudeAnimal(
                        source.isVacinaRaiva(),
                        source.isCastrado(),
                        source.isVermifugado()
                );
            }
        });

        addConverter(new AbstractConverter<SaudeAnimal, SaudeAnimalJpa>() {
            @Override
            protected SaudeAnimalJpa convert(SaudeAnimal source) {
                SaudeAnimalJpa saudeAnimalJpa = new SaudeAnimalJpa();
                saudeAnimalJpa.setVacinaRaiva(source.isVacinaRaiva());
                saudeAnimalJpa.setCastrado(source.isCastrado());
                saudeAnimalJpa.setVermifugado(source.isVermifugado());
                return saudeAnimalJpa;
            }
        });

    }

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return source != null ? super.map(source, destinationType) : null;
    }


    public Class<List<Animal>> getTypeToken(Class<List> listClass, Class<Animal> animalClass) {
        return new TypeToken<List<Animal>>() {
            private static final long serialVersionUID = 1L;
        }.getRawType();
    }




}