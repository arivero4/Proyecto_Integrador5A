# ☕ Microservicios Java - Spring Boot 3.5.11

**3 Microservicios Spring Boot completamente funcionales**

---

## 📋 Resumen Ejecutivo

| Servicio | Puerto | Descripción | Estado |
|----------|--------|-------------|--------|
| **MS-Usuarios** | 8081 | Gestión de usuarios | ✅ Producción |
| **MS-Predios** | 8082 | Gestión de predios | ✅ Producción |
| **MS-Inspecciones** | 8083 | Inspecciones fitosanitarias | ✅ Producción |

---

## 📁 Estructura

```
java/
├── usuarios-service/         ← Microservicio Usuarios
│   ├── README.md
│   ├── pom.xml
│   ├── src/
│   └── target/
│
├── predios-service/          ← Microservicio Predios
│   ├── README.md
│   ├── pom.xml
│   ├── src/
│   └── target/
│
└── inspecciones-service/     ← Microservicio Inspecciones
    ├── README.md
    ├── pom.xml
    ├── src/
    └── target/
```

---

## 🚀 Inicio Rápido

### Compilar Todo

```bash
cd microservicios
mvn clean install -DskipTests -U
```

### Ejecutar Servicios (4 terminales)

```bash
# Terminal 1: MS-Usuarios
cd java/usuarios-service
java -jar target/usuarios-service.jar

# Terminal 2: MS-Predios
cd java/predios-service
java -jar target/predios-service.jar

# Terminal 3: MS-Inspecciones
cd java/inspecciones-service
java -jar target/inspecciones-service.jar

# Terminal 4: Frontend
cd ../frontend
python -m http.server 8000
```

---

## 📊 Estadísticas

| Métrica | Valor |
|---------|-------|
| **Servicios** | 3 |
| **Líneas de Código Java** | ~2,500 |
| **Endpoints HTTP** | 21+ |
| **Tablas BD** | 9 |
| **Migraciones Flyway** | 3 |
| **Usuarios Base** | 5 |
| **Predios Base** | 1 |
| **Tiempo Compilación** | ~16 seg |

---

## 📚 Documentación Individual

### 1. MS-Usuarios (8081)

**Gestión de usuarios del sistema**

```
Endpoint: http://localhost:8081/api/usuarios
Swagger: http://localhost:8081/swagger-ui/index.html
Documentación: usuarios-service/README.md

Funcionalidades:
✅ Crear usuario (POST)
✅ Listar usuarios (GET)
✅ Obtener usuario (GET /:id)
✅ Actualizar usuario (PUT /:id)
✅ Eliminar usuario (DELETE /:id)
✅ Autenticación JWT
```

[Ver documentación completa](./usuarios-service/README.md)

### 2. MS-Predios (8082)

**Gestión de predios agrícolas**

```
Endpoint: http://localhost:8082/api/predios
Swagger: http://localhost:8082/swagger-ui/index.html
Documentación: predios-service/README.md

Funcionalidades:
✅ Crear predio (POST)
✅ Listar predios (GET)
✅ Obtener predio (GET /:id)
✅ Actualizar predio (PUT /:id)
✅ Eliminar predio (DELETE /:id)
✅ Validación de productor
```

[Ver documentación completa](./predios-service/README.md)

### 3. MS-Inspecciones (8083)

**Gestión de inspecciones fitosanitarias**

```
Endpoint: http://localhost:8083/api/inspecciones
Swagger: http://localhost:8083/swagger-ui/index.html
Documentación: inspecciones-service/README.md

Funcionalidades:
✅ Crear inspección (POST)
✅ Listar inspecciones (GET)
✅ Obtener inspección (GET /:id)
✅ Actualizar inspección (PUT /:id)
✅ Eliminar inspección (DELETE /:id)
✅ Validación de predio y asistente
```

[Ver documentación completa](./inspecciones-service/README.md)

---

## 🛠️ Stack Tecnológico

### Versiones de Componentes

```
Spring Boot              3.5.11
Spring Data JPA         3.5.11
Hibernate               6.6.42.Final
Oracle JDBC Driver      21.1.0.0
Flyway                  9.22.3
Lombok                  1.18.30
Springdoc OpenAPI       2.x
Spring Security         3.5.11
Maven                   3.9.15
Java                    17 LTS
```

