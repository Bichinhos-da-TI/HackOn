<br />
<div align="center">
  <h3 align="center">HackOn</h3>

  <p align="center">
    O HackOn é uma plataforma de gestão e criação de desafios e hackathons da Bichinhos da TI.
    <br />
    <br />
  </p>
</div>

<!-- ABOUT THE PROJECT -->
## 📌 Objetivo do projeto
A plataforma HackOn oferece um meio eficaz para gerir e criar desafios e hackathons para a comunidade da Bichinhos da TI.<br />
Dentro da plataforma, o líder de cada squad poderá criar desafios em diversos formatos, como: desafios sazonais, trilhas de desenvolvimento padrão, eventos, etc. Cada integrante/equipe poderá se inscrever nesses desafios e entregar suas soluções através de estruturas similares ao CodePen e/ou repositórios do GitHub.<br />
Conforme os integrantes das equipes entregam os desafios, eles receberão elementos gamificados que serão acumulados. Esses elementos podem ser representados através de níveis, badges, etc. Esses elementos serão utilizados tanto para avaliação quanto para liberar desafios mais complexos dentro da trilha de desenvolvimento.<br />
Os dados gerados pela plataforma permitem que os líderes realizem avaliações técnicas dos integrantes de seus grupos, fornecendo informações valiosas que podem ser utilizadas em diferentes contextos dentro da comunidade Bichinhos da TI.

<!-- GETTING STARTED -->
## 🚀 Instalação e Execução

### 📋 Pré-requisitos

É necessário ter Docker e Docker Compose instalados na máquina.

### 🔧 Instalação

_Para instalar este projeto, siga os passos abaixo:_

1. Clone o repositório
   ```sh
   git clone https://github.com/Bichinhos-da-TI/HackOn-backend.git
   ```

2. Navegue até o diretório do projeto
    ```sh
   cd ./HackOn-backend
    ```
3. Configure as variáveis de ambiente:

   - Renomeie o arquivo `.env.example` para `.env`:

      ```sh
      mv .env.example .env
      ```

   - Abra o arquivo `.env` e insira as credenciais necessárias para a execução do Docker:

      ```env
      DB_HOST=localhost
      DB_USER=seu_usuario
      DB_PASSWORD=sua_senha
      DB_NAME=seu_banco_de_dados
      ```
4. Execute o script de inicialização do Docker Compose
   ```sh
   ./docker-backend.sh
   ```
   
5. Acesse o banco de dados e execute o script SQL:
=======
2. Direcionar para o diretório correto
    ```sh
   cd ./HackOn-backend/server/backend
    ```
3. Executar o script de inicialização do backend
   ```sh
   ./backend-run.sh
   ```
## 🤝 Contribuições
  Para contribuir com código do projeto, é necessário fazer parte da squad 1 de desenvolvimento de produtos da Bichinhos da TI, você pode encontrar mais informações em: https://www.linkedin.com/company/bichinhosdati. <br />
  Sinta-se livre para contribuir com o projeto postando uma issue e/ou entrando em discussões. <br />
Apreciamos imensamento o suporte e qualquer ajuda possivel :).


    ```sh
    docker exec -i <NOME_DO_CONTAINER_DB> psql -U <USUARIO> -d <DATABASE> -f /caminho/para/seu/script.sql
    ```

## 🤝 Contribuições
Para contribuir com o código do projeto, é necessário fazer parte da Squad 1 de desenvolvimento de produtos da Bichinhos da TI. Você pode encontrar mais informações em: https://www.linkedin.com/company/bichinhosdati.<br />
Sinta-se livre para contribuir com o projeto postando uma issue e/ou entrando em discussões.<br />
Apreciamos imensamente o suporte e qualquer ajuda possível :).

## 📝 Licença

Você pode encontrar a licença deste projeto <a href="LICENSE">aqui</a>.
