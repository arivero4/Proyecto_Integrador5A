# 🏞️ MS-Predios - Microservicio de Gestión de Predios

**API REST para gestión de municipios, predios agrícolas y cultivos**

---

## 📊 Descripción

Microservicio Spring Boot para gestión de predios agrícolas:

- **Municipios**: Ubicaciones geográficas
- **Predios**: Terrenos agrícolas con productor asociado
- **Cultivos**: Tipos de cultivos en cada predio

---

## ✨ Características

### CRUD Completo
✅ **GET** `/api/predios` - Listar todos  
✅ **GET** `/api/predios/{id}` - Obtener uno  
✅ **POST** `/api/predios` - Crear nuevo ✅ **FUNCIONAL**  
✅ **PUT** `/api/predios/{id}` - Actualizar  
✅ **DELETE** `/api/predios/{id}` - Eliminar  

### Gestión de Municipios
✅ **GET** `/api/municipios` - Listar municipios  
✅ **POST** `/api/municipios` - Crear municipio  

### Gestión de Cultivos
✅ **GET** `/api/cultivos` - Listar cultivos  
✅ **POST** `/api/cultivos` - Crear cultivo  

### Validaciones
✅ Valida que productor exista (llamada a MS-Usuarios)  
✅ Valida que municipio exista  
✅ Campos requeridos validados  
✅ Área en hectáreas debe ser positiva  

### Documentación
✅ Swagger UI en `/swagger-ui/index.html`  
✅ OpenAPI 3.0 completo  

---

## 🔌 Endpoints

### Predios

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| **GET** | `/api/predios` | Listar todos | ✅ |
| **GET** | `/api/predios/{id}` | Obtener por ID | ✅ |
| **POST** | `/api/predios` | Crear nuevo | ✅ |
| **PUT** | `/api/predios/{id}` | Actualizar | ✅ |
| **DELETE** | `/api/predios/{id}` | Eliminar | ✅ |

### Municipios

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **GET** | `/api/municipios` | Listar municipios |
| **POST** | `/api/municipios` | Crear municipio |

### Cultivos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **GET** | `/api/cultivos` | Listar cultivos |
| **POST** | `/api/cultivos` | Crear cultivo |

---

## 📊 Modelos de Datos

### PredioDTO

```json
{
  "id": 1,
  "nombre": "Finca La Esperanza",
  "idProductor": 2,
  "idMunicipio": 1,
  "areaHectareas": 45.5,
  "latitud": 11.2456,
  "longitud": -85.2654,
  "estado": "ACTIVO"
}
```

### MunicipioDTO

```json
{
  "id": 1,
  "nombre": "Granada",
  "departamento": "Granada"
}
```

### CultivoDTO

```json
{
  "id": 1,
  "nombre": "Cacao",
  "descripcion": "Cultivo de cacao para chocolate",
  "estado": "ACTIVO"
}
```

---

## 💻 Ejemplos de Uso

### Crear Predio ✅

```bash
curl -X POST http://localhost:8082/api/predios \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Nueva Finca",
    "idProductor": 1,
    "idMunicipio": 1,
    "areaHectareas": 50.0,
    "latitud": 11.2456,
    "longitud": -85.2654
  }'
```

### Listar Predios

```bash
curl -X GET http://localhost:8082/api/predios \
  -H "Authorization: Bearer <TOKEN>"
```

### Obtener Predio

```bash
curl -X GET http://localhost:8082/api/predios/1 \
  -H "Authorization: Bearer <TOKEN>"
```

### Actualizar Predio

```bash
curl -X PUT http://localhost:8082/api/predios/1 \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Finca Actualizada",
    "idProductor": 2,
    "idMunicipio": 1,
    "areaHectareas": 60.0,
    "latitud": 11.2456,
    "longitud": -85.2654
  }'
```

---

## 📁 Estructura

```
predios-service/
├── src/main/java/com/example/predios/
│   ├── controller/
│   │   └── PredioController.java
│   ├── service/
│   │   └── PredioService.java
│   ├── entity/
│   │   ├── PredioEntity.java
│   │   ├── MunicipioEntity.java
│   │   └── CultivoEntity.java
│   ├── repository/
│   │   └── PredioRepository.java
│   ├── dto/
│   │   └── PredioDTO.java
│   ├── config/
│   │   ├── SecurityConfig.java
│   │   └── WebConfig.java
│   └── security/
│       └── JwtAuthFilter.java
├── src/main/resources/
│   ├── application.yml
│   └── db/migration/
│       └── V1__init_predios.sql
├── pom.xml
└── README.md
```

---

## ⚙️ Configuración

### application.yml

```yaml
server:
  port: 8082

spring:
  datasource:
    url: jdbc:oracle:thin:@localhost:1522:XE
    username: system
    password: oracle
  jpa:
    database-platform: org.hibernate.dialect.OracleDialect
```

---

## 🔐 Validaciones

### Crear Predio

1. ✅ Campo "nombre" requerido
2. ✅ Campo "idProductor" requerido
3. ✅ Campo "idMunicipio" requerido
4. ✅ Campo "areaHectareas" requerido (debe ser > 0)
5. ✅ Verifica que productor existe (llama MS-Usuarios)
6. ✅ Verifica que municipio existe

### Respuesta de Error

```json
{
  "timestamp": "2026-05-02T10:15:30Z",
  "status": 400,
  "error": "Validation Failed",
  "message": "El productor con ID 999 no existe"
}
```

---

## 📊 Base de Datos

### Tablas Principales

**T_PREDIOS**
- id (PK)
- nombre
- id_productor (FK)
- id_municipio (FK)
- area_hectareas
- latitud
- longitud
- estado

**T_MUNICIPIOS**
- id (PK)
- nombre
- departamento

**T_CULTIVOS**
- id (PK)
- nombre
- descripcion
- estado

---

## 🚀 Instalación

```bash
# Compilar
cd microservicios/java/predios-service
mvn clean install -DskipTests

# Ejecutar
java -jar target/predios-service.jar
```

---

## 🐛 Troubleshooting

### Error: "Productor no existe"

```
Problema: ID del productor no válido
Solución:
1. Verificar que MS-Usuarios está corriendo
2. Usar IDs validos (1-5)
3. Crear nuevo usuario en MS-Usuarios
```

### Error: "Municipio no existe"

```
Problema: ID del municipio inválido
Solución:
1. Usar IDs de municipios válidos
2. Listar municipios disponibles
3. Crear nuevo municipio si necesario
```

---

## 📞 Soporte

- **Swagger**: http://localhost:8082/swagger-ui/index.html
- **API Docs**: http://localhost:8082/v3/api-docs

---

**Versión**: 1.0.0  
**Última actualización**: Mayo 2, 2026  
**Estado**: ✅ Production Ready
