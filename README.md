# TechStore API

API REST para la gestión de productos de TechStore, desarrollada con Spring Boot, PostgreSQL, JWT y Docker.

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- Docker
- Docker Compose
- Maven
- Postman
- Git y GitHub

## Arquitectura del proyecto

El proyecto está organizado mediante arquitectura en capas:

- `controller`: manejo de endpoints REST.
- `service`: lógica de negocio.
- `repository`: acceso a datos mediante JPA.
- `model`: entidades del sistema.
- `security`: autenticación JWT y configuración de Spring Security.
- `dto`: objetos para login y respuestas.
- `exception`: manejo global de errores.

## Ejecución con Docker Compose

Para levantar la aplicación junto con PostgreSQL:

```bash
docker compose up --build
```

La API quedará disponible en:

```text
http://localhost:8080
```

## Login JWT

Endpoint:

```http
POST /auth/login
```

Body:

```json
{
  "username": "admin",
  "password": "1234"
}
```

Respuesta esperada:

```json
{
  "token": "TOKEN_GENERADO",
  "tipo": "Bearer",
  "expiracion": "1 hora"
}
```