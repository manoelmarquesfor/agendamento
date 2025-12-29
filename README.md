
# Agendamento — Serviço simples de agendamentos ✅

Serviço RESTful para gerenciar agendamentos (criar, listar, concluir e cancelar) — desenvolvido em **Java 21** com **Spring Boot 4** e persistência em **PostgreSQL**. Ideal para demonstração e templates de microserviço.

---

## 📦 Stack

- **Linguagem:** Java 21
- **Framework:** Spring Boot 4 (Web MVC, Validation, Flyway)
- **DB:** PostgreSQL (via JDBC + JDBI)
- **Migrações:** Flyway
- **Build:** Maven
- **Container:** Docker / Docker Compose

---

## ⚙️ Variáveis de ambiente


| Variável | Exemplo | Obrigatório | Descrição |
|---|---:|:---:|---|
| `DATASOURCE_HOST` | `localhost` | ✅ | Host do Postgres |
| `DATASOURCE_PORT` | `5432` | ✅ | Porta do Postgres |
| `DATASOURCE_DB` | `agendamento` | ✅ | Nome do banco |
| `DATASOURCE_USERNAME` | `postgres` | ✅ | Usuário do DB |
| `DATASOURCE_PASSWORD` | `postgres` | ✅ | Senha do DB |


---

## 🚀 Executando localmente

1. Build com Maven:

```bash
mvn package
```

2. Rodar com Docker Compose 

## Com `.env`: 

```bash
docker compose --env-file .env up --build
```

## Sem `.env` (ajuste variáveis no `docker-compose.yml`):

```bash
docker compose up --build
```

3. Acessar a API em: `http://localhost:8080`.



---

## 📚 Documentação da API

Base URL: `/agendamentos`

### Endpoints

- **GET /agendamentos/**
  - Retorna lista de agendamentos
  - Response: `200 OK` — array de AgendamentoResponse

- **GET /agendamentos/{id}**
  - Retorna um agendamento por id
  - Path: `id` = UUID
  - Response: `200 OK` — AgendamentoResponse

- **POST /agendamentos/**
  - Cria um novo agendamento
  - Body (exemplo):

```json
{
  "descricao": "Consulta médica",
  "dataAgendamento": "2025-12-31 14:30:00"
}
```
  - Regras de validação:
    - `descricao`: obrigatória, 5–255 caracteres
    - `dataAgendamento`: obrigatória, formato `yyyy-MM-dd HH:mm:ss`
  - Response: `200 OK` — AgendamentoResponse

- **PUT /agendamentos/{id}**
  - Conclui o agendamento (altera `status`)
  - Response: `200 OK` — AgendamentoResponse

- **DELETE /agendamentos/{id}**
  - Cancela o agendamento (altera `status`)
  - Response: `200 OK` — AgendamentoResponse

### Estrutura de resposta (AgendamentoResponse)

```json
{
  "id": "uuid",
  "descricao": "...",
  "dataAgendamento": "yyyy-MM-dd HH:mm:ss",
  "dataCriacao": "yyyy-MM-dd HH:mm:ss",
  "status": "AGENDADO|CONCLUIDO|CANCELADO"
}
```
