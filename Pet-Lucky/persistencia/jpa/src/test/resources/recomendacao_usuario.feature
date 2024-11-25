Feature: Recomendações de Animais para o Usuário

  Scenario: Sistema recomenda animais com base nas preferências do usuário
    Given o usuário "João" prefere animais da espécie "Cachorro", da raça "Labrador" e de porte "Grande"
    And existem animais disponíveis como "Rex", "Bob" e "Mia"
    When o usuário "João" acessa as recomendações
    Then o sistema deve recomendar o animal "Rex"
    And o sistema não deve recomendar os animais "Bob" e "Mia"

