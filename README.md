
# MicroUrl

O MU é uma solução simples e eficiente para quem precisa compartilhar URLs de forma mais conveniente e rastreável. Ele é ideal para uso pessoal ou em pequenas/médias empresas que desejam ter mais controle sobre as URLs compartilhadas.

MU é uma aplicação em Java que fornece um serviço de encurtamento de URLs. Ele permite que os usuários convertam URLs longas em versões mais curtas e fáceis de compartilhar.

---

MU is a simple and efficient solution for those who needs share URLs more conveniently and treceable. It's ideal for personal use or in small/medium enterprises of wish to have more control about urls shared.

MU is a java application that provides a shoten url service. Allows users to convert long urls into shorter version and easy to share.
## Stack Utilizada / Stack used
**Back-end:** Java 21, Spring boot, Docker

**Maven Dependencies:** Flyway, Mysql Driver, Lombok and Spring JPA
## Funcionalidades / Functionalities

- Encurtar url
- Redirecionar url
- Excluir url no banco de dados
- Mostrar todas as urls no banco de dados
---
- Shorten url
- Redirect url
- Delete url in database
- Show all urls in database


## Aprendizados

O principal foco de estudo desse projeto girou em torno de como encurtar a url passada na requisição e gerar uma url pequena.

Para poder solucionar o problema de gerar um hash seguro e com a porcentagem minima de colisões, foi necessario implementar um algoritimo básico de SHA-256 e pegar os dez primeiros caracteres.

Também foi necessario entender HttpServletRequest para fazer o redirect da url armazenada no banco de dados.

---

The main focus of study of this project revolted around of how shoten url passed in request and generating a small url.

To be able to solve the problem of generating a segure hash and with a minimum percentage of collisions, it was necessary implement a básic algorithm of SHA-256 and take ten first characters.

Was necessary as well learning HttpServletRequest to redirect url stored in database.
## Variáveis de Ambiente / Environment Variables

Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu .env

---
To start this project, you needs add the following environment variables in .env file.

`DB_CONNECTION_URL`

`DB_PASSWORD`

`DB_USER`


## Rodando localmente / Running Locally

Clone o projeto

```bash
  git clone https://github.com/thiagovinialmeida/MicroUrl
```

Entre no diretório do projeto

```bash
  cd MicroUrl
```

Instale as dependências

```CMD
  docker-compose --project-name MicroUrl up -d
```


## Licença

![License](https://img.shields.io/badge/license-MIT-orange.svg?style=for-the-badge)

