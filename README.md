# Tekton Challenge

---

## Descripción

* **/api/execute/calculate** – Recibe dos números, obtiene (o cachea) un porcentaje externo y devuelve `(n1 + n2) + %`.
* **/api/execute/history** –Devuelve el historial paginado de invocaciones.
* Todas las respuestas siguen un estandar.

## Stack y dependencias

| Capa | Tecnologías                                     |
|------|-------------------------------------------------|
| Core | Java 21, Spring Boot 3.x (Web, Data JPA, Cache) |
| DB   | PostgreSQL 17 (Docker)                          |
| Build| Maven 3.9                                       |
| Docs | springdoc‑openapi 2.8 (Swagger UI)              |


## Ejecución rápida con Docker Compose

```bash
# Clona el repo y entra en la carpeta raíz
cd tekton-challenge

# Levanta DB + microservicio (compila la imagen la primera vez)
docker compose up -d
```

| Servicio            | URL externa |
|---------------------|-------------|
| API REST            | <http://localhost:8080> |
| Swagger UI          | <http://localhost:8080/tekton-ui.html> |
| OpenAPI JSON        | <http://localhost:8080/api-docs> |
| PostgreSQL          | `localhost:5432` |

---

## Endpoints principales

### 1. Cálculo

`POST /api/execute/calculate`

```jsonc
//Request body
{
  "number1": 100,
  "number2": 50
}
```

*Header opcional:* `fail-simulate: true` fuerza error del servicio externo.

**Respuesta 200**
```json
{
  "code": 0,
  "message": "success",
  "success": true,
  "data": {
    "base": 150.0,
    "percentage": 10.0,
    "result": 165.0
  }
}
```

**Respuesta 500 (sin porcentaje en caché)**
```json
{
  "code": 1,
  "message": "No cached percentage available",
  "success": false,
  "data": null
}
```

### 2. Historial paginado

`GET /api/execute/history?page=0&size=20`

**Respuesta 200**
```json
{
  "code": 0,
  "message": "success",
  "success": true,
  "data": {
    "records": [ /* CallHistory */ ],
    "page": 0,
    "size": 20,
    "totalPages": 1,
    "totalElements": 3
  }
}
```

**Respuesta 404** (sin registros)
```json
{
  "code": 1,
  "message": "no records found",
  "success": false,
  "data": null
}
```

---

## Pruebas

```bash
mvn test
```

---


