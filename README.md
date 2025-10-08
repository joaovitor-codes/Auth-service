🛡️ Auth-Service: Microsserviço de Autenticação e Usuários
🎯 Sobre o Projeto
O Auth-Service é um microsserviço dedicado ao sistema de contas e segurança da arquitetura

Ele segue o padrão de Arquitetura de Microsserviços, sendo o único responsável por: Autenticação, Autorização (via Papéis/Roles), Criptografia de Senhas e Gerenciamento de Contas. No futuro, será a fonte de geração e validação de Tokens JWT para todos os outros serviços.

Funcionalidades Chave
Gerenciamento de Usuários (Contas): Cadastro, consulta e atualização de contas.

Controle de Acesso: Implementação de perfis de usuário via ENUM e Spring Security.

Segurança: Utiliza Spring Security e JPA para autenticação baseada em banco de dados e criptografia de senhas (BCrypt).

Persistência Profissional: Gerenciamento do esquema do banco de dados via Flyway Migrations.

🛠️ Stack de Tecnologia
Categoria	Tecnologia	Uso
Backend Framework	Spring Boot 3.x	Configuração rápida e injeção de dependência.
Segurança	Spring Security	Autenticação (via UserDetails) e Autorização.
Persistência (ORM)	Spring Data JPA / Hibernate	Mapeamento de entidades (ContaEntity) e acesso ao banco.
Banco de Dados	PostgreSQL 16.x	Banco de dados relacional robusto.
Migração de BD	Flyway	Controle de versão e schema do banco de dados (garantindo que o SQL de criação da tabela contas seja executado de forma segura).
Desenvolvimento	Maven	Gerenciamento de dependências e construção do projeto.

⚙️ Configuração e Instalação
Pré-requisitos
Java JDK 17+
Maven 3+

Passos de Execução
Clone o Repositório:

Bash

git clone [Link do seu repositório]
cd auth-service
Configurar o Banco de Dados:
Ajuste o arquivo src/main/resources/application.properties com as suas credenciais:

Properties

spring.datasource.url=jdbc:postgresql://localhost:5433/db_auth-service
spring.datasource.username=[SEU_USUARIO]
spring.datasource.password=[SUA_SENHA]

Executar Migrações (Flyway):
O Flyway irá automaticamente criar a tabela contas ao iniciar o aplicativo, usando o arquivo V1__Create_contas_table.sql.

Compilar e Rodar o Projeto:

Bash

# Compila o projeto e instala dependências
mvn clean install

# Executa o aplicativo
mvn spring-boot:run
O serviço estará acessível em http://localhost:8080.

🔐 Detalhes da Implementação de Segurança
A segurança é implementada através da integração direta entre o Spring Security e o JPA:

1. Entidade de Usuário (ContaEntity)
A classe ContaEntity implementa a interface UserDetails, permitindo que o Spring Security a utilize nativamente para autenticação.

As regras de autoridade são definidas no método getAuthorities() com base no ContasRole.

2. Criptografia de Senhas
A injeção de BCryptPasswordEncoder é configurada em SecurityConfig.

Toda senha é criptografada antes de ser salva na coluna senha do banco.

3. Persistência de Papéis
A lógica de ROLE utiliza um Enum no código (ContasRole) e é mapeada no banco como um VARCHAR(50).

🌐 Endpoints de Exemplo
Método	Endpoint	Descrição	Acesso Requerido
POST	/contas/cadastro	Registra uma nova conta no sistema.	Público (permitAll)
POST	/auth/login	Endpoint futuro para emitir JWT.	Público
GET	/contas/admin	Endpoint de teste para administradores.	ROLE_ADMIN
GET	/contas/{id}	Busca de conta por ID.	Autenticado
