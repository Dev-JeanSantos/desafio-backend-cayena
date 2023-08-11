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

## Requerimentos
- Spring
- Java 11
- Maven
- Mysql
- Git

## Configurações

## Script da Criação  e Insert do Banco de Dados:
```sql
CREATE TABLE supplier (
  suplier_id BIGINT NOT NULL,
   name VARCHAR(255) NULL,
   date_of_creation datetime NULL,
   date_of_the_last_update datetime NULL,
   CONSTRAINT pk_supplier PRIMARY KEY (suplier_id)
);

INSERT INTO supplier (suplier_id,name, date_of_creation, date_of_the_last_update)VALUES (1,'SACOLÃO DO PEDRÃO',now(), now());
INSERT INTO supplier (suplier_id,name, date_of_creation, date_of_the_last_update)VALUES (2,'CASA DAS CARNES CARIOCA',now(), now());
INSERT INTO supplier (suplier_id,name, date_of_creation, date_of_the_last_update)VALUES (3,'MERCADO GUANABARA ',now(), now());

CREATE TABLE product (
  product_id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255) NULL,
   quantity_in_stock INT NULL,
   unit_price DECIMAL NULL,
   supplier_id BIGINT NULL,
   date_of_creation date NULL,
   date_of_the_last_update date NULL,
   CONSTRAINT pk_product PRIMARY KEY (product_id)
);

ALTER TABLE product ADD CONSTRAINT FK_PRODUCT_ON_SUPPLIER FOREIGN KEY (supplier_id) REFERENCES supplier (suplier_id);


```

Ambiente de Desenvolvimento:

Para executar em desenvolvimento segue as instruções onde as etapas necessárias será clonar o repositório e iniciar a aplicação em
diferentes ambientes (Unix e Windows) com o perfil de dev ativado executando em um banco de dados Mysql dockerizado.

1. Clone o repositório: git clone `https://github.com/Dev-JeanSantos/desafio-backend-cayena`
2. Executar o comando docker: `docker run -d -p 3306:3306 --name mysql-container  -e MYSQL_ROOT_PASSWORD=root  -e  MYSQL_PASSWORD=root mysql:8.0.28`
3. Inicie a aplicação no ambiente Windows: `mvn spring-boot:run  -Dspring-boot.run.profiles=dev`
4. Inicie a aplicação no ambiente Unix: `./mvn spring-boot:run  -Dspring-boot.run.profiles=dev`


Seguem alguns links úteis:

