# ENTREVISTAS TÉCNICAS

## RESPONSABILIDADES E ATRIBUIÇÕES
1. Solucionar problemas técnicos.
2. Seguir políticas e processos definidos pelo C6;
3. Codificar testes unitários, funcionais, performance, seguindo diretrizes de Qualidade.
4. Resolver problemas em produção em incidentes que impactam os clientes.
5. Instrumentar as aplicações para extração de informações de performance, logs de funcionamento e métricas.


## REQUISITOS E QUALIFICAÇÕES

### Hard Skill:
1. [Linguagem Kotlin](#kotlin)
2. [Plataforma de conteinerização Docker](#docker);
3. [Orquestração de containers Kubernetes](#kubernetes);
4. [Plataformas Apigee e NodeJs (gerenciamento server-side)](#apigee).
Um provedor de software de gerenciamento de API e análise preditiva antes de sua fusão com o Google Cloud.

### Soft Skill:
1. Capacidade de comunicação clara e objetiva; 
2. Espírito de equipe e capacidade de resolução de conflitos; 
3. Habilidade de trabalhar em equipes ágeis e multidisciplinares; 
4. Criatividade e proatividade para imaginar diferentes cenários de uso das aplicações.

### Seria legal se você tivesse:

1. [Experiência com a linguagem Java](#java);

2. [Vivência com Framework Spring Boot](#spring);

3. [Experiência em Arquitetura de Microsserviços](#microsservices);

4. [Boas práticas de desenvolvimento de software – TDD, DDD e Clean Code](#tdd);

5. [Boas Práticas em Frameworks ágeis: SCRUM e KANBAN](#agil);

6. [Instanciamento virtual de servidores AWS EC2](#aws);

7. [Vivência com Banco de Dados SQL e NoSQL](#banco);

8. [Experiência com Protocolo HTTP](#http)

9. [Experiência Gerenciador de código GIT Flow](#git).


<a id="kotlin"></a>
## KOTLIN

### Vantagens:
* Menos é mais: Menos Verbosidade (código fica mais legível e também agiliza o processo de programação);
* Quem nunca teve erro de ponto e vírgula;
*  Interoperável com Java: Kotlin permite a utilização das bibliotecas e frameworks Java. Assim, todo conhecimento acumulado com o desenvolvimento em Java não é desperdiçado.
*  Null safety: eliminação NullPointerException.

### Desvantagens:
* Flutuação na velocidade de compilação;
* Falta de fonte de conhecimento
* Compartilhamento: a comunidade de suporte ainda é pequena, em comparação com Java, há poucos tutoriais e ajudas disponíveis nessa linguagem.

### Sintaxe
    1. Variaveis
     - var 
     - val
    2. Null Safety
     - ? (elvis operator)
    3. Classes
     - Tudo é função
    4. Controle de Fluxo (estrutura de decisão)
     - When Statement e Expression;
     - Loops for, while, do while e iterator;
    5. Coleções
     - Listas (lists)
     - Conjuntos (Sets)
     - Mapas (Maps)
    6. OO Kotlin
     - Herança com contrutor com parametros, herança uzando a super class;
     - data class
     - Enums
    7. Funções de Scopo
     - Let
     - Run
     - Wiht
     - Apply
     - Also

## DOCKER
<a id="docker"></a>
### Container
Os contêineres são uma tecnologia usada para reunir um aplicativo e todos
os seus arquivos necessários em um ambiente de tempo de execução. Como
uma unidade, o contêiner pode ser facilmente movido e executado em
qualquer sistema operacional, em qualquer contexto.

### Docker
Orquestração dos containers como se fossem máquinas
virtuais modulares e extremamente leves.

### Alguns comandos:
1. docker run
2. docker pull
3. docker stop
4. docker start
5. docker ps
6. docker build
7. docker exec
8. dcoker images

<a id="kubernetes"></a>
## KUBERNETES
### Conceito
Plataforma de gerenciamento desenvolvida para gerir a configuração de aplicações em contêineres.
### Vantagens
Kubernetes se destaca pela capacidade de automação e as facilidades que pode trazer à rotina de trabalho.

1. Automatiza implantações e atualizações de aplicações; 
2. Oferece velocidade para escalar aplicativos em contêineres; 
3. Consegue operar contêineres em múltiplos hosts; 
4. Permite o uso do hardware de forma otimizada, o que ajuda a economizar recursos. 
5. Não limita o tipo de aplicativos suportados. Kubernetes oferece suporte a uma carga diversificada de aplicações; 
6. Fluxos de trabalho de integração e entrega são determinados pela cultura de preferência da organização; 
7. Não dita as soluções, e sim fornece algumas integrações e mecanismos para coletar dados e métricas; 
8. Não fornece (e nem exige) idioma ou linguagem específica para a configuração dos sistemas.

### Estrutura
1. **Master**: é o centro de tudo. É onde roda a API e os componentes mais importantes que gerenciam o cluster, que por sua vez executa os contêineres; 
2. **Nodes**: são máquinas virtuais ou físicas que captam as instruções da Master e processam o acesso às aplicações; 
3. **Pods**: menor unidade do Kubernetes onde rodam os contêineres; 
4. **Deployments**: ajudam a controlar e organizar o deploy dos pods. Pode conter instruções sobre o ambiente, mapeamento de volumes e tags; 
5. **Services**: fase responsável por organizar os pods executados a partir de tags


<a id="apigee"></a>
## APIGEE E NODEJS - (gerenciamento server-side) - AWS CLI
Ferramenta nativa de gerenciamento de APIs do Google Cloud para criar, gerenciar e proteger APIs para qualquer caso de uso, ambiente ou escalonamento



<a id="java"></a>
## JAVA

### PILARES ORIENTAÇÃO A OBJETO
- **Abstração**.
- **Encapsulamento**.
- **Herança**.
- **Polimorfismo**.

### S.O.L.I.D: 
    S — Single Responsiblity Principle (Princípio da responsabilidade única)
    O — Open-Closed Principle (Princípio Aberto-Fechado)
    L — Liskov Substitution Principle (Princípio da substituição de Liskov)
    I — Interface Segregation Principle (Princípio da Segregação da Interface)
    D — Dependency Inversion Principle (Princípio da inversão da dependência)

<a id="microsservices"></a>
## Arquitetura Microsserviçes

### Monolito:
    É um sistema único, não dividido, que roda em um único processo, uma aplicação de software 
    em que diferentes componentes estão ligados a um único programa dentro de uma única plataforma
    Vantagens:
      1 - Mais simples de desenvolver;
      2 - Simples de testar;
      3 - Simples de fazer o deploy para o servidor;
      4 - Simples de escalar.
    Desvantagens:
      1 - Manutenção;
      2 - Alterações;
      3 - Linguagens de programação: não há flexibilidade em linguagens de programação. 
### Microsservices:
      É uma coleção de serviços ou divisão do monolito.
    Vantagens:
      1 - Altamente testável e manutenível: tudo é feito de forma separada e mais rápida;
      2 - Independência e agilidade: os deploys de cada microsserviço são totalmente independentes
          e mais rápidos
      3 - Objetividade: a organização é feita de acordo com a organização do produto e do negócio
      4 - Flexibilidade: é possível dividir em equipes para trabalhar de forma separada 
       e totalmente independente em cada serviço.
    Desvantagens:
      1 - Complexidade no gerenciamento da aplicação: 
      2 - Complexidade no gerenciamento da aplicação:

### Arquitetura MVC:
    M – model (modelo) -> manipulação dos dados;
    V – view (visualização) -> interação do usuário;
    C – controller (controlador).
### Arquitetura Hexagonal:
    Composição:
      - Classes de Dominio: Regra de Negocios;
      - Classes de Infra: integração dos serviços externos.
    Vantagens: Acoplamentos e desacoplamnetos de serviços externos

<a id="tdd"></a>
## TDD, DDD e Clean Code
### TDD - Desenvolvimento Orientado por Testes
      1 - Escreva o teste
      2 - Implemente o codigo
      3 - Elimine a redundancia
      Os principais benefícios do TDD são:
         1 - Maior cobertura de testes unitários
         2 - Testes são executados com maior frequência
         3 - O código se torna mais limpo
### DDD - Desenvolvimento Orientado a dominio
    
    - Pair programming
    - Code Review
#### Arquitetura MVC:
    M – model (modelo) -> manipulação dos dados;
    V – view (visualização) -> interação do usuário;
    C – controller (controlador).
#### Arquitetura Hexagonal:
    Composição:
      - Classes de Dominio: Regra de Negocios;
      - Classes de Infra: integração dos serviços externos.
    Vantagens: Acoplamentos e desacoplamnetos de serviços externos
### CLEAN CODE
#### O que um clean code deve ser?
    * Eficiente
    * Elegante
    * Atenção aos detalhes:
    * Atenção aos comentários:

### Exceptions:
    RestControllerAdvice - listener (ouvindo a aplicação)
    - NotFound
    - NotValid
    - BadRequest

### Observability
     - Paineis e Logs Grafana, Grafana Loki
     - Prometheus
     - actuator
     - Cloud Watch

<a id="spring"></a>
## SPRINGBOOT
### Ferramentas
    - Web-Services
    - Data
    - Test
    - Authorization
    - Session
    - Security
    - Open Feign

    LOMBOK
      - menos verbosidade e trabalho
      - @Builder: Facilita construção de objetos
      - @Data: (toString, Getter e Setter, EqualeHasCode)
      - NoArgsContruct: Não aceita contrutores
      - AllArgsContruct: construtores com argumento

<a id="agil"></a>
## SCRUM e KANBAN
    - Scrum e Kanban
    - Ferramentas: Jira
    - Sprint: 15 dias
    - Cerimonias: Daily, Retro, Review, Refinamentos.
    - Comunicação, transparência, organização.


<a id="aws"></a>
## AWS

    - EC2 - Servidores
    - SQS - permite que você envie, armazene e receba mensagens entre componentes de software.
    - SNS - Serviço de notificação,
    - STEP FUNCTIONS (SERVELESS - computação sem servidor),
    - LAMBDAS EM PYTHON,
    - ATHENA
    - RDS AWS
    - DYNAMODB
    - REDIS
    - JIB - ferramenta do google para containerizar seu projeto maven
    # Cloud practitioner

<a id="banco"></a>
## BANCO DE DADOS RELACIONAIS E NÃO RELACIONAIS (NOSQL):
### Diferenças
**Bancos de dados relacionais** são ideais para aplicações que exigem consistência e integridade de dados:
**Mysql, SQLServer, Postgres, H2 (memoria).**

**Bancos de dados não relacionais NoSql**  são mais adequados para aplicações que exigem alta escalabilidade e flexibilidade no esquema de dados:
**MongoDB, DynamoDB, Redis.**

### Migrations
- É o versionamento do schema de sua aplicação;
- Controle que você crie, altere ou exclua tabelas, colunas, índices e outros elementos do banco de dados de forma consistente e controlada.

<a id="http"></a>
## PROTOCOLO HTTP
### Verbos http
    - POST 
    - GET 
    - DELETE
    - PUT 
    - PATCH
### Http status
    - Respostas de sucesso:
        200 OK
        201 Created - POST
        202 Accepted
        204 No Content - DELETE

    - Respostas de redirecionamento:
        300 Multiple Choices

    - respostas de erro:
        400 Bad Request
        401 Unauthorized
        403 Forbidden
        404 Not Found
        406 Not Acceptable
        422 Unprocessable Entity

    - respostas de erro do Servidor
        500 Internal Server Error
        501 Not Implemented
        502 Bad Gateway

<a id="git"></a>
## VERSIONAMENTO DE CÓDIGO
    - GitHub e GitLab;
    - Esteira de CI e CD;
    - 03 branches no processo: Dev, Hom e Prod;
    - Tinha que passar por aprovações de outros devs (Code Review) e QA (testes estresse ou carga) 
     e aprovação de gestores (prod);
    - ambas esteira cobertura de 92% (ferramentas Sonar (cobertura) e Fortfy (vulnerabilidades)

   - hotfix:
É parecido com um patch mas corrige de forma imediata e sem precisar desligar ou interromper o sistema
   - Coldfix: Iniciar o serviço
   - bugfix:
É um tipo de falha que geralmente ocorre durante a criação ou desenvolvimento do programa