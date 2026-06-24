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

## Endpoints de productos

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/productos` | Lista productos activos |
| GET | `/api/productos/todos` | Lista todos los productos |
| GET | `/api/productos/{id}` | Busca producto activo por ID |
| GET | `/api/productos/todos/{id}` | Busca productos incluyendo eliminados |
| POST | `/api/productos` | Crea productos |
| PUT | `/api/productos/{id}` | Modifica productos |
| DELETE | `/api/productos/{id}` | Eliminación lógica |

## Validaciones y manejo de errores

El proyecto implementa validaciones para asegurar integridad de datos:

- Nombre obligatorio.
- Categoría obligatoria.
- Precio mayor a cero.
- Stock no negativo.

También se implementó manejo global de excepciones para:

- Productos inexistentes.
- Errores de validación.
- Respuestas HTTP personalizadas.

## Control de versiones

El proyecto fue desarrollado utilizando Git y GitHub.

Se trabajó mediante flujo de ramas:

- `dev`: desarrollo.
- `main`: versión estable.

Al finalizar el desarrollo se realizó merge desde DEV hacia MAIN.