1. [Documentação da API](http://localhost:8080/swagger-ui.html)
2. [Collection Postman](https://www.postman.com/jeancbsan/workspace/publics-collections/collection/12860836-3c57f309-cc50-48ed-9aab-955b9f9a0718?action=share&creator=12860836) (útil pra entender todas as rotas da API)



## Uso da API


______
### Criação de um produto:
> Request
- Método: `POST`
- Endpoint: `/api/v1/products`

Parâmetros do body da chamada:
- `{name}`: Nome do produto;
- `{quantity_inStock}`: Quantidade disponivel no estoque;
- `{unit_price}`: Valor unitario do produto;
- `{supplier_id}`: Identificação do Fornecedor

Exemplo de solicitação:

```http
POST /api/v1/products
```

Exemplo de REQUEST:
```json
{
  "name": "Café Melitta",
  "quantity_inStock": 500,
  "unit_price": 12.0,
  "supplier_id": 3
}
```

> Response

- Código de resposta: `200 OK`
- Corpo da resposta: `ProductResponse` contendo os itens do novo produto criado.

Exemplo de RESPONSE:

```json
{
  "id": 2,
  "name": "Café Melitta",
  "quantityInStock": 500,
  "unitPrice": 12.0,
  "supplierName": "MERCADO GUANABARA "
}
```

#### Códigos de resposta possíveis:

- `200 OK`: Produfo criado com sucesso.
- `404 Not Found`: Caso forneça um ID do fornecedor não cadastrado.
-`400 Bad Request`: Caso forneça a um ou mais campos entradas inválidas.
______
### Buscar todos os produtos paginados:
> Request

Exemplo de solicitação:
- Método: `GET`
- Endpoint: `/api/v1/products`
```http
GET /api/v1/products
```
Não passamos nada no corpo da requisição da aplicação.


> Response

- Código de resposta: `200 OK`
- Corpo da resposta: `ProductResponse` contendo os itens do novo produto criado.

Exemplo de RESPONSE:

```json
{
  "content": [
    {
      "id": 1,
      "name": "Café Melitta",
      "quantityInStock": 500,
      "unitPrice": 12,
      "supplierName": "MERCADO GUANABARA "
    },
    {
      "id": 2,
      "name": "Café Melitta",
      "quantityInStock": 500,
      "unitPrice": 12,
      "supplierName": "MERCADO GUANABARA "
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 10,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalElements": 2,
  "totalPages": 1,
  "size": 10,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": true,
  "numberOfElements": 2,
  "number": 0,
  "empty": false
}
```

#### Códigos de resposta possíveis:

- `200 OK`: Produfo criado com sucesso.

_________
### Buscar produto por ID do produto:
> Request

Exemplo de solicitação:
- Método: `GET`
- Endpoint: `/api/v1/products/{id}`
```http
GET /api/v1/products{id}
```
Não passamos nada no corpo da requisição da aplicação.


> Response

- Código de resposta: `200 OK`
- Corpo da resposta: `ProductAllResponse` contendo os itens do novo produto criado.

Exemplo de RESPONSE:

```json
{
  "name": "Café Melitta",
  "quantity_in_stock": 500,
  "unit_price": 12,
  "date_of_creation": "2023-08-11T00:00:00",
  "date_of_the_last_update": null,
  "supplier": {
    "name": "MERCADO GUANABARA ",
    "suplier_id": 3,
    "date_of_creation": "2023-08-11T12:11:16",
    "date_of_the_last_update": "2023-08-11T12:11:16"
  }
}
```

#### Códigos de resposta possíveis:

- `200 OK`: Produto criado com sucesso.
- `404 Not Found`: Caso forneça um ID do produto não cadastrado.
__________
### Atualizar um produto:
> Request

Exemplo de solicitação:
- Método: `PUT`
- Endpoint: `/api/v1/products/{id}`
```http
GET /api/v1/products{id}
```
Exemplo de REQUEST:
```json
{
  "name": "Café Melitta",
  "quantity_inStock": 500,
  "unit_price": 12.0,
  "supplier_id": 3
}
```

> Response

- Código de resposta: `200 OK`
- Corpo da resposta: `ProductResponse` contendo os itens do novo produto criado.

Exemplo de RESPONSE:

```json
{
  "name": "Café Melitta",
  "quantity_in_stock": 500,
  "unit_price": 12,
  "date_of_creation": "2023-08-11T00:00:00",
  "date_of_the_last_update": null,
  "supplier": {
    "name": "MERCADO GUANABARA ",
    "suplier_id": 3,
    "date_of_creation": "2023-08-11T12:11:16",
    "date_of_the_last_update": "2023-08-11T12:11:16"
  }
}
```

#### Códigos de resposta possíveis:

- `200 OK`: Produto atualizado com sucesso.
- `404 Not Found`: Caso forneça um ID do fornecedor ou produto não cadastrado.
- `400 Bad Request`: Caso forneça a um ou mais campos entradas inválidas.

______
### Deletar um produto:
> Request

Exemplo de solicitação:
- Método: `DELETE`
- Endpoint: `/api/v1/products/{id}`
```http
DELETE /api/v1/products{id}
```
Não passamos nada no corpo da requisição da aplicação.

> Response

- Código de resposta: `204 NO CONTENT`
- Corpo da resposta: Não há nada no corpo da resposta.

Exemplo de RESPONSE:


#### Códigos de resposta possíveis:

- `200 OK`: Produto atualizado com sucesso.
- `404 Not Found`: Caso forneça um ID do fornecedor ou produto não cadastrado.
- `400 Bad Request`: Caso forneça a um ou mais campos entradas inválidas.

__________
### Atualizar um produto:
> Request

Exemplo de solicitação:
- Método: `PATCH`
- Endpoint: `/api/v1/products/{id}`
```http
PATCH /api/v1/products{id}
```
Exemplo de REQUEST:
```json
{
  "update_type": "DECREASE",
  "quantity_inStock": 135
}
```

> Response

- Código de resposta: `200 OK`
- Corpo da resposta: `ProductResponse` contendo os itens do produto atualizado.

Exemplo de RESPONSE:

```json
{
  "id": 1,
  "name": "Café Melitta",
  "quantityInStock": 365,
  "unitPrice": 12,
  "supplierName": "MERCADO GUANABARA "
}
```

#### Códigos de resposta possíveis:

- `200 OK`: Produto atualizado com sucesso.
- `404 Not Found`: Caso forneça um ID do fornecedor ou produto não cadastrado.
- `400 Bad Request`: Caso forneça a um ou mais campos entradas inválidas.

## Documentação do Swagger

A documentação da API pode ser encontrada no Swagger. Para visualizá-la,
acesse: [Documentação do Swagger](https://ju-marketplace-8b8b843c329f.herokuapp.com/swagger-ui/index.html).

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o
arquivo <a href="https://github.com/Dev-JeanSantos/desafio-backend-cayena/blob/main/LICENSE">(LICENSE)</a> para obter.

## Autor

<table>
  <tr>
    <td align="center"><a href="https://github.com/Dev-JeanSantos"><img src="https://avatars.githubusercontent.com/u/69737234?s=400&u=52b04d21b481ad8fa582410d30084534dde0e483&v=4" width="100px;" alt=""/><br/><strong>Jean Santos</strong></a><br/><a href="https://www.linkedin.com/in/dev-jeansantos/">LinkedIn</a></td>
  </tr>
</table>


