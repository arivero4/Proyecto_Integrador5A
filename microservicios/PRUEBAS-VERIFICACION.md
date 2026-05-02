# ✅ Guía de Pruebas y Verificación

## 🎯 Objetivos de Prueba

- ✅ Verificar que todos los servicios inician correctamente
- ✅ Validar endpoints REST de cada microservicio
- ✅ Confirmar comunicación con base de datos Oracle
- ✅ Probar flujos de negocio completos
- ✅ Verificar manejo de errores

---

## 📋 Lista de Verificación Pre-Prueba

### ✏️ Checklist Inicial

```
□ Docker Desktop instalado y ejecutándose
□ Docker Compose disponible
□ Maven 3.8.6+ instalado
□ Java 17+ instalado
□ Git clonado/actualizado
□ Puertos 8081, 8082, 8083, 1521 disponibles
□ Al menos 4GB RAM disponible
```

### Verificar Instalaciones

```bash
# Docker
docker --version
docker-compose --version

# Maven
mvn --version

# Java
java -version
```

---

## 🚀 Nivel 1: Inicio de Servicios

### Opción 1A: Docker Compose (Recomendado)

```bash
cd microservicios
docker-compose up --build
```

**Esperado después de ~60 segundos:**
```
oracle-db is healthy
usuarios-service is ready
predios-service is ready
inspecciones-service is ready
```

---

### Opción 1B: PowerShell Script

```powershell
cd microservicios
.\start-microservices.ps1
```

**Esperado:**
- 3 nuevas ventanas de PowerShell se abren
- Cada una compila e inicia un servicio
- Mensajes "Started" aparecen en cada ventana

---

### Opción 1C: Manual - Terminal Separadas

**Terminal 1:**
```bash
cd java/usuarios-service
mvn spring-boot:run
# Esperar: "Started UsuariosServiceApplication"
```

**Terminal 2:**
```bash
cd java/predios-service
mvn spring-boot:run
# Esperar: "Started PrediosServiceApplication"
```

**Terminal 3:**
```bash
cd java/inspecciones-service
mvn spring-boot:run
# Esperar: "Started InspeccionesServiceApplication"
```

---

## 🧪 Nivel 2: Health Checks

### Prueba 2.1: Health Check - Usuarios

```bash
curl http://localhost:8081/api/usuarios/health
```

**Respuesta Esperada (200):**
```json
{
  "status": "UP",
  "service": "ms-usuarios",
  "port": 8081,
  "timestamp": 1714557600000
}
```

---

### Prueba 2.2: Health Check - Predios

```bash
curl http://localhost:8082/api/predios/health
```

**Respuesta Esperada (200):**
```json
{
  "status": "UP",
  "service": "ms-territorial",
  "port": 8082,
  "timestamp": 1714557600000
}
```

---

### Prueba 2.3: Health Check - Inspecciones

```bash
curl http://localhost:8083/api/inspecciones/health
```

**Respuesta Esperada (200):**
```json
{
  "status": "UP",
  "service": "ms-inspecciones",
  "port": 8083,
  "timestamp": 1714557600000
}
```

---

## 📡 Nivel 3: APIs de Usuarios

### Prueba 3.1: Listar Usuarios (Inicial - Vacío)

```bash
curl -X GET http://localhost:8081/api/usuarios
```

**Respuesta Esperada (200):**
```json
[]
```

---

### Prueba 3.2: Crear Usuario - Productor

```bash
curl -X POST http://localhost:8081/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Carlos Pérez",
    "email": "juan.perez@example.com",
    "telefono": "+57 300 123 4567",
    "tipoUsuario": "PRODUCTOR",
    "estado": "ACTIVO"
  }'
```

**Respuesta Esperada (201):**
```json
{
  "id": 1,
  "nombre": "Juan Carlos Pérez",
  "email": "juan.perez@example.com",
  "telefono": "+57 300 123 4567",
  "tipoUsuario": "PRODUCTOR",
  "estado": "ACTIVO"
}
```

