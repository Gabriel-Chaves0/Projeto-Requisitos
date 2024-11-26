import { useState } from 'react';

const AbrigoCard = ({ img, name, city, street }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    nome: '',
    email: '',
    celular: '',
    rua: '',
    cidade: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Dados enviados:', formData);
    setIsModalOpen(false);
    alert(`Voluntário inscrito com sucesso no ${name}!`);
  };

  return (
    <div className='card'>
      <img src={img} alt={`Imagem do ${name}`} />
      <h4>{name}</h4>
      <ul>
        <li>{city}</li>
        <li>{street}</li>
      </ul>
      <p className='card__city'>{city}</p>
      <button className="volunteer-button" onClick={() => setIsModalOpen(true)}>
        Inscrever Voluntário
      </button>

      {/* Modal */}
      {isModalOpen && (
        <div className="modal">
          <div className="modal-content">
            <h3>Inscrever Voluntário para {name}</h3>
            <form onSubmit={handleSubmit}>
              <input
                type="text"
                name="nome"
                placeholder="Nome"
                value={formData.nome}
                onChange={handleChange}
                required
              />
              <input
                type="email"
                name="email"
                placeholder="Email"
                value={formData.email}
                onChange={handleChange}
                required
              />
              <input
                type="tel"
                name="celular"
                placeholder="Celular"
                value={formData.celular}
                onChange={handleChange}
                required
              />
              <input
                type="text"
                name="rua"
                placeholder="Rua"
                value={formData.rua}
                onChange={handleChange}
                required
              />
              <input
                type="text"
                name="cidade"
                placeholder="Cidade"
                value={formData.cidade}
                onChange={handleChange}
                required
              />
              <div className="modal-buttons">
                <button type="submit" className="submit-button">Enviar</button>
                <button type="button" className="close-button" onClick={() => setIsModalOpen(false)}>
                  Cancelar
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default AbrigoCard;
