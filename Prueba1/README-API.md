# 🌾 Sistema de Gestión Agrícola - Arquitectura de Microservicios

Sistema de microservicios para gestión agrícola con **Spring Boot** y **REST APIs**.

## 📦 Microservicios

### 1. **MS-USUARIOS** (Puerto 8081)
Gestión de Productores y Asistentes Técnicos

### 2. **MS-TERRITORIAL** (Puerto 8082)
Gestión de estructura territorial y lugares de producción

### 3. **MS-INSPECCIONES** (Puerto 8083)
Gestión de inspecciones fitosanitarias, resultados y plagas

---

## 🚀 Ejecución

### Opción 1: Con Maven
```bash
# Terminal 1 - MS-USUARIOS
cd ms-usuarios
mvn spring-boot:run

# Terminal 2 - MS-TERRITORIAL
cd ms-territorial
mvn spring-boot:run

# Terminal 3 - MS-INSPECCIONES
cd ms-inspecciones
mvn spring-boot:run
```

### Opción 2: Con Java
```bash
# Terminal 1
cd ms-usuarios
java UsuariosApplication.java

# Terminal 2
cd ms-territorial
java TerritorialApplication.java

# Terminal 3
cd ms-inspecciones
java InspeccionesApplication.java
```

---

## 📡 APIs REST Disponibles

### 🔷 MS-USUARIOS (http://localhost:8081)

#### Productores
```bash
# Crear productor
POST http://localhost:8081/api/productores
Content-Type: application/json

{
  "documentoIdentidad": "123456789",
  "nombres": "Juan Carlos",
  "apellidos": "Pérez García",
  "telefono": "555-1234"
}

# Listar productores
GET http://localhost:8081/api/productores

# Buscar productor
GET http://localhost:8081/api/productores/123456789

# Actualizar productor
PUT http://localhost:8081/api/productores/123456789
{
  "nombres": "Juan Carlos",
  "apellidos": "Pérez García",
  "telefono": "555-9999"
}

# Eliminar productor
DELETE http://localhost:8081/api/productores/123456789
```

#### Asistentes Técnicos
```bash
# Crear asistente técnico
POST http://localhost:8081/api/asistentes-tecnicos
{
  "documentoIdentidad": "987654321",
  "nombres": "Ana María",
  "apellidos": "González López",
  "telefono": "555-5678",
  "especialidad": "Cultivos orgánicos"
}

# Listar asistentes técnicos
GET http://localhost:8081/api/asistentes-tecnicos

# Buscar asistente técnico
GET http://localhost:8081/api/asistentes-tecnicos/987654321
```

#### Usuarios (todos)
```bash
# Listar todos los usuarios
GET http://localhost:8081/api/usuarios

# Health check
GET http://localhost:8081/api/usuarios/healthcheck
```

---

### 🔷 MS-TERRITORIAL (http://localhost:8082)

#### Departamentos
```bash
# Crear departamento
POST http://localhost:8082/api/departamentos
{
  "codigoDane": "05",
  "nombre": "Antioquia",
  "region": "Andina"
}

# Listar departamentos
GET http://localhost:8082/api/departamentos

# Buscar departamento
GET http://localhost:8082/api/departamentos/05
```

#### Lugares de Producción
```bash
# Crear lugar de producción
POST http://localhost:8082/api/lugares-produccion
{
  "codigoIca": "ICA001",
  "nombre": "Finca El Sol",
  "areaProductiva": 15.5,
  "tipoExplotacion": "Orgánico",
  "numeroPredialPredio": "PRED001"
}

# Listar lugares de producción
GET http://localhost:8082/api/lugares-produccion

# Listar por predio
GET http://localhost:8082/api/lugares-produccion?numeroPredial=PRED001

# Buscar lugar de producción
GET http://localhost:8082/api/lugares-produccion/ICA001

# Actualizar lugar
PUT http://localhost:8082/api/lugares-produccion/ICA001
{
  "nombre": "Finca El Sol (Actualizado)",
  "areaProductiva": 20.0,
  "tipoExplotacion": "Orgánico certificado"
}

# Eliminar lugar
DELETE http://localhost:8082/api/lugares-produccion/ICA001
```

