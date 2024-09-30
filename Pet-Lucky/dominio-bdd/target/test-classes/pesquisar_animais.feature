Feature: Pesquisa de Animais para Adoção

  Scenario: Usuário pesquisa por um cachorro em São Paulo
    Given existem animais disponíveis para adoção
    When o usuário pesquisa por "Cachorro" em "São Paulo"
    Then o sistema deve exibir o animal "Rex"

  Scenario: Usuário pesquisa por um gato no Rio de Janeiro
    Given existem animais disponíveis para adoção
    When o usuário pesquisa por "Gato" em "Rio de Janeiro"
    Then o sistema deve exibir o animal "Mia"
