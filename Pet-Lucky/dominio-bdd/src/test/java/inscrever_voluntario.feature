Feature: Inscrição como Voluntário

  Scenario: Usuário se inscreve como voluntário em um abrigo
    Given existe um abrigo chamado "Abrigo Esperança"
    And o usuário "Maria" está cadastrado no sistema
    When o usuário "Maria" se inscreve como voluntária no "Abrigo Esperança"
    Then o sistema deve registrar a inscrição de "Maria" como voluntária no "Abrigo Esperança"
    And o abrigo deve ser notificado sobre o novo voluntário

  Scenario: Usuário tenta se inscrever em um abrigo inexistente
    Given não existe um abrigo com o nome "Abrigo Inexistente"
    When o usuário "Carlos" tenta se inscrever como voluntário no "Abrigo Inexistente"
    Then o sistema deve informar que o abrigo não foi encontrado
