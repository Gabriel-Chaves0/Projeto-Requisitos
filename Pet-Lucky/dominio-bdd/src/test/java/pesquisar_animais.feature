Feature: Pesquisar Animais para Adoção

  Scenario: Usuário pesquisa animais por espécie e localização
    Given existem os seguintes animais disponíveis para adoção:
      | Nome | Espécie  | Raça     | Localização     |
      | Rex  | Cachorro | Labrador | São Paulo       |
      | Mia  | Gato     | Siamês   | Rio de Janeiro  |
    When o usuário pesquisa por "Cachorro" em "São Paulo"
    Then o sistema deve exibir os seguintes animais:
      | Nome |
      | Rex  |
    And o sistema não deve exibir os seguintes animais:
      | Nome |
      | Mia  |

  Scenario: Usuário aplica múltiplos filtros na pesquisa
    Given existem os seguintes animais disponíveis para adoção:
      | Nome   | Espécie  | Raça     | Porte   | Localização |
      | Thor   | Cachorro | Labrador | Grande  | São Paulo   |
      | Luna   | Cachorro | Poodle   | Pequeno | São Paulo   |
    When o usuário pesquisa por "Cachorro" da raça "Labrador" de porte "Grande" em "São Paulo"
    Then o sistema deve exibir os seguintes animais:
      | Nome |
      | Thor |
    And o sistema não deve exibir os seguintes animais:
      | Nome  |
      | Luna  |