#### Cultivos
```bash
# Crear cultivo
POST http://localhost:8082/api/cultivos
{
  "codigoIca": "CULT001",
  "tipoPlanta": "Café",
  "variedad": "Arábigo",
  "numeroPlantasSembradas": 1000,
  "areaCultivada": 5.0,
  "codigoIcaLote": "LOTE001"
}

# Listar cultivos
GET http://localhost:8082/api/cultivos

# Listar por lote
GET http://localhost:8082/api/cultivos?codigoIcaLote=LOTE001

# Buscar cultivo
GET http://localhost:8082/api/cultivos/CULT001

# Eliminar cultivo
DELETE http://localhost:8082/api/cultivos/CULT001
```

---

### 🔷 MS-INSPECCIONES (http://localhost:8083)

#### Inspecciones Fitosanitarias
```bash
# Crear inspección (valida que el lugar exista en MS-TERRITORIAL)
POST http://localhost:8083/api/inspecciones
{
  "fechaVisita": "07/03/2026",
  "estadoPlaga": "Controlado",
  "conceptoTecnico": "Aplicar fertilizante nitrogenado",
  "codigoIcaLugarProduccion": "ICA001",
  "idAsistenteTecnico": "987654321"
}

# Listar inspecciones
GET http://localhost:8083/api/inspecciones

# Listar por lugar de producción
GET http://localhost:8083/api/inspecciones?codigoIcaLugar=ICA001

# Buscar inspección
GET http://localhost:8083/api/inspecciones/INSP001

# Actualizar inspección
PUT http://localhost:8083/api/inspecciones/INSP001
{
  "fechaVisita": "08/03/2026",
  "estadoPlaga": "En tratamiento",
  "conceptoTecnico": "Continuar con aplicación de fertilizante"
}

# Eliminar inspección
DELETE http://localhost:8083/api/inspecciones/INSP001
```

#### Resultados Técnicos
```bash
# Crear resultado técnico
POST http://localhost:8083/api/resultados-tecnicos
{
  "observacion": "Planta presenta síntomas de deficiencia nutricional",
  "recomendacion": "Aplicar fertilizante rico en nitrógeno",
  "idInspeccion": "INSP001"
}

# Listar resultados
GET http://localhost:8083/api/resultados-tecnicos

# Listar por inspección
GET http://localhost:8083/api/resultados-tecnicos?idInspeccion=INSP001
```

#### Plagas
```bash
# Crear plaga
POST http://localhost:8083/api/plagas
{
  "nombreComun": "Roya del café",
  "nombreCientifico": "Hemileia vastatrix",
  "tipo": "Hongo",
  "idResultadoTecnico": "RES001"
}

# Listar plagas
GET http://localhost:8083/api/plagas

# Listar por resultado técnico
GET http://localhost:8083/api/plagas?idResultado=RES001
```

---

## 🔄 Comunicación entre Microservicios

### Ejemplo: Crear inspección con validación
El MS-INSPECCIONES valida automáticamente que el lugar de producción existe en MS-TERRITORIAL:

```
POST http://localhost:8083/api/inspecciones
{
  "codigoIcaLugarProduccion": "ICA001",  ← Valida en MS-TERRITORIAL
  ...
}

MS-INSPECCIONES → GET http://localhost:8082/api/lugares-produccion/ICA001
MS-TERRITORIAL → Responde con datos del lugar o 404

Si existe → Crea la inspección
Si no existe → Retorna error 400
```

---

## 🛠️ Herramientas para Probar APIs

### **Postman** (Recomendado)
1. Descargar: https://www.postman.com/downloads/
2. Importar colección de requests
3. Probar endpoints

### **curl** (Terminal)
```bash
# Crear productor con curl
curl -X POST http://localhost:8081/api/productores \
  -H "Content-Type: application/json" \
  -d '{
    "documentoIdentidad": "123456789",
    "nombres": "Juan",
    "apellidos": "Pérez",
    "telefono": "555-1234"
  }'

# Listar productores
curl http://localhost:8081/api/productores
```

