Feature: Recomendações de Animais para o Usuário

  Scenario: Sistema recomenda animais com base nas preferências do usuário
    Given o usuário "João" tem as seguintes preferências:
      | Espécie | Cachorro |
      | Raça    | Labrador |
      | Porte   | Grande   |
    And existem os seguintes animais disponíveis:
      | Nome | Espécie  | Raça     | Porte   |
      | Rex  | Cachorro | Labrador | Grande  |
      | Bob  | Cachorro | Poodle   | Pequeno |
      | Mia  | Gato     | Siamês   | Pequeno |
    When o usuário "João" acessa as recomendações
    Then o sistema deve recomendar os seguintes animais:
      | Nome |
      | Rex  |
    And o sistema não deve recomendar os seguintes animais:
      | Nome |
      | Bob  |
      | Mia  |
