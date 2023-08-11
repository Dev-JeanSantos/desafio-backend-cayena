# Rest API - Challenger Cayena
<p align="center">
     <a alt="Java">
        <img src="https://img.shields.io/npm/l/react" />
    </a>
     <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v11-blue.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v3.1.1-brightgreen.svg" />
    </a>
</p>

## Rest APIs with Spring Cloud and MySQL

## Requirements
- Spring
- Java 11
- Maven
- Mysql
- Git

## Settings
Development Environment

Para executar em desenvolvimento segue as instruções onde as etapas necessárias será clonar o repositório e iniciar a aplicação em
diferentes ambientes (Unix e Windows) com o perfil de dev ativado executando em um banco de dados Mysql dockerizado.

1. Clone o repositório: git clone `https://github.com/Dev-JeanSantos/desafio-backend-cayena`
2. Executar o comando docker: `docker run -d -p 3306:3306 --name mysql-container  -e MYSQL_ROOT_PASSWORD=root  -e  MYSQL_PASSWORD=root mysql:8.0.28`
3. Inicie a aplicação no ambiente Unix: `./mvn spring-boot:run  -Dspring-boot.run.profiles=dev`
4. Inicie a aplicação no ambiente Windows: `mvn spring-boot:run  -Dspring-boot.run.profiles=dev`


Seguem alguns links úteis:

1. [Documentação da API](https://ju-marketplace-8b8b843c329f.herokuapp.com/swagger-ui/index.html)
2. [Collection Postman](https://elements.getpostman.com/redirect?entityId=12860836-96e75533-1363-4b3f-a460-273578ebaa97&entityType=collection) (útil pra entender todas as rotas da API)

## Uso da API

> Request

### Finalizar uma compra:

- Método: `POST`
- Endpoint: `/api/v1/marketing/orders`

Parâmetros do body da chamada:
- `{id}`: Lista dos IDs de cada item da compra.
- `{payment}`: Formas de pagamento.

Exemplo de solicitação:

```http
POST /api/v1/marketing/orders
```

Exemplo de REQUEST:
```json
{
  "items": [
    {
      "idItem": 1,
      "idItem": 2,
      "idItem": 3,
      "idItem": 4
    }
  ],
  "payment": "DINHEIRO"
}
```

> Response

- Código de resposta: `200 OK`
- Corpo da resposta: `OrderResponseDTO` contendo o total da compra e o tipo do pagamento.

Exemplo de RESPONSE:

```json
{
  "totalSalePrice": 1350.00,
  "payment": "PIX"
}
```

#### Códigos de resposta possíveis:

- `200 OK`: Compra efetuada com sucesso e retorna os o valor total da compra e o tipo de pagamento.
- `422 Unprocessable Entity`: Alguns dos ids passado no body não existe na base de items do carrinho de compras.

## Documentação do Swagger

A documentação da API pode ser encontrada no Swagger. Para visualizá-la,
acesse: [Documentação do Swagger](https://ju-marketplace-8b8b843c329f.herokuapp.com/swagger-ui/index.html).

## Hospedagem no Heroku

A Solução está hospedada em produção na plataforma Heroku na seguinte URL:
[https://ju-marketplace-8b8b843c329f.herokuapp.com](https://ju-marketplace-8b8b843c329f.herokuapp.com)

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o
arquivo <a href="https://github.com/Dev-JeanSantos/tqi_kotlin_backend_developer_2023/blob/main/LICENSE">(LICENSE)</a> para obter.

## Autor

<table>
  <tr>
    <td align="center"><a href="https://github.com/Dev-JeanSantos"><img src="https://avatars.githubusercontent.com/u/69737234?s=400&u=52b04d21b481ad8fa582410d30084534dde0e483&v=4" width="100px;" alt=""/><br/><strong>Jean Santos</strong></a><br/><a href="https://www.linkedin.com/in/dev-jeansantos/">LinkedIn</a></td>
  </tr>
</table>


