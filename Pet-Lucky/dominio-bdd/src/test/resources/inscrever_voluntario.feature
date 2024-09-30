Feature: Inscrição como Voluntário

  Scenario: Usuário se inscreve como voluntário em um abrigo
    Given existe um abrigo chamado "Abrigo Esperança"
    And o usuário "Maria" está cadastrado no sistema
    When o usuário "Maria" do CPF "123456789" se inscreve como voluntária no "Abrigo Esperança"
    Then o sistema deve registrar a inscrição de "Maria" do CPF "123456789" como voluntária no "Abrigo Esperança"