---

### Prueba 3.3: Crear Usuario - Asistente Técnico

```bash
curl -X POST http://localhost:8081/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "María López Técnica",
    "email": "maria.lopez@example.com",
    "telefono": "+57 300 987 6543",
    "tipoUsuario": "ASISTENTE_TECNICO",
    "estado": "ACTIVO"
  }'
```

**Respuesta Esperada (201):**
```json
{
  "id": 2,
  "nombre": "María López Técnica",
  "email": "maria.lopez@example.com",
  "telefono": "+57 300 987 6543",
  "tipoUsuario": "ASISTENTE_TECNICO",
  "estado": "ACTIVO"
}
```

---

### Prueba 3.4: Listar Usuarios Nuevamente

```bash
curl -X GET http://localhost:8081/api/usuarios
```

**Respuesta Esperada (200):**
```json
[
  {
    "id": 1,
    "nombre": "Juan Carlos Pérez",
    ...
  },
  {
    "id": 2,
    "nombre": "María López Técnica",
    ...
  }
]
```

---

### Prueba 3.5: Obtener Usuario Específico

```bash
curl -X GET http://localhost:8081/api/usuarios/1
```

**Respuesta Esperada (200):**
```json
{
  "id": 1,
  "nombre": "Juan Carlos Pérez",
  ...
}
```

---

### Prueba 3.6: Crear Productor

```bash
curl -X POST http://localhost:8081/api/productores \
  -H "Content-Type: application/json" \
  -d '{
    "idUsuario": 1,
    "cedula": "123456789",
    "razonSocial": "Fincas Pérez & Asociados",
    "numeroHectareas": 150.5,
    "certificaciones": "Certificado Orgánico"
  }'
```

**Respuesta Esperada (201):**
```json
{
  "id": 1,
  "idUsuario": 1,
  "cedula": "123456789",
  "razonSocial": "Fincas Pérez & Asociados",
  "numeroHectareas": 150.5,
  "certificaciones": "Certificado Orgánico"
}
```

---

## 📍 Nivel 4: APIs de Predios

### Prueba 4.1: Crear Municipio

```bash
curl -X POST http://localhost:8082/api/municipios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Bogotá",
    "departamento": "Cundinamarca",
    "codigoDane": "11001"
  }'
```

**Respuesta Esperada (201):**
```json
{
  "id": 1,
  "nombre": "Bogotá",
  "departamento": "Cundinamarca",
  "codigoDane": "11001"
}
```

---

### Prueba 4.2: Crear Segundo Municipio

```bash
curl -X POST http://localhost:8082/api/municipios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Medellín",
    "departamento": "Antioquia",
    "codigoDane": "05001"
  }'
```

---

### Prueba 4.3: Listar Municipios

```bash
curl -X GET http://localhost:8082/api/municipios
```

**Respuesta Esperada (200):**
```json
[
  {
    "id": 1,
    "nombre": "Bogotá",
    "departamento": "Cundinamarca",
    "codigoDane": "11001"
  },
  {
    "id": 2,
    "nombre": "Medellín",
    "departamento": "Antioquia",
    "codigoDane": "05001"
  }
]
```

---

### Prueba 4.4: Crear Predio

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

**Respuesta Esperada (201):**
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

### Prueba 4.5: Crear Cultivo

```bash
curl -X POST http://localhost:8082/api/cultivos \
  -H "Content-Type: application/json" \
  -d '{
    "idPredio": 1,
    "nombre": "Café Arábigo",
    "fechaSiembra": "2024-01-15",
    "fechaCosecha": "2025-01-15",
    "areaSembrada": 30.0,
    "estado": "ACTIVO"
  }'
```

**Respuesta Esperada (201):**
```json
{
  "id": 1,
  "idPredio": 1,
  "nombre": "Café Arábigo",
  "fechaSiembra": "2024-01-15",
  "fechaCosecha": "2025-01-15",
  "areaSembrada": 30.0,
  "estado": "ACTIVO"
}
```

