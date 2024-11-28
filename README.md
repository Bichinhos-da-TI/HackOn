<br />
<div align="center">
  <h3 align="center">HackOn</h3>

  <p align="center">
    O HackOn é uma plataforma de gestão e criação de desafios e hackathons da Bichinhos da TI.
    <br />
    <br />
  </p>
</div>

<!-- SOBRE O PROJETO -->
## 📌 Objetivo do projeto

A plataforma HackOn oferece um meio eficaz para gerir e criar desafios e hackathons para a comunidade da Bichinhos da TI.
Dentro da plataforma, o líder de cada squad poderá criar desafios em diversos formatos, como desafios sazonais, trilhas de desenvolvimento padrão, eventos, etc. Cada integrante ou equipe poderá se inscrever nesses desafios e devolver suas soluções através de estruturas similares ao CodePen e/ou repositórios do GitHub.
Conforme os integrantes das equipes entregam os desafios, eles receberão elementos gamificados que serão acumulados. Estes elementos podem ser representados através de níveis, badges, etc., e serão utilizados tanto para avaliação quanto para liberar desafios mais complexos dentro da trilha de desenvolvimento.
Os dados gerados pela plataforma permitem que os líderes realizem avaliações técnicas dos integrantes de seus grupos, fornecendo informações valiosas que podem ser utilizadas em diferentes contextos dentro da comunidade Bichinhos da TI.

<!-- COMEÇANDO -->
## 🚀 Instalação e Execução

### 📋 Pré-requisitos

É necessário ter **Docker** e **Docker Compose** instalados na máquina.

#### Instalando o Docker

1. **Para Windows e Mac:**

   - Baixe e instale o Docker Desktop a partir do [site oficial do Docker](https://www.docker.com/products/docker-desktop).
   - Siga as instruções de instalação fornecidas pelo instalador.

2. **Para Linux:**

   - **Ubuntu/Debian:**

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

   - **Fedora:**

     ```sh
     sudo dnf -y remove podman buildah
     sudo dnf -y install dnf-plugins-core
     sudo dnf config-manager --add-repo https://download.docker.com/linux/fedora/docker-ce.repo
     sudo dnf install docker-ce docker-ce-cli containerd.io
     sudo systemctl start docker
     sudo systemctl enable docker
     ```

   - **Arch Linux:**

     ```sh
     sudo pacman -Syu
     sudo pacman -S docker
     sudo systemctl start docker
     sudo systemctl enable docker
     ```

3. **Verifique a instalação:**

   - Execute o comando abaixo para verificar se o Docker foi instalado corretamente:

     ```sh
     docker --version
     ```

#### Instalando o Docker Compose

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

### 🔧 Instalação

_Siga os passos abaixo para instalar o projeto:_

1. **Clonar o repositório**

   ```sh
   git clone https://github.com/Bichinhos-da-TI/HackOn-backend.git
   ```
   
2. **Navegar até o diretório do projeto**

   ```sh
   cd ./HackOn-backend
   ```

3. **Configurar as variáveis de ambiente**

   - Renomeie o arquivo `.env.example` para `.env`:

     ```sh
     mv .env .env
     ```

   - Abra o arquivo `.env` e insira as credenciais necessárias para a execução do Docker:

     ```env
     DB_HOST=localhost
     DB_USER=seu_usuario
     DB_PASSWORD=sua_senha
     DB_NAME=seu_banco_de_dados
     ```

4. **Iniciar os serviços com Docker Compose**

   ```sh
   docker-compose up -d
   ```

5. **Acessar o banco de dados e executar o script SQL**

   ```sh
   docker exec -i <NOME_DO_CONTAINER_DB> psql -U <USUARIO> -d <DATABASE> -f /caminho/para/seu/script.sql
   ```

### 📁 Estrutura do Docker Compose

O arquivo `docker-compose.yml` define os serviços necessários para o projeto, incluindo:

- **Serviço de Banco de Dados:** Configura um contêiner para o banco de dados.
- **Serviço de Aplicação:** Configura um contêiner para a aplicação principal.

### 🗄️ Estrutura do Script SQL

O script SQL (`HACK-20_FE-Criar Banco de dados Postgres.sql`) contém comandos para:

- Criação de tabelas
- Definição de índices e chaves estrangeiras

### ✅ Considerações Finais

Certifique-se de que todos os serviços estão em execução e o banco de dados está corretamente configurado antes de iniciar a aplicação.

## 🤝 Contribuições

Para contribuir com o código do projeto, é necessário fazer parte da **Squad 1 de Desenvolvimento de Produtos** da Bichinhos da TI. Você pode encontrar mais informações em: [LinkedIn Bichinhos da TI](https://www.linkedin.com/company/bichinhosdati).

Sinta-se livre para contribuir com o projeto postando uma *issue* e/ou participando das discussões.

Agradecemos imensamente o suporte e qualquer ajuda possível 😊.

## 📝 Licença

Você pode encontrar a licença deste projeto <a href="LICENSE">aqui</a>.
