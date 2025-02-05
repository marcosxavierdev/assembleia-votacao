# assembleia-votacao API  

## Descrição  

Este projeto é uma API REST para gerenciamento de sessões de votação para cooperativas. Desenvolvido com Java e Spring Boot, a API oferece funcionalidades para criar, gerenciar e acompanhar sessões de votação de forma eficiente.  

## Tecnologias Utilizadas  

- **Java 21**  
- **Spring Boot 3.4.1**  
- **Spring Web**  
- **Spring Data MongoDB**  
- **Spring Validation**  
- **Lombok**  
- **MapStruct**  
- **SpringDoc OpenAPI**  

## Pré-requisitos  

- JDK 21 ou superior  
- Apache Maven 3.6 ou superior  

## Banco de dados

A Aplicação utiliza um cluster do MongoDB Atlas privado (conta de usuário não acessível), Disponível em: https://www.mongodb.com/products/platform/atlas-database

## Documentação da API - Openapi

A documentação da API é gerada automaticamente através do SpringDoc OpenAPI e pode ser acessada pelo Swagger UI. Para visualizar a documentação interativa da API, siga os passos abaixo:  

### Configuração do Ambiente  

1. **Clone o repositório (via terminal/bash)**:  


    git clone https://github.com/marcosxavierdev/assembleia-votacao.git


    cd assembleia-votacao


2. **Inicie a aplicação**: Certifique-se de que a aplicação esteja em execução. Se estiver usando uma IDe, pode iniciar uma run, mas se você estiver utilizando o Maven, pode iniciar a aplicação com o seguinte comando (via terminal/bash):  


    mvn spring-boot:run  


3. **Acesse o Swagger UI**: Abra seu navegador e acesse o seguinte URL: http://localhost:8080/assembleia/api/swagger-ui.html


4. **Explore a API**: Na interface do Swagger UI, você pode:

- Visualizar todas as rotas disponíveis na API.
- Testar requisições diretamente pela interface.
- Visualizar a estrutura dos dados esperados e retornados por cada endpoint.

## Endpoints

### Contexto: Eleitor  

#### 1. Buscar Eleitor por ID  
- **Método**: `GET`  
- **URL**: `/assembleia/api/public/v1/eleitor/{id}`  
- **Descrição**: Busca um eleitor pelo ID.  
- **Respostas**:  
  - `200 OK`: Retorna os dados do eleitor.  
  - `404 NOT FOUND`: Eleitor não encontrado.  

#### 2. Buscar Eleitor por CPF  
- **Método**: `GET`  
- **URL**: `/assembleia/api/public/v1/eleitor/byCPF/{cpf}`  
- **Descrição**: Busca um eleitor pelo CPF.  
- **Respostas**:  
  - `200 OK`: Retorna os dados do eleitor.  
  - `404 NOT FOUND`: Eleitor não encontrado.  

#### 3. Criar Eleitor  
- **Método**: `POST`  
- **URL**: `/assembleia/api/public/v1/eleitor`  
- **Descrição**: Cadastra um novo eleitor.  
- **Corpo**: `EleitorRequestDTO`  
- **Respostas**:  
  - `201 CREATED`: Retorna os dados do eleitor criado.  
  - `400 BAD REQUEST`: Requisição inválida.  
  - `404 NOT FOUND`: Recurso não encontrado.  

#### 4. Atualizar Eleitor  
- **Método**: `PUT`  
- **URL**: `/assembleia/api/public/v1/eleitor`  
- **Descrição**: Atualiza os dados de um eleitor pelo ID.  
- **Corpo**: `EleitorUpdateDTO`  
- **Respostas**:  
  - `200 OK`: Retorna os dados atualizados do eleitor.  
  - `400 BAD REQUEST`: Requisição inválida.  
  - `404 NOT FOUND`: Eleitor não encontrado.  

#### 5. Buscar Todos os Eleitores  
- **Método**: `GET`  
- **URL**: `/assembleia/api/public/v1/eleitor`  
- **Descrição**: Retorna uma lista de todos os eleitores cadastrados.  
- **Respostas**:  
  - `200 OK`: Lista de eleitores.  

#### 6. Deletar Eleitor  
- **Método**: `DELETE`  
- **URL**: `/assembleia/api/public/v1/eleitor/{id}`  
- **Descrição**: Remove um eleitor pelo ID.  
- **Respostas**:  
  - `204 NO CONTENT`: Eleitor deletado com sucesso.  
  - `404 NOT FOUND`: Eleitor não encontrado.  

#### 7. Zerar Collection de Eleitores (Apenas para Testes)  
- **Método**: `DELETE`  
- **URL**: `/assembleia/api/private/v1/eleitor`  
- **Descrição**: Deleta todos os registros de eleitores.  
- **Respostas**:  
  - `204 NO CONTENT`: Coleção zerada.  
