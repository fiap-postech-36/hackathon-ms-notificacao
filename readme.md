# Notification Microservice

Este é um microserviço responsável pelo envio de notificações por e-mail.

## Tecnologias Utilizadas

- Java 17 (Amazon Corretto)
- Maven
- Spring Boot
- Docker / Docker Compose
- Resend API
---

## Configuração e Execução do Container Docker

### 1. Configurar o Token do Resend

Este microserviço utiliza a API Resend para envio de e-mails.

Certifique-se de ter um token válido da API Resend. O token deve ser configurado como uma variável de ambiente chamada `RESEND_TOKEN`.

### 2. Arquivo `docker-compose.yml`

Certifique-se de que o arquivo `docker-compose.yml` esteja configurado corretamente:

```yaml
services:
  app:
    build: .
    ports:
      - "8081:8080"
    environment:
       - RESEND_TOKEN=re_token12345678
       - RABBIT_USER=username
       - RABBIT_PASSWORD=password
- 
```
Substitua `seu_token_resend` pelo seu token da API Resend e defina as credenciais do rabbitMQ.

### 3. Construir e Iniciar o Container

No diretório onde estão os arquivos `Dockerfile` e `docker-compose.yml`, execute os comandos abaixo:

1. Construir a imagem Docker:
   ```bash
   docker-compose build
   ```

2. Iniciar o container:
   ```bash
   docker-compose up -d
   ```

O microserviço estará acessível em:
```
http://localhost:8081
```

---

## Endpoint Disponível

### Enviar Notificação por E-mail

- **Rota:** `POST /email`
- **URL Local:** `http://localhost:8081/email`
- **Headers:**
    - `Content-Type: application/json`
- **Body:**

```json
{
  "recipient": "seuemail@fiap.com.br",
  "subject": "ERROR",
  "body": "Olá, identificamos que houve erro ao processar seu vídeo 'SHREK 2', faça login em sua conta para ver mais detalhes"
}
```

**Observação:** O campo `subject` é um enum que pode assumir os valores:
- `ERROR`: Indica que ocorreu um problema no processamento do vídeo.
- `SUCCESS`: Indica que o vídeo foi processado com sucesso.

---

## Logs e Debug

Para acompanhar os logs do microserviço enquanto ele está rodando, utilize o comando:

```bash
docker-compose logs -f
```

---

## Possíveis Problemas

1. **Token do Resend não configurado:**
   Certifique-se de que o token foi configurado corretamente na variável `RESEND_TOKEN`. Sem ele, o envio de e-mails falhará.

2. **Porta 8081 ocupada:**
   Se a porta 8081 estiver em uso, altere a configuração de `ports` no `docker-compose.yml` para uma porta disponível, por exemplo:
   ```yaml
   ports:
     - "8082:8080"
   ```