### **PowerShell**
```powershell
# Crear productor
Invoke-RestMethod -Uri "http://localhost:8081/api/productores" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"documentoIdentidad":"123456789","nombres":"Juan","apellidos":"Pérez","telefono":"555-1234"}'

# Listar productores
Invoke-RestMethod -Uri "http://localhost:8081/api/productores" -Method Get
```

---

## 📋 Arquitectura Completa

```
┌─────────────────────────────────────────────────┐
│  CLIENTE (Postman/Frontend/Otro Microservicio)  │
└─────────────────────────────────────────────────┘
                      ↓ HTTP Request
         ┌────────────┴────────────┐
         ↓                         ↓
┌─────────────────┐       ┌─────────────────┐
│  MS-USUARIOS    │       │  MS-TERRITORIAL │
│  Puerto: 8081   │←─────→│  Puerto: 8082   │
└─────────────────┘       └─────────────────┘
         ↓ RestTemplate           ↓
         └───────────┬─────────────┘
                     ↓
         ┌─────────────────────┐
         │  MS-INSPECCIONES    │
         │  Puerto: 8083       │
         └─────────────────────┘

Flujo dentro de cada microservicio:
REST Controller → Presentación Controller → Servicio → Repository → Modelo
```

---

## 📊 Estructura de Carpetas

```
ms-usuarios/
├── pom.xml                          # Configuración Maven
├── application.yml                  # Configuración Spring Boot
├── UsuariosApplication.java         # Main de Spring Boot
├── rest/                            # ← NUEVA CAPA API REST
│   ├── ProductorRestController.java
│   ├── AsistenteTecnicoRestController.java
│   └── UsuarioRestController.java
├── presentacionC/                   # Controladores de lógica
├── negocioC/                        # Servicios de negocio
├── persistenciaC/                   # Repositorios
└── modeloC/                         # Entidades (POJOs)
```

---

## ✅ Verificación

### 1. Verificar que los 3 microservicios están corriendo:
```bash
# MS-USUARIOS
curl http://localhost:8081/api/usuarios/healthcheck

# MS-TERRITORIAL
curl http://localhost:8082/api/departamentos

# MS-INSPECCIONES
curl http://localhost:8083/api/inspecciones
```

### 2. Probar flujo completo:
```bash
# 1. Crear lugar de producción en MS-TERRITORIAL
POST http://localhost:8082/api/lugares-produccion
{"codigoIca": "ICA001", ...}

# 2. Crear inspección en MS-INSPECCIONES (valida el lugar)
POST http://localhost:8083/api/inspecciones
{"codigoIcaLugarProduccion": "ICA001", ...}
```

---

## 📝 Notas

- **Puertos**: MS-USUARIOS (8081), MS-TERRITORIAL (8082), MS-INSPECCIONES (8083)
- **CORS**: Habilitado en todos los controllers (`@CrossOrigin(origins = "*")`)
- **JSON**: Respuestas automáticamente serializadas/deserializadas por Spring Boot
- **Validación**: MS-INSPECCIONES valida automáticamente existencia de lugares de producción
- **Comunicación**: Los microservicios se comunican usando `RestTemplate`

---

## 🔧 Dependencias Instaladas

- **Spring Boot Starter Web**: APIs REST + Tomcat embebido
- **Spring Boot Starter Validation**: Validación de datos
- **Spring Boot DevTools**: Hot reload durante desarrollo
- **Lombok**: Reducción de código boilerplate (getters/setters)
- **Jackson**: Serialización/deserialización JSON

---

## 🎯 Próximos Pasos (Opcionales)

1. **Base de datos**: Agregar JPA + H2/PostgreSQL/MySQL
2. **Seguridad**: Spring Security + JWT
3. **Documentación**: Swagger/OpenAPI
4. **Discovery Service**: Eureka para registro de microservicios
5. **API Gateway**: Zuul/Spring Cloud Gateway
6. **Circuit Breaker**: Resilience4j
7. **Contenedores**: Docker + Docker Compose
8. **Trazabilidad**: Sleuth + Zipkin

---

Creado con Spring Boot 3.2.0 + Java 17