---  
### Contexto: Pauta  

#### 1. Buscar Pauta por ID  
- **Método**: `GET`  
- **URL**: `/assembleia/api/public/v1/pauta/{id}`  
- **Descrição**: Busca uma pauta pelo ID.  
- **Respostas**:  
  - `200 OK`: Retorna os dados da pauta.  
  - `404 NOT FOUND`: Pauta não encontrada.  

#### 2. Criar Pauta  
- **Método**: `POST`  
- **URL**: `/assembleia/api/public/v1/pauta`  
- **Descrição**: Cria uma nova pauta.  
- **Corpo**: `PautaRequestDTO`  
- **Respostas**:  
  - `201 CREATED`: Retorna os dados da pauta criada.  
  - `400 BAD REQUEST`: Requisição inválida.  
  - `404 NOT FOUND`: Recurso não encontrado.  

#### 3. Atualizar Pauta  
- **Método**: `PUT`  
- **URL**: `/assembleia/api/public/v1/pauta`  
- **Descrição**: Atualiza uma pauta pelo ID.  
- **Corpo**: `PautaUpdateDTO`  
- **Respostas**:  
  - `200 OK`: Retorna os dados atualizados da pauta.  
  - `400 BAD REQUEST`: Requisição inválida.  
  - `404 NOT FOUND`: Pauta não encontrada.  

#### 4. Buscar Todas as Pautas  
- **Método**: `GET`  
- **URL**: `/assembleia/api/public/v1/pauta`  
- **Descrição**: Retorna uma lista de todas as pautas cadastradas.  
- **Respostas**:  
  - `200 OK`: Lista de pautas.  

#### 5. Deletar Pauta  
- **Método**: `DELETE`  
- **URL**: `/assembleia/api/public/v1/pauta/{id}`  
- **Descrição**: Remove uma pauta pelo ID.  
- **Respostas**:  
  - `204 NO CONTENT`: Pauta deletada com sucesso.  
  - `404 NOT FOUND`: Pauta não encontrada.  

#### 6. Zerar Collection de Pautas (Apenas para Testes)  
- **Método**: `DELETE`  
- **URL**: `/assembleia/api/private/v1/pauta`  
- **Descrição**: Deleta todos os registros de pautas.  
- **Respostas**:  
  - `204 NO CONTENT`: Coleção zerada.  

#### 7. Encerrar Pauta  
- **Método**: `POST`  
- **URL**: `/assembleia/api/public/v1/pauta/{id}/encerrar`  
- **Descrição**: Encerra uma pauta pelo ID.  
- **Respostas**:  
  - `200 OK`: Retorna os dados da pauta encerrada.  
  - `404 NOT FOUND`: Pauta não encontrada.  
---  
### Contexto: Voto  

#### 1. Buscar Voto por ID  
- **Método**: `GET`  
- **URL**: `/assembleia/api/public/v1/voto/{id}`  
- **Descrição**: Busca um voto pelo ID.  
- **Respostas**:  
  - `200 OK`: Retorna os dados do voto.  
  - `404 NOT FOUND`: Voto não encontrado.  

#### 2. Criar Voto  
- **Método**: `POST`  
- **URL**: `/assembleia/api/public/v1/voto`  
- **Descrição**: Cadastra um novo voto.  
- **Corpo**: `VotoRequestDTO`  
- **Respostas**:  
  - `201 CREATED`: Retorna os dados do voto criado.  
  - `400 BAD REQUEST`: Requisição inválida.  
  - `404 NOT FOUND`: Recurso não encontrado.  

#### 3. Atualizar Voto  
- **Método**: `PUT`  
- **URL**: `/assembleia/api/public/v1/voto`  
- **Descrição**: Atualiza os dados de um voto pelo ID.  
- **Corpo**: `VotoUpdateDTO`  
- **Respostas**:  
  - `200 OK`: Retorna os dados atualizados do voto.  
  - `400 BAD REQUEST`: Requisição inválida.  
  - `404 NOT FOUND`: Voto não encontrado.  

#### 4. Buscar Todos os Votos  
- **Método**: `GET`  
- **URL**: `/assembleia/api/public/v1/voto`  
- **Descrição**: Retorna uma lista de todos os votos cadastrados.  
- **Respostas**:  
  - `200 OK`: Lista de votos.  

#### 5. Deletar Voto  
- **Método**: `DELETE`  
- **URL**: `/assembleia/api/public/v1/voto/{id}`  
- **Descrição**: Remove um voto pelo ID.  
- **Respostas**:  
  - `204 NO CONTENT`: Voto deletado com sucesso.  
  - `404 NOT FOUND`: Voto não encontrado.  

#### 6. Zerar Collection de Votos (Apenas para Testes)  
- **Método**: `DELETE`  
- **URL**: `/assembleia/api/private/v1/voto`  
- **Descrição**: Deleta todos os registros de votos.  
- **Respostas**:  
  - `204 NO CONTENT`: Coleção zerada.  