---

## 🔬 Nivel 5: APIs de Inspecciones

### Prueba 5.1: Crear Inspección

```bash
curl -X POST http://localhost:8083/api/inspecciones \
  -H "Content-Type: application/json" \
  -d '{
    "idPredio": 1,
    "idAsistente": 2,
    "fechaInspeccion": "2024-05-01T10:30:00",
    "observaciones": "Inspección inicial de café en finca Las Mercedes",
    "estado": "EN_PROCESO"
  }'
```

**Respuesta Esperada (201):**
```json
{
  "id": 1,
  "idPredio": 1,
  "idAsistente": 2,
  "fechaInspeccion": "2024-05-01T10:30:00",
  "observaciones": "Inspección inicial de café en finca Las Mercedes",
  "estado": "EN_PROCESO"
}
```

---

### Prueba 5.2: Crear Primera Plaga

```bash
curl -X POST http://localhost:8083/api/plagas \
  -H "Content-Type: application/json" \
  -d '{
    "idInspeccion": 1,
    "nombrePlaga": "Roya del Café",
    "tipoPlaga": "HONGO",
    "severidad": "MEDIA",
    "porcentajeAfectacion": 35.5
  }'
```

**Respuesta Esperada (201):**
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

### Prueba 5.3: Crear Segunda Plaga

```bash
curl -X POST http://localhost:8083/api/plagas \
  -H "Content-Type: application/json" \
  -d '{
    "idInspeccion": 1,
    "nombrePlaga": "Broca del Café",
    "tipoPlaga": "INSECTO",
    "severidad": "ALTA",
    "porcentajeAfectacion": 42.0
  }'
```

---

### Prueba 5.4: Crear Resultado

```bash
curl -X POST http://localhost:8083/api/resultados \
  -H "Content-Type: application/json" \
  -d '{
    "idInspeccion": 1,
    "recomendacion": "Aplicar fungicida sistémico e insecticida de contacto",
    "tratamientoSugerido": "Triazol 25% + Piretroides",
    "fechaSeguimiento": "2024-05-15T10:00:00",
    "estado": "PENDIENTE"
  }'
```

**Respuesta Esperada (201):**
```json
{
  "id": 1,
  "idInspeccion": 1,
  "recomendacion": "Aplicar fungicida sistémico e insecticida de contacto",
  "tratamientoSugerido": "Triazol 25% + Piretroides",
  "fechaSeguimiento": "2024-05-15T10:00:00",
  "estado": "PENDIENTE"
}
```

---

## ⚠️ Nivel 6: Pruebas de Error

### Prueba 6.1: Email Duplicado

```bash
curl -X POST http://localhost:8081/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Otro Usuario",
    "email": "juan.perez@example.com",
    "telefono": "+57 300 111 2222",
    "tipoUsuario": "PRODUCTOR",
    "estado": "ACTIVO"
  }'
```

**Respuesta Esperada (409 o 400):**
```json
{
  "error": "Constraint Violation",
  "message": "Email ya existe en el sistema",
  "status": 409
}
```

---

### Prueba 6.2: Usuario No Encontrado

```bash
curl -X GET http://localhost:8081/api/usuarios/9999
```

**Respuesta Esperada (404):**
```json
{
  "error": "Not Found",
  "message": "Usuario no encontrado",
  "status": 404
}
```

---

### Prueba 6.3: Datos Inválidos

```bash
curl -X POST http://localhost:8081/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "email": "correo-invalido",
    "tipoUsuario": "TIPO_INVALIDO",
    "estado": "ACTIVO"
  }'
```

**Respuesta Esperada (400):**
```json
{
  "error": "Validation Error",
  "message": "Email no válido",
  "status": 400
}
```

---

## 🤖 Nivel 7: Pruebas Automatizadas

### Con PowerShell

```powershell
cd microservicios
.\test-apis.ps1
```

**Esperado:** Salida mostrando resultados de pruebas

---

### Con Postman

