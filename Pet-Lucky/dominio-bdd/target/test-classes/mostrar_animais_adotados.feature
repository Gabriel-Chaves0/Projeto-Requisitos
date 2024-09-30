Feature: Exibição de Animais Adotados

  Scenario: Usuário visualiza a lista de animais adotados recentemente
    Given os animais "Rex" e "Mia" foram adotados recentemente
    When o usuário acessa a página de animais adotados
    Then o sistema deve exibir o animal adotado "Rex"
    And o sistema deve exibir o animal adotado "Mia"

  Scenario: Não há animais adotados recentemente
    Given nenhum animal foi adotado recentemente
    When o usuário acessa a página de animais adotados
    Then o sistema deve exibir a mensagem "Nenhum animal foi adotado recentemente"
