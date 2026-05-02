# Sistema de Gestión Agrícola - Microservicios en Java con Oracle

## 📋 Descripción

Sistema de gestión agrícola implementado con arquitectura de microservicios en Java, Spring Boot y Oracle 10g/11g. El sistema gestiona:

- **Usuarios**: Productores y Asistentes Técnicos
- **Predios**: Propiedades agrícolas con cultivos
- **Inspecciones**: Inspecciones fitosanitarias y control de plagas

## 🏗️ Arquitectura

```
┌─────────────────────────────────────────────────────────────┐
│                    Clientes (REST APIs)                      │
└─────────────────────┬───────────────────────────────────────┘
                      │
        ┌─────────────┼─────────────┐
        │             │             │
   ┌────▼────┐  ┌───▼────┐  ┌────▼─────┐
   │ Usuarios │  │ Predios │  │Inspecciones│
   │ Service  │  │ Service │  │ Service   │
   │ (8081)   │  │ (8082)  │  │ (8083)    │
   └────┬────┘  └────┬────┘  └────┬─────┘
        │            │             │
        └────────────┼─────────────┘
                     │
              ┌──────▼──────┐
              │  Oracle 11g │
              │   (1521)    │
              └─────────────┘
```

## 📁 Estructura de Carpetas

```
microservicios/
├── pom.xml                          # POM padre (multi-módulo)
├── docker-compose.yml               # Orquestación de servicios
├── config/
│   └── oracle/
│       └── init.sql                 # Scripts SQL iniciales
├── java/
│   ├── usuarios-service/
│   │   ├── pom.xml
│   │   ├── Dockerfile
│   │   ├── src/main/java/com/example/usuarios/
│   │   │   ├── UsuariosServiceApplication.java
│   │   │   ├── controller/          # REST endpoints
│   │   │   ├── service/             # Lógica de negocio
│   │   │   ├── repository/          # Acceso a datos (JPA)
│   │   │   ├── entity/              # Entidades JPA
│   │   │   └── dto/                 # Data Transfer Objects
│   │   └── src/main/resources/
│   │       └── application.yml
│   ├── predios-service/
│   │   └── [misma estructura]
│   └── inspecciones-service/
│       └── [misma estructura]
└── README.md
```

## 🚀 Inicio Rápido

### Prerequisitos

- Docker y Docker Compose
- Maven 3.8.6+
- Java 17+
- Oracle JDBC Driver (ojdbc8)

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
cd microservicios
```

2. **Construir y levantar los servicios**
```bash
docker-compose up --build
```

3. **Verificar que los servicios estén corriendo**
```bash
docker-compose ps
```

## 🔌 API Endpoints

### Usuarios Service (Puerto 8081)

```
POST   /api/usuarios                          # Crear usuario
GET    /api/usuarios                          # Listar todos
GET    /api/usuarios/{id}                     # Obtener por ID
GET    /api/usuarios/tipo/{tipoUsuario}      # Listar por tipo
PUT    /api/usuarios/{id}                     # Actualizar
DELETE /api/usuarios/{id}                     # Eliminar

POST   /api/productores                       # Crear productor
GET    /api/productores                       # Listar todos
GET    /api/productores/{id}                  # Obtener por ID
PUT    /api/productores/{id}                  # Actualizar
DELETE /api/productores/{id}                  # Eliminar

POST   /api/asistentes                        # Crear asistente
GET    /api/asistentes                        # Listar todos
GET    /api/asistentes/{id}                   # Obtener por ID
PUT    /api/asistentes/{id}                   # Actualizar
DELETE /api/asistentes/{id}                   # Eliminar
```

### Predios Service (Puerto 8082)

```
POST   /api/predios                           # Crear predio
GET    /api/predios                           # Listar todos
GET    /api/predios/{id}                      # Obtener por ID
GET    /api/predios/productor/{idProductor}   # Listar por productor
PUT    /api/predios/{id}                      # Actualizar
DELETE /api/predios/{id}                      # Eliminar

POST   /api/cultivos                          # Crear cultivo
GET    /api/cultivos                          # Listar todos
GET    /api/cultivos/{id}                     # Obtener por ID
GET    /api/cultivos/predio/{idPredio}        # Listar por predio
PUT    /api/cultivos/{id}                     # Actualizar
DELETE /api/cultivos/{id}                     # Eliminar

