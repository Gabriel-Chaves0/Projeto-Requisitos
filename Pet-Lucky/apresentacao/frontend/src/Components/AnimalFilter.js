import Button from './Button';
import { motion } from 'framer-motion';
import { useForm } from "react-hook-form";

const AnimalFilter = ({ onFilter }) => {
  const { register, handleSubmit, formState: { errors } } = useForm({
    mode: 'onBlur',
    reValidateMode: 'onChange',
  });

  const onSubmit = (data) => {
    console.log(data); // Dados enviados pelo formulário
    onFilter(data); // Chama a função de filtro com os dados selecionados
  };

  return (
    <motion.section
      className='animal-filter'
      initial={{ width: 0 }}
      animate={{ width: "auto", transition: { duration: 0.5 } }}
      exit={{ x: window.innerWidth, transition: { duration: 0.5 } }}
    >
      <p>Filtre os animais disponíveis no abrigo:</p>
      <form onSubmit={handleSubmit(onSubmit)}>
        <label htmlFor="species">Espécie</label>
        <select id="species" {...register("species")}>
          <option value="">Selecione</option>
          <option value="cachorro">Cachorro</option>
          <option value="gato">Gato</option>
          <option value="outros">Outros</option>
        </select>
        {errors.species && <p className="error">{errors.species.message}</p>}

        <label htmlFor="breed">Raça</label>
        <input
          id="breed"
          type="text"
          {...register("breed", { maxLength: { value: 40, message: 'Máximo de 40 caracteres' } })}
          placeholder='Digite a raça'
        />
        {errors.breed && <p className="error">{errors.breed.message}</p>}

        <label htmlFor="gender">Sexo</label>
        <select id="gender" {...register("gender")}>
          <option value="">Selecione</option>
          <option value="macho">Macho</option>
          <option value="femea">Fêmea</option>
        </select>
        {errors.gender && <p className="error">{errors.gender.message}</p>}

        <label htmlFor="size">Porte</label>
        <select id="size" {...register("size")}>
          <option value="">Selecione</option>
          <option value="pequeno">Pequeno</option>
          <option value="medio">Médio</option>
          <option value="grande">Grande</option>
        </select>
        {errors.size && <p className="error">{errors.size.message}</p>}

        <Button type="submit" children="Filtrar" />
      </form>
    </motion.section>
  );
};

export default AnimalFilter;
