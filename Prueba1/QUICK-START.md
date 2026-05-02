# 🚀 INICIO RÁPIDO - APIs REST con Spring Boot

## ✅ Lo que se implementó

### ✨ **Capa REST completa para los 3 microservicios**

```
ANTES (Consola):                  AHORA (APIs REST):
main/                             UsuariosApplication.java  ← Spring Boot Main
  ├── MainUsuarios.java          rest/                     ← Nueva capa API
  └── MenuUsuarios.java            ├── ProductorRestController.java
                                   ├── AsistenteTecnicoRestController.java
presentacionC/                     └── UsuarioRestController.java
negocioC/                         presentacionC/            ← Sin cambios
persistenciaC/                    negocioC/                 ← Sin cambios
modeloC/                          persistenciaC/            ← Sin cambios
                                  modeloC/                  ← Sin cambios
                                  pom.xml                   ← Maven + Spring Boot
                                  application.yml           ← Configuración
```

### 📦 **Archivos creados:**

#### Configuración (3 archivos):
- ✅ `ms-usuarios/pom.xml` - Maven con Spring Boot 3.2.0
- ✅ `ms-territorial/pom.xml`
- ✅ `ms-inspecciones/pom.xml`

#### Aplicaciones Spring Boot (3 archivos):
- ✅ `ms-usuarios/UsuariosApplication.java` - Main para puerto 8081
- ✅ `ms-territorial/TerritorialApplication.java` - Main para puerto 8082
- ✅ `ms-inspecciones/InspeccionesApplication.java` - Main para puerto 8083

#### REST Controllers (10 archivos):
**MS-USUARIOS:**
- ✅ `rest/ProductorRestController.java` - GET, POST, PUT, DELETE
- ✅ `rest/AsistenteTecnicoRestController.java` - GET, POST, PUT, DELETE
- ✅ `rest/UsuarioRestController.java` - GET general + healthcheck

**MS-TERRITORIAL:**
- ✅ `rest/DepartamentoRestController.java` - GET, POST
- ✅ `rest/LugarProduccionRestController.java` - GET, POST, PUT, DELETE (con filtros)
- ✅ `rest/CultivoRestController.java` - GET, POST, DELETE (con filtros)

**MS-INSPECCIONES:**
- ✅ `rest/InspeccionFitosanitariaRestController.java` - CRUD completo + validación cross-microservice
- ✅ `rest/ResultadoTecnicoRestController.java` - CRUD completo
- ✅ `rest/PlagaRestController.java` - CRUD completo

#### Configuración YAML (3 archivos):
- ✅ `ms-usuarios/application.yml` - Puerto 8081, logging, URLs otros MS
- ✅ `ms-territorial/application.yml` - Puerto 8082, logging, URLs otros MS
- ✅ `ms-inspecciones/application.yml` - Puerto 8083, logging, URLs otros MS

#### Scripts de utilidad (2 archivos):
- ✅ `start-microservices.ps1` - Inicia los 3 MS automáticamente
- ✅ `test-apis.ps1` - Prueba las APIs con ejemplos reales

#### Documentación (3 archivos):
- ✅ `README-API.md` - Guía completa de uso de APIs
- ✅ `Postman-Collection.json` - Colección importable para Postman
- ✅ `QUICK-START.md` - Este archivo

---

## 🎯 PASOS PARA EJECUTAR

### **Opción 1: Script Automático (Recomendado)**

```powershell
# En PowerShell:
.\start-microservices.ps1
```

Esto abrirá 3 ventanas, una para cada microservicio.

---

### **Opción 2: Manual (3 terminales)**

**Terminal 1 - MS-USUARIOS:**
```bash
cd ms-usuarios
java UsuariosApplication.java
```

**Terminal 2 - MS-TERRITORIAL:**
```bash
cd ms-territorial
java TerritorialApplication.java
```

**Terminal 3 - MS-INSPECCIONES:**
```bash
cd ms-inspecciones
java InspeccionesApplication.java
```

---

### **Opción 3: Con Maven**

```bash
# Terminal 1
cd ms-usuarios
mvn spring-boot:run

# Terminal 2
cd ms-territorial
mvn spring-boot:run

# Terminal 3
cd ms-inspecciones
mvn spring-boot:run
```

---

## ✅ Verificar que funcionan

```powershell
# Ejecutar script de prueba
.\test-apis.ps1
```

O manualmente:

```powershell
# MS-USUARIOS
Invoke-RestMethod http://localhost:8081/api/usuarios/healthcheck

# MS-TERRITORIAL
Invoke-RestMethod http://localhost:8082/api/departamentos

# MS-INSPECCIONES
Invoke-RestMethod http://localhost:8083/api/inspecciones
```

---

## 📡 Probar las APIs

### **Opción A: Con el script de prueba**
```powershell
.\test-apis.ps1
```

Este script:
- ✅ Verifica que los 3 MS estén corriendo
- ✅ Crea un productor de ejemplo
- ✅ Crea un departamento
- ✅ Crea un lugar de producción
- ✅ Crea una inspección (validando la comunicación entre MS)

---

### **Opción B: Con Postman**

1. **Importar colección:**
   - Abrir Postman
   - File → Import
   - Seleccionar `Postman-Collection.json`

2. **Usar los requests:**
   - MS-USUARIOS (8081) → 9 endpoints listos
   - MS-TERRITORIAL (8082) → 9 endpoints listos
   - MS-INSPECCIONES (8083) → 8 endpoints listos

---

### **Opción C: Con curl**

```bash
# Crear productor
curl -X POST http://localhost:8081/api/productores \
  -H "Content-Type: application/json" \
  -d '{"documentoIdentidad":"123","nombres":"Juan","apellidos":"Pérez","telefono":"555-1234"}'

# Listar productores
curl http://localhost:8081/api/productores
```