POST   /api/municipios                        # Crear municipio
GET    /api/municipios                        # Listar todos
GET    /api/municipios/{id}                   # Obtener por ID
PUT    /api/municipios/{id}                   # Actualizar
DELETE /api/municipios/{id}                   # Eliminar
```

### Inspecciones Service (Puerto 8083)

```
POST   /api/inspecciones                      # Crear inspección
GET    /api/inspecciones                      # Listar todas
GET    /api/inspecciones/{id}                 # Obtener por ID
GET    /api/inspecciones/predio/{idPredio}    # Listar por predio
PUT    /api/inspecciones/{id}                 # Actualizar
DELETE /api/inspecciones/{id}                 # Eliminar

POST   /api/plagas                            # Crear plaga
GET    /api/plagas                            # Listar todas
GET    /api/plagas/{id}                       # Obtener por ID
GET    /api/plagas/inspeccion/{idInspeccion}   # Listar por inspección
PUT    /api/plagas/{id}                       # Actualizar
DELETE /api/plagas/{id}                       # Eliminar

POST   /api/resultados                        # Crear resultado
GET    /api/resultados                        # Listar todos
GET    /api/resultados/{id}                   # Obtener por ID
GET    /api/resultados/inspeccion/{idInspeccion} # Listar por inspección
PUT    /api/resultados/{id}                   # Actualizar
DELETE /api/resultados/{id}                   # Eliminar
```

## 📝 Ejemplo de Uso

### Crear un Usuario

```bash
curl -X POST http://localhost:8081/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "email": "juan@example.com",
    "telefono": "+57 300 123 4567",
    "tipoUsuario": "PRODUCTOR",
    "estado": "ACTIVO"
  }'
```

### Crear un Predio

```bash
curl -X POST http://localhost:8082/api/predios \
  -H "Content-Type: application/json" \
  -d '{
    "idProductor": 1,
    "nombre": "Finca Las Mercedes",
    "idMunicipio": 1,
    "areaHectareas": 50.5,
    "latitud": 4.7110,
    "longitud": -74.0721,
    "estado": "ACTIVO"
  }'
```

### Crear una Inspección

```bash
curl -X POST http://localhost:8083/api/inspecciones \
  -H "Content-Type: application/json" \
  -d '{
    "idPredio": 1,
    "idAsistente": 1,
    "fechaInspeccion": "2024-05-01T10:00:00",
    "observaciones": "Inspección inicial",
    "estado": "EN_PROCESO"
  }'
```

## 🗄️ Base de Datos

### Conexión a Oracle

```
Host: localhost
Puerto: 1521
SID: XE
Usuario: system
Contraseña: oracle
```

### Tablas Principales

**USUARIOS**
- T_USUARIOS
- T_PRODUCTORES
- T_ASISTENTES_TECNICOS

**PREDIOS**
- T_MUNICIPIOS
- T_PREDIOS
- T_CULTIVOS

**INSPECCIONES**
- T_INSPECCIONES
- T_PLAGAS
- T_RESULTADOS

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA**
- **Hibernate**
- **Oracle Database 11g**
- **Docker & Docker Compose**
- **Maven**
- **Lombok**

## 📊 Monitoreo y Salud

Cada servicio expone endpoints de health check:

```bash
curl http://localhost:8081/actuator/health
curl http://localhost:8082/actuator/health
curl http://localhost:8083/actuator/health
```

## 🔧 Desarrollo Local

### Construcción sin Docker

```bash
mvn clean install
cd java/usuarios-service
mvn spring-boot:run
```

### Variables de Entorno Opcionales

```bash
export SPRING_DATASOURCE_URL=jdbc:oracle:thin:@localhost:1521:XE
export SPRING_DATASOURCE_USERNAME=system
export SPRING_DATASOURCE_PASSWORD=oracle
```

## 🐛 Troubleshooting

### El servicio no conecta a Oracle
- Verificar que oracle-db está corriendo: `docker-compose ps`
- Esperar 40 segundos después de levantar los servicios
- Revisar logs: `docker-compose logs oracle-db`

### Puerto ya en uso
```bash
# Cambiar en docker-compose.yml o usar puertos diferentes
docker-compose down
```

## 📚 Documentación Adicional

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Oracle JDBC](https://docs.oracle.com/en/database/oracle/oracle-database/)

## 👥 Contribuciones

Para agregar nuevos microservicios:

1. Crear carpeta `java/nuevo-service`
2. Copiar estructura de un servicio existente
3. Actualizar `pom.xml` padre
4. Agregar servicio en `docker-compose.yml`

## 📄 Licencia

Este proyecto está bajo la licencia MIT.

---

**Última actualización**: Mayo 2024