---  
### Contexto: Resultado  

#### 1. Buscar Resultado por Pauta ID  
- **Método**: `GET`  
- **URL**: `/assembleia/api/public/v1/resultado/{idPauta}`  
- **Descrição**: Busca o resultado de uma pauta pelo ID.  
- **Respostas**:  
  - `200 OK`: Retorna o resultado da pauta.  
  - `404 NOT FOUND`: Resultado não encontrado.  

#### 2. Listar Todos os Resultados  
- **Método**: `GET`  
- **URL**: `/assembleia/api/public/v1/resultados`  
- **Descrição**: Retorna uma lista com todos os resultados cadastrados.  
- **Respostas**:  
  - `200 OK`: Lista de resultados.  
---  

## Testes Unitarios

## Testes Funcionais

Este tópico fornece instruções sobre como executar os testes funcionais para a API de Gestão de Votação usando o Postman.  

## Conteúdo  

- [Requisitos](#requisitos)  
- [Configuração do Ambiente](#configuração-do-ambiente)  
- [Executando os Testes Funcionais](#executando-os-testes-funcionais)  
- [Estrutura da Coleção do Postman](#estrutura-da-coleção-do-postman)  
- [Estrutura do Ambiente do Postman](#estrutura-do-ambiente-do-postman)  
- [Contribuições](#contribuições)  

## Requisitos  

Antes de executar os testes, certifique-se de ter as seguintes ferramentas instaladas:  

- Postman  

## Configuração do Ambiente  

1. **Clone o repositório (via terminal/bash)**:  


    git clone https://github.com/marcosxavierdev/assembleia-votacao.git


    cd assembleia-votacao


2. **Inicie a aplicação**: Certifique-se de que a aplicação esteja em execução. Se estiver usando uma IDe, pode iniciar uma run, mas se você estiver utilizando o Maven, pode iniciar a aplicação com o seguinte comando (via terminal/bash):  


    mvn spring-boot:run  


3. **Importe a coleção e o ambiente do Postman**:

A coleção de testes está localizada em **resources/postman-collection/Assembleia.postman_collection.**

O ambiente do Postman está localizado em **resources/postman-collection/AssembleiaVariaveis.postman_environment.**


4. **Abra o Postman e importe os arquivos**:

Vá em "Import" no Postman e selecione os arquivos da coleção e do ambiente.
Certifique-se de que a coleção e o ambiente estejam selecionados corretamente.


5. **Executando os Testes Funcionais**

- Verifique se o ambiente correto está selecionado no Postman.
- Selecione a coleção de testes funcionais no painel esquerdo.
- Clique no botão "Run" no canto superior direito.
- Na janela do "Runner", configure as opções desejadas, como número de iterações, delay entre as requisições, etc.
- Clique em "Run" para executar os testes.
- O resultado dos testes será exibido no painel de execução do Postman. Você poderá visualizar o status de cada requisição (passou/falhou) e outras informações relevantes.

### Estrutura da Coleção do Postman
A coleção de testes funcionais contém os seguintes grupos de testes:

Eleitor
- Criar Eleitor
- Buscar Eleitor por ID
- Atualizar Eleitor
- Buscar Todos os Eleitores
- Deletar Eleitor
- zera collection mongodb (somente para testes)

Pauta
- Criar Pauta
- Buscar Pauta por ID
- Atualizar Pauta
- Buscar Todas as Pautas
- Deletar Pauta
- Encerrar Pauta
- zera collection mongodb (somente para testes)

Voto
- Criar Voto
- Buscar Voto por ID
- Atualizar Voto
- Buscar Todos os Votos
- Deletar Voto
- zera collection mongodb (somente para testes)

Resultado
- Buscar Resultado por Pauta
- Listar Todos os Resultados

Cada grupo contém testes individuais que verificam o comportamento da API em diferentes cenários.

### Estrutura do Ambiente do Postman

O ambiente do Postman contém as seguintes variáveis:

- base_url: a URL base da API
- eleitor_id: ID de um eleitor existente
- pauta_id: ID de uma pauta existente
- voto_id: ID de um voto existente

Certifique-se de que essas variáveis estejam configuradas corretamente de acordo com o seu ambiente de teste.

### Observações:  

- Este README assume que a coleção e o ambiente do Postman estão localizados em `resources/postman-collection`.  
- Você pode ajustar a estrutura da coleção e do ambiente conforme necessário para atender às suas necessidades específicas.  
- Inclua informações adicionais, como como configurar o ambiente de teste, se necessário.  
- Considere adicionar seções sobre como gerar relatórios ou integrar os testes a um pipeline de CI/CD, se aplicável. 

## Arquitetura

## Autenticacao



