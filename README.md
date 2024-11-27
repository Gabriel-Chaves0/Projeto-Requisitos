# PetLucky
Projeto desenvolvido para a cadeira de Requisitos e validação.

Link da primeira apresentação: https://www.canva.com/design/DAGSUmmFmOs/dOEaYMEEJ6nssUKCpDOFUg/edit?utm_content=DAGSUmmFmOs&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

Link do protótipo de baixa: https://www.figma.com/design/otEKCscFUeuIqu5JOpJV2F/PetLucky?node-id=0-1&t=DaZmUX2Lfgb6pMRI-1

Link do avion: [https://petlucky.avion.io/map/efaHJsnXYA4otFRp9](https://petlucky.avion.io/share/ZoGzTXMGRp7YcsxcJ)

## Dominio
O sistema PetLucky tem como principal objetivo conectar pessoas interessadas em adotar algum animal de estimação e os responsáveis pela adoção deles. O sistema é composto por diversas funcionalidades para melhorar a experiência do usuário como: recomendação de animais de acordo com o perfil do adotante, relatórios de acompanhamento dos status do animal, pesquisas de animais com filtros (raça, comportamento, proximidade...), agenda de visitas ao abrigo, avaliação de adotantes e abrigos, planos de cuidados personalizados, gestão de doações e voluntariado e integração com redes sociais.

O projeto abrange todos os envolvidos no processo da adoção do animal, ou seja: abrigos responsáveis por disponibilizar os animais, os usuários adotantes ou interessados em adotar, os animais envolvidos e os responsáveis pela divulgação dos animais.

A principal missão do nosso sistema é facilitar e agilizar esse processo, mantendo um alto cuidado com os animais e oferecendo suporte quando necessário a todos os envolvidos nesse meio. Criando um ambiente seguro e acolhedor, onde cada adoção seja feita com responsabilidade e transparência, garantindo que os animais encontrem lares amorosos e adequados, enquanto os adotantes têm acesso a toda a orientação e apoio necessários para cuidar de seus novos companheiros.

## Padrões de projetos adotados
### 1. Singleton: JpaMapeador
O padrão Singleton foi implementado na classe ```JpaMapeador```, localizada em ```persistencia/jpa/src/main/java/lp/jpa/adocao/JpaMapeador.java```. Este padrão é utilizado para garantir que apenas uma instância do mapeador JPA esteja disponível durante a execução da aplicação. Ele é útil para centralizar a configuração e mapeamento de objetos de domínio para entidades persistentes, promovendo consistência e evitando a criação desnecessária de múltiplas instâncias.

### 2. Strategy: Filtros no Controller de Animal
O padrão Strategy é aplicado no controle de pesquisas de animais, com diferentes estratégias de filtro, como por idade, nome e raça. As implementações podem ser encontradas nas seguintes classes:

- ```PesquisarAnimalStrategy```: Define a interface base para as estratégias de pesquisa.
- ```PesquisaPorIdade```: Implementa a estratégia para filtrar animais por idade.
- ```PesquisaPorNome```: Implementa a estratégia para filtrar animais por nome.
- ```PesquisaPorRaca```: Implementa a estratégia para filtrar animais por raça.

Essas estratégias são gerenciadas pela classe ContextoPesquisaAnimal, que permite aplicar múltiplos filtros de maneira flexível. A abordagem facilita a manutenção e a extensão do código, já que novas formas de pesquisa podem ser adicionadas sem modificar o comportamento existente.

## Requisitos da 1ª entrega
- Descrição do dominio
- Mapa de contexto
- Mapa historias dos usuarios
- Prototipo de baixa
- Modelos dos subdominios desenvolvidos com CM
- Cenários BBD
- Automação dos cenários BDD
- Adotar os níveis preliminar DDD
- Arquiquetura limpa
- Camada de persistência e memória

## Requisitos da 2ª entrega
- Os mesmos da 1ª entrega 
- Adotar 2 ou mais padrões de projeto
- Implementar a camada de persistencia com mapeamento objeto relacional
- Implementar a camada de apresentação web
