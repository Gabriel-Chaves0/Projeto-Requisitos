Feature: Exibição de Animais Adotados

  Scenario: Usuário visualiza a lista de animais adotados recentemente
    Given os seguintes animais foram adotados recentemente:
      | Nome | Espécie  | Adotante |
      | Rex  | Cachorro | João     |
      | Mia  | Gato     | Maria    |
    When o usuário acessa a página de animais adotados
    Then o sistema deve exibir os seguintes animais adotados:
      | Nome | Adotante |
      | Rex  | João     |
      | Mia  | Maria    |

  Scenario: Não há animais adotados recentemente
    Given nenhum animal foi adotado recentemente
    When o usuário acessa a página de animais adotados
    Then o sistema deve exibir a mensagem "Nenhum animal foi adotado recentemente"
