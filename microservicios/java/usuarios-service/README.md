# 👥 MS-Usuarios - Microservicio de Gestión de Usuarios

**API REST para gestión de usuarios (productores y asistentes técnicos)**

---

## 📋 Contenido

- [Descripción](#descripción)
- [Características](#características)
- [Tecnología](#tecnología)
- [Instalación](#instalación)
- [Endpoints](#endpoints)
- [Modelos de Datos](#modelos-de-datos)
- [Ejemplos de Uso](#ejemplos-de-uso)
- [Troubleshooting](#troubleshooting)

---

## 📊 Descripción

Microservicio Spring Boot que gestiona todo lo relacionado con usuarios del sistema:

- **Productores**: Dueños de predios agrícolas
- **Asistentes Técnicos**: Profesionales que realizan inspecciones
- **Administradores**: Gestores del sistema

---

## ✨ Características

### CRUD Completo
✅ **GET** `/api/usuarios` - Listar todos  
✅ **GET** `/api/usuarios/{id}` - Obtener uno  
✅ **POST** `/api/usuarios` - Crear nuevo ✅ **FUNCIONAL**  
✅ **PUT** `/api/usuarios/{id}` - Actualizar  
✅ **DELETE** `/api/usuarios/{id}` - Eliminar  

### Autenticación
✅ JWT Bearer Tokens  
✅ Spring Security integrado  
✅ Endpoint `/auth/login` para iniciar sesión  
✅ Endpoint `/auth/register` para registrarse  

### Validación
✅ DTOs con @Valid  
✅ Validación de campos requeridos  
✅ Email único en base de datos  
✅ Manejo de excepciones centralizado  

### Documentación
✅ Swagger UI en `/swagger-ui/index.html`  
✅ OpenAPI 3.0 specification  
✅ Ejemplos de request/response  

---

## 🛠️ Tecnología

| Componente | Versión | Descripción |
|-----------|---------|-------------|
| Spring Boot | 3.5.11 | Framework web |
| Spring Data JPA | 3.5.11 | ORM/Data access |
| Hibernate | 6.6.42.Final | Entity mapping |
| Oracle JDBC | 21.1.0.0 | Driver BD |
| Flyway | 9.22.3 | Versionado BD |
| Lombok | 1.18.30 | Boilerplate reduction |
| Springdoc OpenAPI | 2.x | Swagger/OpenAPI |
| Spring Security | 3.5.11 | Autenticación |

---

## 📦 Instalación

### Prerequisitos
```bash
# Java 17 LTS
java -version

# Maven 3.9.15+
mvn -version

# Oracle 10g XE (puerto 1522)
sqlplus system/oracle@localhost:1522/XE
```

### Compilar

```bash
cd microservicios/java/usuarios-service
mvn clean install -DskipTests
```

### Ejecutar

```bash
# Opción 1: JAR
java -jar target/usuarios-service.jar

# Opción 2: Maven
mvn spring-boot:run
```

### Verificar

```bash
curl http://localhost:8081/api/usuarios
```

---

## 🔌 Endpoints

### Autenticación

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **POST** | `/auth/login` | Iniciar sesión |
| **POST** | `/auth/register` | Registrar usuario |

### Usuarios

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| **GET** | `/api/usuarios` | Listar todos | ✅ |
| **GET** | `/api/usuarios/{id}` | Obtener por ID | ✅ |
| **POST** | `/api/usuarios` | Crear nuevo | ✅ |
| **PUT** | `/api/usuarios/{id}` | Actualizar | ✅ |
| **DELETE** | `/api/usuarios/{id}` | Eliminar | ✅ |

### Health Check

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **GET** | `/api/usuarios/health` | Estado del servicio |
| **GET** | `/actuator/health` | Health check Spring |

---

## 📊 Modelos de Datos

### UsuarioDTO (Request/Response)

```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "email": "juan@agro.com",
  "telefono": "+505 555-1234",
  "tipoUsuario": "PRODUCTOR",
  "estado": "ACTIVO",
  "fechaCreacion": "2026-05-02T10:00:00Z"
}
```

### Tipos de Usuario

```
- ADMIN            → Administrador del sistema
- PRODUCTOR        → Dueño de predios
- ASISTENTE_TECNICO → Profesional inspecciones
```

### Estados

```
- ACTIVO   → Usuario operativo
- INACTIVO → Usuario deshabilitado
```

---

## 💻 Ejemplos de Uso

### Listar Usuarios

```bash
# cURL
curl -X GET http://localhost:8081/api/usuarios \
  -H "Authorization: Bearer <TOKEN>"

# PowerShell
Invoke-RestMethod -Uri http://localhost:8081/api/usuarios \
  -Method Get \
  -Headers @{"Authorization"="Bearer <TOKEN>"}

# JavaScript/Fetch
fetch('http://localhost:8081/api/usuarios', {
  method: 'GET',
  headers: {
    'Authorization': 'Bearer <TOKEN>'
  }
}).then(r => r.json())
```

### Obtener Usuario

```bash
curl -X GET http://localhost:8081/api/usuarios/1 \
  -H "Authorization: Bearer <TOKEN>"
```

### Crear Usuario ✅

```bash
curl -X POST http://localhost:8081/api/usuarios \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Carlos López",
    "email": "carlos@agro.com",
    "telefono": "555-5678",
    "tipoUsuario": "ASISTENTE_TECNICO",
    "estado": "ACTIVO"
  }'
```

### Actualizar Usuario

```bash
curl -X PUT http://localhost:8081/api/usuarios/5 \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Carlos López Actualizado",
    "email": "carlos.nuevo@agro.com",
    "telefono": "555-9999",
    "tipoUsuario": "PRODUCTOR",
    "estado": "ACTIVO"
  }'
```

### Eliminar Usuario

```bash
curl -X DELETE http://localhost:8081/api/usuarios/5 \
  -H "Authorization: Bearer <TOKEN>"
```

### Iniciar Sesión

```bash
curl -X POST http://localhost:8081/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@agro.com",
    "password": "12345678"
  }'

# Response
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 2,
    "nombre": "Test Usuario",
    "email": "test@agro.com",
    "tipoUsuario": "PRODUCTOR"
  }
}
```

---

## 📁 Estructura del Proyecto

```
usuarios-service/
├── src/main/java/com/example/usuarios/
│   ├── UsuarioApplication.java         ← Main
│   │
│   ├── controller/
│   │   ├── UsuarioController.java      ← API REST endpoints
│   │   └── AuthController.java         ← Auth endpoints
│   │
│   ├── service/
│   │   ├── UsuarioService.java         ← Lógica de negocio
│   │   └── AuthService.java            ← Autenticación
│   │
│   ├── entity/
│   │   └── UsuarioEntity.java          ← Entidad JPA
│   │
│   ├── repository/
│   │   └── UsuarioRepository.java      ← Data access
│   │
│   ├── dto/
│   │   └── UsuarioDTO.java             ← Data Transfer Object
│   │
│   ├── config/
│   │   ├── SecurityConfig.java         ← Spring Security
│   │   └── WebConfig.java              ← CORS
│   │
│   ├── security/
│   │   └── JwtAuthFilter.java          ← JWT validation
│   │
│   └── filter/
│       └── [Filtros HTTP]
│
├── src/main/resources/
│   ├── application.yml                 ← Config
│   └── db/migration/
│       └── V1__init_usuarios.sql       ← Flyway
│
├── pom.xml                             ← Maven
├── Dockerfile                          ← Docker image
└── README.md                           ← Este archivo
```

---

## ⚙️ Configuración

### application.yml

```yaml
server:
  port: 8081
  servlet:
    context-path: /

spring:
  datasource:
    url: jdbc:oracle:thin:@localhost:1522:XE
    username: system
    password: oracle
  jpa:
    database-platform: org.hibernate.dialect.OracleDialect
    hibernate:
      ddl-auto: validate
  flyway:
    baselineOnMigrate: true
```

### application-prod.yml (Producción)

```yaml
server:
  port: 8081
  ssl:
    enabled: true
    key-store: classpath:keystore.p12
```

---

## 🐛 Troubleshooting

### Error: "Connection refused"

```
Problema: No puede conectar a la BD
Solución:
1. Verificar Oracle está corriendo
2. sqlplus system/oracle@localhost:1522/XE
3. Revisar puerto 1522 (netstat)
```

### Error: "Request processing failed"

```
Problema: Error en validación de datos
Solución:
1. Verificar JSON request
2. Revisar logs del servicio
3. Consultar Swagger para formato correcto
```

### Error: "CORS error"

```
Problema: Frontend no puede conectar
Solución:
1. Verificar WebConfig.java
2. Confirmar que /api/** permitAll
3. Limpiar cache navegador
```

### Error: "JWT token invalid"

```
Problema: Token expirado o inválido
Solución:
1. Obtener nuevo token en /auth/login
2. Verificar Authorization header
3. Incluir "Bearer" antes del token
```

---

## 📊 Estadísticas

| Métrica | Valor |
|---------|-------|
| **Líneas de Código Java** | ~800 |
| **Métodos en Controller** | 5 (GET, POST, PUT, DELETE) |
| **Métodos en Service** | 8+ |
| **Entidades JPA** | 1 (UsuarioEntity) |
| **DTOs** | 1 (UsuarioDTO) |
| **Migraciones BD** | 1 (V1__init_usuarios.sql) |
| **Usuarios Base** | 5 |
| **Endpoints Documentados** | 9 |

---

## 🚀 Despliegue

### Docker

```bash
docker build -t ms-usuarios:1.0 .
docker run -d -p 8081:8081 \
  -e DB_URL=jdbc:oracle:thin:@oracle:1522:XE \
  -e DB_USER=system \
  -e DB_PASS=oracle \
  ms-usuarios:1.0
```

### Kubernetes

```bash
kubectl apply -f k8s/usuarios-service.yaml
```

---

## 📞 Soporte

- **Swagger**: http://localhost:8081/swagger-ui/index.html
- **API Docs**: http://localhost:8081/v3/api-docs
- **Documentación**: Ver [README.md](../../README.md)

---

**Versión**: 1.0.0  
**Última actualización**: Mayo 2, 2026  
**Estado**: ✅ Production Ready