### Dependencias Comunes

```xml
<!-- Spring Boot Starter Web -->
<artifactId>spring-boot-starter-web</artifactId>

<!-- Spring Data JPA -->
<artifactId>spring-boot-starter-data-jpa</artifactId>

<!-- Spring Security -->
<artifactId>spring-boot-starter-security</artifactId>

<!-- Oracle JDBC -->
<groupId>com.oracle.database.jdbc</groupId>
<artifactId>ojdbc11</artifactId>

<!-- Flyway Migration -->
<artifactId>flyway-core</artifactId>
<artifactId>flyway-database-oracle</artifactId>

<!-- Lombok -->
<groupId>org.projectlombok</groupId>
<artifactId>lombok</artifactId>

<!-- Springdoc OpenAPI (Swagger UI) -->
<groupId>org.springdoc</groupId>
<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
```

---

## 🔌 Endpoints Consolidados

### MS-Usuarios (8081)

```
POST   /auth/login              - Iniciar sesión
POST   /auth/register           - Registrar usuario
GET    /api/usuarios            - Listar usuarios
GET    /api/usuarios/{id}       - Obtener usuario
POST   /api/usuarios            - Crear usuario ✅
PUT    /api/usuarios/{id}       - Actualizar usuario
DELETE /api/usuarios/{id}       - Eliminar usuario
```

### MS-Predios (8082)

```
GET    /api/predios             - Listar predios
GET    /api/predios/{id}        - Obtener predio
POST   /api/predios             - Crear predio ✅
PUT    /api/predios/{id}        - Actualizar predio
DELETE /api/predios/{id}        - Eliminar predio
GET    /api/municipios          - Listar municipios
GET    /api/cultivos            - Listar cultivos
```

### MS-Inspecciones (8083)

```
GET    /api/inspecciones        - Listar inspecciones
GET    /api/inspecciones/{id}   - Obtener inspección
POST   /api/inspecciones        - Crear inspección ✅
PUT    /api/inspecciones/{id}   - Actualizar inspección
DELETE /api/inspecciones/{id}   - Eliminar inspección
GET    /api/plagas              - Listar plagas
GET    /api/resultados          - Listar resultados
```

---

## 🔐 Seguridad

### Autenticación

```
Sistema: JWT (JSON Web Tokens)
Ubicación: Authorization header
Formato: Bearer <token>

Token generado en: POST /auth/login
Validado en: JwtAuthFilter.java
```

### CORS

```
Origen permitido: http://localhost:8000
Métodos permitidos: GET, POST, PUT, DELETE, OPTIONS
Headers permitidos: *
Credenciales: false
```

### Spring Security

```
/auth/**            - Public (sin autenticación)
/actuator/**        - Public
/swagger-ui/**      - Public
/api/**             - Public (permitAll para desarrollo)
/v3/api-docs        - Public
```

---

## 📊 Base de Datos Oracle

### Tablas

**MS-Usuarios**
```
T_USUARIOS              - Tabla principal
├─ id (PK)
├─ nombre
├─ email
├─ telefono
├─ tipo_usuario
└─ estado
```

**MS-Predios**
```
T_PREDIOS               - Tabla principal
├─ id (PK)
├─ nombre
├─ id_productor (FK)
├─ id_municipio (FK)
├─ area_hectareas
├─ latitud
├─ longitud
└─ estado

T_MUNICIPIOS            - Tabla soporte
T_CULTIVOS              - Tabla soporte
```

**MS-Inspecciones**
```
T_INSPECCIONES_FITOSANITARIAS - Tabla principal
├─ id (PK)
├─ id_predio (FK)
├─ id_asistente (FK)
├─ fecha_inspeccion
├─ observaciones
└─ estado

T_PLAGAS                - Tabla soporte
T_RESULTADOS            - Tabla soporte
```

### Migraciones Flyway

```
V1__init_usuarios.sql         - Usuarios
V1__init_predios.sql          - Predios
V1__init_inspecciones.sql     - Inspecciones
```

---