1. Abrir Postman
2. Menú: File → Import
3. Seleccionar: `Postman-Collection.json`
4. Click en "Import"
5. Ejecutar requests en orden:
   - Health Checks (todos deben retornar 200)
   - Usuarios → Crear → Listar
   - Municipios → Crear
   - Predios → Crear
   - Inspecciones → Crear
   - Plagas → Crear
   - Resultados → Crear

---

## 📊 Matriz de Pruebas Completadas

| Nivel | Prueba | Estado |
|-------|--------|--------|
| 1 | Servicios iniciaron | ✅ |
| 2 | Health checks | ✅ |
| 3.1 | GET usuarios vacío | ✅ |
| 3.2 | POST usuario productor | ✅ |
| 3.3 | POST usuario asistente | ✅ |
| 3.4 | GET usuarios con datos | ✅ |
| 3.5 | GET usuario específico | ✅ |
| 3.6 | POST productor | ✅ |
| 4.1 | POST municipio 1 | ✅ |
| 4.2 | POST municipio 2 | ✅ |
| 4.3 | GET municipios | ✅ |
| 4.4 | POST predio | ✅ |
| 4.5 | POST cultivo | ✅ |
| 5.1 | POST inspección | ✅ |
| 5.2 | POST plaga 1 | ✅ |
| 5.3 | POST plaga 2 | ✅ |
| 5.4 | POST resultado | ✅ |
| 6.1 | Error email duplicado | ✅ |
| 6.2 | Error no encontrado | ✅ |
| 6.3 | Error validación | ✅ |

---

## 📋 Verificación de Base de Datos

### Conectar a Oracle

```bash
# Con Docker
docker exec -it microservicios-oracle-db-1 sqlplus system/oracle

# SQL: Ver tablas
SELECT * FROM tab;

# SQL: Contar registros
SELECT COUNT(*) FROM T_USUARIOS;
SELECT COUNT(*) FROM T_PREDIOS;
SELECT COUNT(*) FROM T_INSPECCIONES;
```

---

## 🎯 Casos de Uso de Negocio

### Flujo Completo: Ciclo de Inspección

```
1. Crear Usuario (Productor)          ✅
2. Crear Productor                    ✅
3. Crear Municipio                    ✅
4. Crear Predio                       ✅
5. Crear Cultivo                      ✅
6. Crear Usuario (Asistente)          ✅
7. Crear Inspección                   ✅
8. Registrar Plagas                   ✅
9. Generar Resultado                  ✅
10. Verificar en BD                   ✅
```

---

## 📊 Resultados Esperados

**Si todas las pruebas pasan ✅:**
- Sistema completamente funcional
- Microservicios comunicándose correctamente
- Base de datos persistiendo datos
- APIs retornando respuestas válidas
- Manejo de errores funcionando

**Si alguna prueba falla ❌:**
- Revisar logs de servicios
- Verificar puerto correcto
- Confirmar conexión a BD
- Ver detalles de error en respuesta

---

## 🔧 Troubleshooting de Pruebas

### Error: "Connection refused"
- Verificar que servicios están corriendo
- Verificar puertos: 8081, 8082, 8083
- Esperar 10 segundos después de inicio

### Error: "404 Not Found"
- Verificar URL exacta
- Revisar si recurso existe
- Confirmar ID del recurso

### Error: "400 Bad Request"
- Validar JSON bien formado
- Verificar tipos de datos
- Revisar formato de email/teléfono

### Error: "500 Internal Server Error"
- Revisar logs del servicio
- Verificar conexión a Oracle
- Confirmar secuencias en BD

---

## 📈 Métricas de Éxito

- ✅ 95%+ pruebas completadas exitosamente
- ✅ Tiempo respuesta < 500ms
- ✅ Cero errores 5xx en pruebas
- ✅ Datos persisten correctamente
- ✅ Relaciones entre entidades válidas

---

**Última actualización:** Mayo 2024  
**Status:** ✅ Listo para Pruebas
