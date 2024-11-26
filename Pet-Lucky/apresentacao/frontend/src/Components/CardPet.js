const CardPet = ({ nome, idade, sexo, porte, especie, img, index }) => {
  return (
    <div key={index} className='card'>
      <img src={img} alt={nome} />
      <h4>{nome}</h4>
      <ul>
        <li>{idade}</li>
        <li>{porte}</li>
        <li>{especie}</li>
        <li>{sexo}</li>
      </ul>
    </div>
  );
};

export default CardPet;
