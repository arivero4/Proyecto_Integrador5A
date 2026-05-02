# 📖 Referencia de APIs - Sistema de Gestión Agrícola

## 📋 Tabla de Contenidos

1. [Autenticación](#autenticación)
2. [MS-USUARIOS](#ms-usuarios-puerto-8081)
3. [MS-TERRITORIAL](#ms-territorial-puerto-8082)
4. [MS-INSPECCIONES](#ms-inspecciones-puerto-8083)
5. [Códigos de Error](#códigos-de-error)
6. [Formatos de Respuesta](#formatos-de-respuesta)

---

## 🔒 Autenticación

En esta versión, todos los endpoints son accesibles sin autenticación.

**Próxima versión:** Implementar JWT Token

---

## 🔷 MS-USUARIOS (Puerto 8081)

### Base URL
```
http://localhost:8081/api
```

### 📤 Usuarios

#### Listar Usuarios
```http
GET /usuarios
```

**Respuesta (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Juan Pérez",
    "email": "juan@example.com",
    "telefono": "+57 300 123 4567",
    "tipoUsuario": "PRODUCTOR",
    "estado": "ACTIVO"
  }
]
```

---

#### Crear Usuario
```http
POST /usuarios
Content-Type: application/json

{
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "telefono": "+57 300 123 4567",
  "tipoUsuario": "PRODUCTOR",
  "estado": "ACTIVO"
}
```

**Parámetros:**
| Campo | Tipo | Obligatorio | Validación |
|-------|------|-------------|-----------|
| nombre | String | Sí | No vacío, 3-100 caracteres |
| email | String | Sí | Email válido, único |
| telefono | String | No | Formato: +57 XXX XXX XXXX |
| tipoUsuario | Enum | Sí | PRODUCTOR, ASISTENTE_TECNICO, ADMIN |
| estado | Enum | Sí | ACTIVO, INACTIVO |

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "telefono": "+57 300 123 4567",
  "tipoUsuario": "PRODUCTOR",
  "estado": "ACTIVO"
}
```

---

#### Obtener Usuario por ID
```http
GET /usuarios/{id}
```

**Ejemplo:**
```http
GET /usuarios/1
```

**Respuesta (200 OK):**
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "telefono": "+57 300 123 4567",
  "tipoUsuario": "PRODUCTOR",
  "estado": "ACTIVO"
}
```

---

#### Actualizar Usuario
```http
PUT /usuarios/{id}
Content-Type: application/json

{
  "nombre": "Juan Carlos Pérez",
  "email": "juan.nuevo@example.com",
  "telefono": "+57 300 999 9999",
  "tipoUsuario": "PRODUCTOR",
  "estado": "ACTIVO"
}
```

**Respuesta (200 OK):** Objeto usuario actualizado

---

#### Eliminar Usuario
```http
DELETE /usuarios/{id}
```

**Respuesta (204 No Content)**

---

### 👨‍🌾 Productores

#### Crear Productor
```http
POST /productores
Content-Type: application/json

{
  "idUsuario": 1,
  "cedula": "123456789",
  "razonSocial": "Fincas Pérez & Asociados",
  "numeroHectareas": 50.5,
  "certificaciones": "Certificado Orgánico"
}
```

**Parámetros:**
| Campo | Tipo | Obligatorio |
|-------|------|-------------|
| idUsuario | Long | Sí |
| cedula | String | Sí |
| razonSocial | String | No |
| numeroHectareas | Double | Sí |
| certificaciones | String | No |

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "idUsuario": 1,
  "cedula": "123456789",
  "razonSocial": "Fincas Pérez & Asociados",
  "numeroHectareas": 50.5,
  "certificaciones": "Certificado Orgánico"
}
```

---

#### Listar Productores
```http
GET /productores
```

**Respuesta (200 OK):**
```json
[
  {
    "id": 1,
    "idUsuario": 1,
    "cedula": "123456789",
    "razonSocial": "Fincas Pérez & Asociados",
    "numeroHectareas": 50.5,
    "certificaciones": "Certificado Orgánico"
  }
]
```

---

#### Obtener Productor
```http
GET /productores/{id}
```

---

#### Actualizar Productor
```http
PUT /productores/{id}
Content-Type: application/json
```

---

#### Eliminar Productor
```http
DELETE /productores/{id}
```

---

### 👨‍💼 Asistentes Técnicos

Misma estructura que **Productores**:
- `POST /asistentes` - Crear
- `GET /asistentes` - Listar
- `GET /asistentes/{id}` - Obtener
- `PUT /asistentes/{id}` - Actualizar
- `DELETE /asistentes/{id}` - Eliminar

---

### 🏥 Health Check
```http
GET /usuarios/health
```

**Respuesta:**
```json
{
  "status": "UP",
  "service": "ms-usuarios",
  "port": 8081,
  "timestamp": 1714557600000
}
```

---

## 🔶 MS-TERRITORIAL (Puerto 8082)

### Base URL
```
http://localhost:8082/api
```

### 🏛️ Municipios

#### Crear Municipio
```http
POST /municipios
Content-Type: application/json

{
  "nombre": "Bogotá",
  "departamento": "Cundinamarca",
  "codigoDane": "11001"
}
```

**Parámetros:**
| Campo | Tipo | Obligatorio |
|-------|------|-------------|
| nombre | String | Sí |
| departamento | String | Sí |
| codigoDane | String | No |

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "nombre": "Bogotá",
  "departamento": "Cundinamarca",
  "codigoDane": "11001"
}
```

---

#### Listar Municipios
```http
GET /municipios
```

---

#### Obtener Municipio
```http
GET /municipios/{id}
```

---

### 🏞️ Predios

#### Crear Predio
```http
POST /predios
Content-Type: application/json

{
  "idProductor": 1,
  "nombre": "Finca Las Mercedes",
  "idMunicipio": 1,
  "areaHectareas": 50.5,
  "latitud": 4.7110,
  "longitud": -74.0721,
  "estado": "ACTIVO"
}
```

**Parámetros:**
| Campo | Tipo | Obligatorio |
|-------|------|-------------|
| idProductor | Long | Sí |
| nombre | String | Sí |
| idMunicipio | Long | Sí |
| areaHectareas | Double | Sí |
| latitud | Double | No |
| longitud | Double | No |
| estado | Enum | Sí |

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "idProductor": 1,
  "nombre": "Finca Las Mercedes",
  "idMunicipio": 1,
  "areaHectareas": 50.5,
  "latitud": 4.7110,
  "longitud": -74.0721,
  "estado": "ACTIVO"
}
```

---

#### Listar Predios
```http
GET /predios
```

---

#### Obtener Predio
```http
GET /predios/{id}
```

---

#### Listar Predios por Productor
```http
GET /predios/productor/{idProductor}
```

---

#### Actualizar Predio
```http
PUT /predios/{id}
Content-Type: application/json
```

---

#### Eliminar Predio
```http
DELETE /predios/{id}
```

---

### 🌾 Cultivos

#### Crear Cultivo
```http
POST /cultivos
Content-Type: application/json

{
  "idPredio": 1,
  "nombre": "Café",
  "fechaSiembra": "2024-04-01",
  "fechaCosecha": "2025-04-01",
  "areaSembrada": 10.5,
  "estado": "ACTIVO"
}
```

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "idPredio": 1,
  "nombre": "Café",
  "fechaSiembra": "2024-04-01",
  "fechaCosecha": "2025-04-01",
  "areaSembrada": 10.5,
  "estado": "ACTIVO"
}
```

---

#### Listar Cultivos
```http
GET /cultivos
```

---

#### Listar Cultivos por Predio
```http
GET /cultivos/predio/{idPredio}
```

---

### 🏥 Health Check
```http
GET /predios/health
```

---

## 🔴 MS-INSPECCIONES (Puerto 8083)

### Base URL
```
http://localhost:8083/api
```

### 🔬 Inspecciones Fitosanitarias

#### Crear Inspección
```http
POST /inspecciones
Content-Type: application/json

{
  "idPredio": 1,
  "idAsistente": 1,
  "fechaInspeccion": "2024-05-01T10:00:00",
  "observaciones": "Inspección inicial del predio",
  "estado": "EN_PROCESO"
}
```

**Parámetros:**
| Campo | Tipo | Obligatorio |
|-------|------|-------------|
| idPredio | Long | Sí |
| idAsistente | Long | Sí |
| fechaInspeccion | DateTime | Sí |
| observaciones | String | No |
| estado | Enum | Sí |

**Estados Válidos:**
- PENDIENTE
- EN_PROCESO
- COMPLETADA

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "idPredio": 1,
  "idAsistente": 1,
  "fechaInspeccion": "2024-05-01T10:00:00",
  "observaciones": "Inspección inicial del predio",
  "estado": "EN_PROCESO"
}
```

---

#### Listar Inspecciones
```http
GET /inspecciones
```

---

#### Obtener Inspección
```http
GET /inspecciones/{id}
```

---

#### Listar Inspecciones por Predio
```http
GET /inspecciones/predio/{idPredio}
```

---

#### Actualizar Inspección
```http
PUT /inspecciones/{id}
```

---

#### Eliminar Inspección
```http
DELETE /inspecciones/{id}
```

---

### 🐛 Plagas

#### Crear Plaga
```http
POST /plagas
Content-Type: application/json

{
  "idInspeccion": 1,
  "nombrePlaga": "Roya del Café",
  "tipoPlaga": "HONGO",
  "severidad": "MEDIA",
  "porcentajeAfectacion": 35.5
}
```

**Parámetros:**
| Campo | Tipo | Obligatorio |
|-------|------|-------------|
| idInspeccion | Long | Sí |
| nombrePlaga | String | Sí |
| tipoPlaga | Enum | Sí |
| severidad | Enum | Sí |
| porcentajeAfectacion | Double | Sí |

**Tipos de Plaga:**
- INSECTO
- HONGO
- BACTERIA
- VIRUS
- MALEZA

**Severidades:**
- BAJA (0-25%)
- MEDIA (25-50%)
- ALTA (50-75%)
- CRITICA (75%+)

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "idInspeccion": 1,
  "nombrePlaga": "Roya del Café",
  "tipoPlaga": "HONGO",
  "severidad": "MEDIA",
  "porcentajeAfectacion": 35.5
}
```

---

#### Listar Plagas
```http
GET /plagas
```

---

#### Obtener Plaga
```http
GET /plagas/{id}
```

---

#### Listar Plagas por Inspección
```http
GET /plagas/inspeccion/{idInspeccion}
```

---

### 📋 Resultados

#### Crear Resultado
```http
POST /resultados
Content-Type: application/json

{
  "idInspeccion": 1,
  "recomendacion": "Aplicar fungicida sistémico",
  "tratamientoSugerido": "Triazol 25%",
  "fechaSeguimiento": "2024-05-08T10:00:00",
  "estado": "PENDIENTE"
}
```

**Parámetros:**
| Campo | Tipo | Obligatorio |
|-------|------|-------------|
| idInspeccion | Long | Sí |
| recomendacion | String | Sí |
| tratamientoSugerido | String | Sí |
| fechaSeguimiento | DateTime | No |
| estado | Enum | Sí |

**Estados:**
- PENDIENTE
- EN_EJECUCION
- COMPLETADO

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "idInspeccion": 1,
  "recomendacion": "Aplicar fungicida sistémico",
  "tratamientoSugerido": "Triazol 25%",
  "fechaSeguimiento": "2024-05-08T10:00:00",
  "estado": "PENDIENTE"
}
```

---

#### Listar Resultados
```http
GET /resultados
```

---

#### Obtener Resultado
```http
GET /resultados/{id}
```

---

#### Listar Resultados por Inspección
```http
GET /resultados/inspeccion/{idInspeccion}
```

---

### 🏥 Health Check
```http
GET /inspecciones/health
```

---

## ⚠️ Códigos de Error

| Código | Descripción | Causa |
|--------|------------|-------|
| 200 | OK | Solicitud exitosa |
| 201 | Created | Recurso creado exitosamente |
| 204 | No Content | Eliminación exitosa |
| 400 | Bad Request | Datos inválidos o incompletos |
| 404 | Not Found | Recurso no encontrado |
| 409 | Conflict | Email duplicado o constraint violation |
| 500 | Internal Server Error | Error en el servidor |

---

## 📦 Formatos de Respuesta

### ✅ Éxito (200, 201)
```json
{
  "id": 1,
  "nombre": "...",
  "...": "..."
}
```

### ❌ Error (400, 404, 500)
```json
{
  "error": "Not Found",
  "message": "Usuario con ID 999 no encontrado",
  "status": 404,
  "timestamp": "2024-05-01T10:00:00Z"
}
```

---

## 🔄 Flujo de Datos Recomendado

```
1. Crear Usuario (Productor)
   ↓
2. Crear Productor (vinculado a Usuario)
   ↓
3. Crear Municipio
   ↓
4. Crear Predio (vinculado a Productor y Municipio)
   ↓
5. Crear Cultivo (vinculado a Predio)
   ↓
6. Crear Usuario (Asistente Técnico)
   ↓
7. Crear Asistente (vinculado a Usuario)
   ↓
8. Crear Inspección (vinculado a Predio y Asistente)
   ↓
9. Crear Plaga (vinculada a Inspección)
   ↓
10. Crear Resultado (vinculado a Inspección)
```

---

## 🧪 Testing

### Con cURL
```bash
# Health check
curl -X GET http://localhost:8081/api/usuarios/health

# Crear usuario
curl -X POST http://localhost:8081/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Test User",
    "email": "test@example.com",
    "telefono": "+57 300 000 0000",
    "tipoUsuario": "PRODUCTOR",
    "estado": "ACTIVO"
  }'
```

### Con Postman
1. Importar [Postman-Collection.json](./Postman-Collection.json)
2. Usar requests predefinidas
3. Modificar datos según necesidad

### Con PowerShell
```powershell
.\test-apis.ps1
```

---

**Última actualización:** Mayo 2024
