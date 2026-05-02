# 🔍 MS-Inspecciones - Microservicio de Inspecciones Fitosanitarias

**API REST para gestión de inspecciones fitosanitarias, plagas y resultados**

---

## 📊 Descripción

Microservicio Spring Boot para gestión de inspecciones agrícolas:

- **Inspecciones Fitosanitarias**: Inspecciones de salud de cultivos
- **Plagas**: Registro de plagas detectadas
- **Resultados**: Resultados y observaciones de inspecciones

---

## ✨ Características

### CRUD Completo
✅ **GET** `/api/inspecciones` - Listar todos  
✅ **GET** `/api/inspecciones/{id}` - Obtener uno  
✅ **POST** `/api/inspecciones` - Crear nuevo ✅ **FUNCIONAL**  
✅ **PUT** `/api/inspecciones/{id}` - Actualizar  
✅ **DELETE** `/api/inspecciones/{id}` - Eliminar  

### Gestión de Plagas
✅ **GET** `/api/plagas` - Listar plagas  
✅ **POST** `/api/plagas` - Registrar plaga  

### Gestión de Resultados
✅ **GET** `/api/resultados` - Listar resultados  
✅ **POST** `/api/resultados` - Crear resultado  

### Validaciones
✅ Valida que predio existe (llama MS-Predios)  
✅ Valida que asistente existe (llama MS-Usuarios)  
✅ Fecha de inspección requerida y válida  
✅ Campos requeridos validados  

### Documentación
✅ Swagger UI en `/swagger-ui/index.html`  
✅ OpenAPI 3.0 completo  

---

## 🔌 Endpoints

### Inspecciones

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| **GET** | `/api/inspecciones` | Listar todas | ✅ |
| **GET** | `/api/inspecciones/{id}` | Obtener por ID | ✅ |
| **GET** | `/api/inspecciones/predio/{idPredio}` | Por predio | ✅ |
| **POST** | `/api/inspecciones` | Crear nueva | ✅ |
| **PUT** | `/api/inspecciones/{id}` | Actualizar | ✅ |
| **DELETE** | `/api/inspecciones/{id}` | Eliminar | ✅ |

### Plagas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **GET** | `/api/plagas` | Listar plagas |
| **POST** | `/api/plagas` | Registrar plaga |

### Resultados

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| **GET** | `/api/resultados` | Listar resultados |
| **POST** | `/api/resultados` | Crear resultado |

---

## 📊 Modelos de Datos

### InspeccionFitosanitariaDTO

```json
{
  "id": 1,
  "idPredio": 1,
  "idAsistente": 3,
  "fechaInspeccion": "2026-05-02T10:30:00Z",
  "observaciones": "Cultivo en buen estado",
  "estado": "PENDIENTE"
}
```

### Estados de Inspección

```
- PENDIENTE   → No iniciada
- EN_PROGRESO → En desarrollo
- COMPLETADA  → Terminada
- CANCELADA   → Cancelada
```

### PlagaDTO

```json
{
  "id": 1,
  "nombre": "Mosca Blanca",
  "descripcion": "Insecto plaga de cultivos",
  "nivel_riesgo": "ALTO"
}
```

### ResultadoDTO

```json
{
  "id": 1,
  "idInspeccion": 1,
  "idPlaga": 1,
  "cantidad": 150,
  "recomendacion": "Aplicar fungicida"
}
```

---

## 💻 Ejemplos de Uso

### Crear Inspección ✅

```bash
curl -X POST http://localhost:8083/api/inspecciones \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "idPredio": 1,
    "idAsistente": 3,
    "fechaInspeccion": "2026-05-02T10:30:00Z",
    "observaciones": "Inspección inicial del cultivo",
    "estado": "PENDIENTE"
  }'
```

### Listar Inspecciones

```bash
curl -X GET http://localhost:8083/api/inspecciones \
  -H "Authorization: Bearer <TOKEN>"
```

### Inspecciones por Predio

```bash
curl -X GET http://localhost:8083/api/inspecciones/predio/1 \
  -H "Authorization: Bearer <TOKEN>"
```

### Obtener Inspección

```bash
curl -X GET http://localhost:8083/api/inspecciones/1 \
  -H "Authorization: Bearer <TOKEN>"
```