---

### **Opción D: Con PowerShell**

```powershell
# Crear productor
$body = @{
    documentoIdentidad = "123456789"
    nombres = "Juan"
    apellidos = "Pérez"
    telefono = "555-1234"
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8081/api/productores" `
                  -Method Post `
                  -ContentType "application/json" `
                  -Body $body

# Listar productores
Invoke-RestMethod http://localhost:8081/api/productores
```

---

## 🔄 Comunicación entre microservicios

### **Ejemplo implementado:**

Cuando creas una inspección en MS-INSPECCIONES:

```
POST http://localhost:8083/api/inspecciones
{
  "codigoIcaLugarProduccion": "ICA001",  ← Este código
  ...
}

MS-INSPECCIONES hace:
  ↓
  GET http://localhost:8082/api/lugares-produccion/ICA001
  ↓
MS-TERRITORIAL responde:
  • Si existe → MS-INSPECCIONES crea la inspección ✅
  • Si NO existe → MS-INSPECCIONES retorna error 400 ❌
```

---

## 📊 Arquitectura implementada

```
┌──────────────────────────────────────┐
│  CLIENTE (Postman/curl/Frontend)     │
└──────────────────────────────────────┘
           │ HTTP Request
    ┌──────┴──────┬─────────┐
    ↓             ↓         ↓
┌─────────┐  ┌─────────┐  ┌──────────┐
│ MS-USU  │  │ MS-TERR │  │ MS-INSP  │
│:8081    │←→│:8082    │←→│:8083     │
└─────────┘  └─────────┘  └──────────┘
    │            │            │
    ↓            ↓            ↓
REST Controller  REST Controller  REST Controller
    ↓            ↓            ↓
Presentación     Presentación     Presentación
    ↓            ↓            ↓
Negocio          Negocio          Negocio
    ↓            ↓            ↓
Persistencia     Persistencia     Persistencia
    ↓            ↓            ↓
Modelo           Modelo           Modelo
```

---

## 🎯 Endpoints más importantes

### Crear datos de prueba completos:

```powershell
# 1. Crear productor
POST http://localhost:8081/api/productores
{
  "documentoIdentidad": "123456789",
  "nombres": "Juan",
  "apellidos": "Pérez",
  "telefono": "555-1234"
}

# 2. Crear asistente técnico
POST http://localhost:8081/api/asistentes-tecnicos
{
  "documentoIdentidad": "987654321",
  "nombres": "Ana",
  "apellidos": "García",
  "telefono": "555-5678",
  "especialidad": "Fitosanitaria"
}

# 3. Crear lugar de producción
POST http://localhost:8082/api/lugares-produccion
{
  "codigoIca": "ICA001",
  "nombre": "Finca El Sol",
  "areaProductiva": 15.5,
  "tipoExplotacion": "Orgánico",
  "numeroPredialPredio": "PRED001"
}

# 4. Crear inspección (validará automáticamente que ICA001 existe)
POST http://localhost:8083/api/inspecciones
{
  "fechaVisita": "07/03/2026",
  "estadoPlaga": "Controlado",
  "conceptoTecnico": "Aplicar fertilizante",
  "codigoIcaLugarProduccion": "ICA001",
  "idAsistenteTecnico": "987654321"
}

# 5. Verificar
GET http://localhost:8083/api/inspecciones
GET http://localhost:8081/api/productores
GET http://localhost:8082/api/lugares-produccion
```

---

## ✨ Características implementadas

✅ **REST APIs completas** con GET, POST, PUT, DELETE  
✅ **Comunicación entre microservicios** (MS-INSPECCIONES → MS-TERRITORIAL)  
✅ **CORS habilitado** para consumo desde frontend  
✅ **Serialización JSON automática** (Jackson)  
✅ **Manejo de errores** con status codes HTTP apropiados  
✅ **Filtros en queries** (ej: listar por predio, por lote, etc.)  
✅ **Validación cross-microservice** (inspecciones validan lugares)  
✅ **RestTemplate configurado** para llamadas HTTP entre MS  
✅ **Logging configurado** para debugging  
✅ **Puertos separados** para cada microservicio  

---

## 📚 Documentación adicional

- **README-API.md** - Guía completa con todos los endpoints
- **Postman-Collection.json** - Importar en Postman para probar
- **start-microservices.ps1** - Script para iniciar todo automáticamente
- **test-apis.ps1** - Pruebas automatizadas con ejemplos

---

## 🚨 Troubleshooting

### ❌ Error: "Address already in use"
**Solución:** Otro proceso está usando el puerto.
```powershell
# Ver qué está usando el puerto 8081
Get-NetTCPConnection -LocalPort 8081

# Matar proceso
Stop-Process -Id <PID> -Force
```

### ❌ Error: "Java no encontrado"
**Solución:** Instalar Java 17 o superior
```
https://www.oracle.com/java/technologies/downloads/
```

### ❌ Error: "No se encuentra el lugar de producción"
**Solución:** Crear primero el lugar en MS-TERRITORIAL antes de crear la inspección
```powershell
# 1. Crear lugar
POST http://localhost:8082/api/lugares-produccion
{"codigoIca": "ICA001", ...}

# 2. Crear inspección
POST http://localhost:8083/api/inspecciones
{"codigoIcaLugarProduccion": "ICA001", ...}
```

---

## 🎉 ¡Listo!

Tu sistema de microservicios con APIs REST está completamente funcional.

**Siguiente paso:** Ejecuta `.\start-microservices.ps1` y luego `.\test-apis.ps1`
