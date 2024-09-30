Feature: Inscrição como Voluntário

  Scenario: Usuário se inscreve como voluntário em um abrigo
    Given existe um abrigo chamado "Abrigo Esperança"
    And o usuário "Maria" está cadastrado no sistema
    When o usuário "Maria" do CPF "123456789" se inscreve como voluntária no "Abrigo Esperança"
    Then o sistema deve registrar a inscrição de "Maria" do CPF "123456789" como voluntária no "Abrigo Esperança"

  Scenario: Usuário tenta se inscrever em um abrigo inexistente
    Given não existe um abrigo com o nome "Abrigo Inexistente"
    When o usuário "Carlos" tenta se inscrever como voluntário no "Abrigo Inexistente"
    Then o sistema deve informar que o abrigo não foi encontrado