### Actualizar Inspección

```bash
curl -X PUT http://localhost:8083/api/inspecciones/1 \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "idPredio": 1,
    "idAsistente": 3,
    "fechaInspeccion": "2026-05-02T14:00:00Z",
    "observaciones": "Inspección completada",
    "estado": "COMPLETADA"
  }'
```

---

## 📁 Estructura

```
inspecciones-service/
├── src/main/java/com/example/inspecciones/
│   ├── controller/
│   │   └── InspeccionController.java
│   ├── service/
│   │   └── InspeccionService.java
│   ├── entity/
│   │   ├── InspeccionFitosanitariaEntity.java
│   │   ├── PlagaEntity.java
│   │   └── ResultadoEntity.java
│   ├── repository/
│   │   └── InspeccionRepository.java
│   ├── dto/
│   │   └── InspeccionFitosanitariaDTO.java
│   ├── config/
│   │   ├── SecurityConfig.java
│   │   └── WebConfig.java
│   └── security/
│       └── JwtAuthFilter.java
├── src/main/resources/
│   ├── application.yml
│   └── db/migration/
│       └── V1__init_inspecciones.sql
├── pom.xml
└── README.md
```

---

## ⚙️ Configuración

### application.yml

```yaml
server:
  port: 8083

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

### Crear Inspección

1. ✅ Campo "idPredio" requerido
2. ✅ Campo "idAsistente" requerido
3. ✅ Campo "fechaInspeccion" requerido (formato ISO 8601)
4. ✅ Verifica que predio existe (llama MS-Predios)
5. ✅ Verifica que asistente existe (llama MS-Usuarios)
6. ✅ Valida que asistente sea de tipo ASISTENTE_TECNICO

### Respuesta de Error

```json
{
  "timestamp": "2026-05-02T10:15:30Z",
  "status": 400,
  "error": "Validation Failed",
  "message": "El predio con ID 999 no existe"
}
```

---

## 📊 Base de Datos

### Tablas Principales

**T_INSPECCIONES_FITOSANITARIAS**
- id (PK)
- id_predio (FK)
- id_asistente (FK)
- fecha_inspeccion
- observaciones
- estado

**T_PLAGAS**
- id (PK)
- nombre
- descripcion
- nivel_riesgo

**T_RESULTADOS**
- id (PK)
- id_inspeccion (FK)
- id_plaga (FK)
- cantidad
- recomendacion

---

## 🚀 Instalación

```bash
# Compilar
cd microservicios/java/inspecciones-service
mvn clean install -DskipTests

# Ejecutar
java -jar target/inspecciones-service.jar
```

---

## 🔄 Integraciones con Otros Servicios

### MS-Predios

```
Cuando: POST /api/inspecciones
Valida: Que el idPredio existe
Endpoint: GET http://localhost:8082/api/predios/{idPredio}
```

### MS-Usuarios

```
Cuando: POST /api/inspecciones
Valida: Que el idAsistente existe y es ASISTENTE_TECNICO
Endpoint: GET http://localhost:8081/api/usuarios/{idAsistente}
```

---

## 🐛 Troubleshooting

### Error: "Predio no existe"

```
Problema: ID del predio inválido
Solución:
1. Verificar que MS-Predios está corriendo
2. Usar IDs validos de predios
3. Crear nuevo predio si necesario
```

### Error: "Asistente no existe o no es técnico"

```
Problema: ID del asistente inválido o rol incorrecto
Solución:
1. Usar ID de usuario con rol ASISTENTE_TECNICO
2. IDs válidos: 3 (Usuario Dos es ASISTENTE_TECNICO)
3. Crear nuevo asistente en MS-Usuarios si necesario
```

### Error: "Fecha inválida"

```
Problema: Formato de fecha incorrecto
Solución:
1. Usar formato ISO 8601: "2026-05-02T10:30:00Z"
2. Incluir hora y zona horaria
```

---

## 📞 Soporte

- **Swagger**: http://localhost:8083/swagger-ui/index.html
- **API Docs**: http://localhost:8083/v3/api-docs

---

**Versión**: 1.0.0  
**Última actualización**: Mayo 2, 2026  
**Estado**: ✅ Production Ready