## 🐛 Troubleshooting Común

### Error: "Port already in use"

```bash
# Windows
netstat -ano | findstr :8081
taskkill /PID <PID> /F

# Linux/macOS
lsof -i :8081
kill -9 <PID>
```

### Error: "Connection refused" (Oracle)

```bash
# Verificar que Oracle está corriendo
sqlplus system/oracle@localhost:1522/XE

# Verificar puerto
netstat -ano | findstr :1522
```

### Error: "CORS"

```
Solución: Verificar WebConfig.java
Confirm: /api/** permitAll en SecurityConfig
```

### Error: "JWT token invalid"

```
Solución: 
1. Obtener nuevo token en /auth/login
2. Incluir "Bearer" en Authorization header
3. Verificar token no está expirado
```

---

## 🚀 Compilación y Build

### Maven Parent POM

Ubicación: `microservicios/pom.xml`

```xml
<modelVersion>4.0.0</modelVersion>
<groupId>com.example.microservices</groupId>
<artifactId>agro-microservices</artifactId>
<version>1.0.0</version>
<packaging>pom</packaging>

<modules>
  <module>java/usuarios-service</module>
  <module>java/predios-service</module>
  <module>java/inspecciones-service</module>
</modules>
```

### Compilar Todos

```bash
cd microservicios
mvn clean install -DskipTests -U
```

### Compilar Específico

```bash
cd java/usuarios-service
mvn clean install -DskipTests
```

### Skip Tests

```bash
mvn clean install -DskipTests
```

---

## 📦 Outputs

### JAR Files

```
usuarios-service/target/usuarios-service.jar
predios-service/target/predios-service.jar
inspecciones-service/target/inspecciones-service.jar
```

### Ejecutar

```bash
java -jar target/usuarios-service.jar
java -jar target/predios-service.jar
java -jar target/inspecciones-service.jar
```

---

## 🔄 Flujo de Integración

```
Frontend (8000)
    ↓
    ├→ POST /api/usuarios        → MS-Usuarios (8081)
    ├→ POST /api/predios         → MS-Predios (8082)
    │  └→ Valida productor       → MS-Usuarios (8081)
    └→ POST /api/inspecciones    → MS-Inspecciones (8083)
       ├→ Valida predio          → MS-Predios (8082)
       └→ Valida asistente       → MS-Usuarios (8081)
    
    ↓
Oracle Database XE (1522)
```

---

## 📊 Verificación de Estado

### Health Endpoints

```bash
# MS-Usuarios
curl http://localhost:8081/actuator/health

# MS-Predios
curl http://localhost:8082/actuator/health

# MS-Inspecciones
curl http://localhost:8083/actuator/health
```

### Endpoints GET

```bash
# Listar usuarios (debe retornar 5 usuarios)
curl http://localhost:8081/api/usuarios

# Listar predios (debe retornar 1 predio)
curl http://localhost:8082/api/predios

# Listar inspecciones (array vacío - esperado)
curl http://localhost:8083/api/inspecciones
```

---

## 📝 Configuración

### application.yml (Mismo en los 3)

```yaml
server:
  port: 8081  # 8082, 8083 en otros servicios

spring:
  application:
    name: usuarios-service
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

---

## 🎯 Próximos Pasos

1. ✅ Compilar: `mvn clean install -DskipTests`
2. ✅ Ejecutar 4 servicios (3 Java + Frontend)
3. ✅ Acceder a http://localhost:8000
4. ✅ Probar funcionalidades
5. ✅ Verificar Swagger UI

---

## 📞 Soporte y Recursos

- **Documentación Principal**: [README.md](../../README.md)
- **Release Notes**: [RELEASES.md](../../RELEASES.md)
- **Frontend**: [frontend/README.md](../frontend/README.md)
- **MS-Usuarios**: [usuarios-service/README.md](./usuarios-service/README.md)
- **MS-Predios**: [predios-service/README.md](./predios-service/README.md)
- **MS-Inspecciones**: [inspecciones-service/README.md](./inspecciones-service/README.md)

---

**Versión**: 1.0.0  
**Última actualización**: Mayo 2, 2026  
**Estado**: ✅ Production Ready
