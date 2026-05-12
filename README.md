# TechStore API

API REST CRUD de productos desarrollada con Spring Boot, PostgreSQL, JWT y Docker.

## Tecnologías utilizadas
-Java 17
-Spring Boot
-Spring Security
-JWT
-PostgreSQL
-Docker
-Maven
-Postman

## Ejecutar proyecto
```bash
docker compose up --buil


## Login
```md
## Login JWT
POST http://localhost:8080/auth/login

{
  "username": "admin",
  "password": "1234"
}

## Endpoints
GET /api/productos
GET /api/productos/todos
GET /api/productos/{id}
GET /api/productos/todos/{id}

POST /api/productos
PUT /api/productos/{id}
DELETE /api/productos/{id}

## Funcionalidades
-CRUD de productos
-Borrado lógico
-Validaciones
-Manejo de errores
-Autenticación JWT
-Docker Compose