## Pré-requisitos

- Docker
- Docker Compose

### Como executar

## Instalar Docker

1. **Para Windows e Mac:**

    - Baixe e instale o Docker Desktop a partir do [site oficial do Docker](https://www.docker.com/products/docker-desktop).
    - Siga as instruções de instalação fornecidas pelo instalador.

2. **Para Linux:**

    - Execute os seguintes comandos no terminal:

    ```sh
    sudo apt-get update
    sudo apt-get install \
        ca-certificates \
        curl \
        gnupg \
        lsb-release
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
    echo \
      "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
      $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
    sudo apt-get update
    sudo apt-get install docker-ce docker-ce-cli containerd.io
    ```

3. **Verifique a instalação:**

    - Execute o comando abaixo para verificar se o Docker foi instalado corretamente:

    ```sh
    docker --version
    ```

### Instalar Docker Compose

1. **Para todas as plataformas:**

    - Execute o comando abaixo para baixar a versão mais recente do Docker Compose:

    ```sh
    sudo curl -L "https://github.com/docker/compose/releases/download/$(curl -s https://api.github.com/repos/docker/compose/releases/latest | grep -Po '"tag_name": "\K.*?(?=")')/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
    ```

    - Aplique permissões executáveis ao binário:

    ```sh
    sudo chmod +x /usr/local/bin/docker-compose
    ```

2. **Verifique a instalação:**

    - Execute o comando abaixo para verificar se o Docker Compose foi instalado corretamente:

    ```sh
    docker-compose --version
    ```

## Executar

1. **Configure as variáveis de ambiente:**

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

2. **Inicie os serviços com Docker Compose:**

    ```sh
    docker-compose up -d
    ```

3. **Acesse o banco de dados e execute o script SQL:**

    ```sh
    docker exec -i <NOME_DO_CONTAINER_DB> psql -U <USUARIO> -d <DATABASE> -f /caminho/para/seu/script.sql
    ```

## Estrutura do Docker Compose

O arquivo `docker-compose.yml` define os serviços necessários para o projeto, incluindo:

- **Serviço de Banco de Dados:** Configura um contêiner para o banco de dados.
- **Serviço de Aplicação:** Configura um contêiner para a aplicação principal.

## Estrutura do Script SQL

O script SQL (`HACK-20_FE-Criar Banco de dados Postgres.sql`) contém comandos para:

- Criação de tabelas
- Definição de índices e chaves estrangeiras

## Considerações Finais

Certifique-se de que todos os serviços estão em execução e o banco de dados está corretamente configurado antes de iniciar a aplicação.
