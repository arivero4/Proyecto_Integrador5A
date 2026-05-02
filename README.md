# 🌾 Sistema de Gestión Agrícola - Documentación Completa

**Versión**: 1.0.0  
**Estado**: ✅ Producción  
**Última actualización**: Mayo 2, 2026

---

## 📋 Tabla de Contenidos

1. [Descripción General](#descripción-general)
2. [Arquitectura del Sistema](#arquitectura-del-sistema)
3. [Estructura de Carpetas](#estructura-de-carpetas)
4. [Requisitos Previos](#requisitos-previos)
5. [Instalación y Configuración](#instalación-y-configuración)
6. [Inicio Rápido](#inicio-rápido)
7. [Documentación por Módulo](#documentación-por-módulo)
8. [Endpoints Disponibles](#endpoints-disponibles)
9. [Solución de Problemas](#solución-de-problemas)
10. [Stack Tecnológico](#stack-tecnológico)

---

## 📊 Descripción General

**Sistema de Gestión Agrícola** es una plataforma empresarial completa basada en **microservicios** para la administración integral de:

- 👥 **Usuarios**: Productores y asistentes técnicos
- 🏞️ **Predios**: Municipios, terrenos agrícolas y cultivos
- 🔍 **Inspecciones**: Inspecciones fitosanitarias y plagas

### Características Principales

✅ **Arquitectura de Microservicios**: 3 servicios independientes y escalables  
✅ **Base de Datos Oracle 10g XE**: Persistencia robusta con Flyway para versionado  
✅ **API REST Completa**: Documentada en Swagger UI  
✅ **Frontend Moderno**: HTML5/CSS3/JavaScript con Fetch API  
✅ **Autenticación JWT**: Seguridad en endpoints  
✅ **CORS Configurado**: Comunicación cross-origin segura  
✅ **CRUD Completo**: Operaciones Create, Read, Update, Delete  
✅ **Validación de Datos**: DTOs con validación en backend  
✅ **Transacciones**: Manejo de transacciones y consistencia  
✅ **Logging**: Auditoría de operaciones

---

## 🏗️ Arquitectura del Sistema

```
┌─────────────────────────────────────────────────────────────────┐
│                       Cliente Web                                │
│                  (HTML5 + CSS3 + JavaScript)                     │
│                      http://localhost:8000                       │
└────────────┬────────────────────────────┬────────────────────────┘
             │                            │
     ┌───────▼────────┐          ┌────────▼──────────┐
     │                │          │                   │
┌────▼─────────────┐  │  ┌───────▼──────────────┐    │
│ MS-Usuarios      │  │  │  MS-Predios          │    │
│ Puerto: 8081     │  │  │  Puerto: 8082        │    │
│ Spring Boot 3.5  │  │  │  Spring Boot 3.5     │    │
└────────┬─────────┘  │  └───────┬──────────────┘    │
         │            │          │                   │
         │            │  ┌───────▼──────────────┐    │
         │            │  │ MS-Inspecciones      │    │
         │            │  │ Puerto: 8083         │    │
         │            │  │ Spring Boot 3.5      │    │
         │            │  └───────┬──────────────┘    │
         │            │          │                   │
         └────────────┼──────────┴───────────────────┘
                      │
                      ▼
            ┌──────────────────────┐
            │  Oracle Database XE  │
            │  Puerto: 1522        │
            │  Flyway Migrations   │
            └──────────────────────┘
```

---

## 📁 Estructura de Carpetas

```
videosYoutube-main/
│
├── README.md                           ← Documentación principal (este archivo)
├── RELEASES.md                         ← Notas de versión y cambios
│
├── microservicios/                     ← Carpeta principal del proyecto
│   ├── README.md                       ← Documentación microservicios
│   ├── pom.xml                         ← Maven parent POM
│   ├── docker-compose.yaml             ← Orquestación Docker
│   │
│   ├── frontend/                       ← Aplicación web
│   │   ├── README.md                   ← Documentación frontend
│   │   ├── index.html                  ← Página principal
│   │   ├── app.js                      ← Lógica JS
│   │   ├── style.css                   ← Estilos CSS
│   │   └── package.json                ← Dependencias
│   │
│   ├── java/                           ← Servicios Java
│   │   ├── README.md                   ← Documentación Java
│   │   │
│   │   ├── usuarios-service/           ← Microservicio Usuarios
│   │   │   ├── README.md
│   │   │   ├── pom.xml
│   │   │   ├── src/main/java/
│   │   │   └── src/main/resources/
│   │   │
│   │   ├── predios-service/            ← Microservicio Predios
│   │   │   ├── README.md
│   │   │   └── [estructura similar]
│   │   │
│   │   └── inspecciones-service/       ← Microservicio Inspecciones
│   │       ├── README.md
│   │       └── [estructura similar]
│   │
│   ├── GUIA-RAPIDA.md                  ← Inicio rápido
│   └── DIAGRAMA-CASOS-USO.md           ← Casos de uso
│
└── [Otros directorios legacy]
```

---

## 🔧 Requisitos Previos

### Software Necesario

| Software | Versión | Descripción |
|----------|---------|------------|
| **Java JDK** | 17 LTS | Compilación y ejecución |
| **Maven** | 3.9.15+ | Build tool |
| **Oracle Database** | 10g XE | Base de datos (puerto 1522) |
| **Python** | 3.8+ | Servidor HTTP frontend |
| **Git** | Última | Control de versiones |

### Puertos Requeridos

| Servicio | Puerto | Descripción |
|----------|--------|------------|
| Frontend | 8000 | Aplicación web |
| MS-Usuarios | 8081 | API Usuarios |
| MS-Predios | 8082 | API Predios |
| MS-Inspecciones | 8083 | API Inspecciones |
| Oracle | 1522 | Base de datos |

### Requisitos del Sistema

- **RAM**: Mínimo 4GB (8GB recomendado)
- **Disco**: 10GB libres
- **SO**: Windows 10+, macOS 10.15+, Linux (Ubuntu 20.04+)

---

## 📦 Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/videosYoutube.git
cd videosYoutube-main
```

### 2. Configurar Java 17

```bash
# Windows
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%

# Linux/macOS
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH
```

### 3. Verificar Oracle XE

```bash
# Windows
sqlplus system/oracle@localhost:1522/XE

# Linux/macOS
sqlplus system/oracle@localhost:1522/XE
```

### 4. Compilar Proyecto

```bash
cd microservicios
mvn clean install -DskipTests
```

### 5. Iniciar Servicios

```bash
# Terminal 1: MS-Usuarios
cd microservicios/java/usuarios-service
java -jar target/usuarios-service.jar

# Terminal 2: MS-Predios
cd microservicios/java/predios-service
java -jar target/predios-service.jar

# Terminal 3: MS-Inspecciones
cd microservicios/java/inspecciones-service
java -jar target/inspecciones-service.jar

# Terminal 4: Frontend
cd microservicios/frontend
python -m http.server 8000
```

---

## 🚀 Inicio Rápido

### Acceso a la Aplicación

| Recurso | URL |
|---------|-----|
| Frontend | http://localhost:8000 |
| Swagger Usuarios | http://localhost:8081/swagger-ui/index.html |
| Swagger Predios | http://localhost:8082/swagger-ui/index.html |
| Swagger Inspecciones | http://localhost:8083/swagger-ui/index.html |

### Credenciales de Prueba

| Usuario | Email | Contraseña | Rol |
|---------|-------|------------|-----|
| Admin | admin@agro.com | 12345678 | ADMIN |
| Productor | test@agro.com | 12345678 | PRODUCTOR |
| Técnico | user2@agro.com | 12345678 | ASISTENTE_TECNICO |

### Primeros Pasos

1. ✅ Acceder a http://localhost:8000
2. ✅ Iniciar sesión con test@agro.com / 12345678
3. ✅ Ver lista de Usuarios, Predios e Inspecciones
4. ✅ Crear un nuevo Usuario
5. ✅ Explorar Swagger UI en cada servicio

---

## 📚 Documentación por Módulo

- **[Microservicios](./microservicios/README.md)** - Arquitectura de servicios
- **[Frontend Web](./microservicios/frontend/README.md)** - Aplicación cliente
- **[MS-Usuarios](./microservicios/java/usuarios-service/README.md)** - API de usuarios
- **[MS-Predios](./microservicios/java/predios-service/README.md)** - API de predios
- **[MS-Inspecciones](./microservicios/java/inspecciones-service/README.md)** - API de inspecciones
- **[Release Notes](./RELEASES.md)** - Historial de versiones

---

## 🔌 Endpoints Disponibles

### MS-Usuarios (8081)

```
GET    /api/usuarios              → Listar usuarios
GET    /api/usuarios/{id}         → Obtener usuario
POST   /api/usuarios              → Crear usuario ✅
PUT    /api/usuarios/{id}         → Actualizar usuario
DELETE /api/usuarios/{id}         → Eliminar usuario
POST   /auth/login                → Iniciar sesión
POST   /auth/register             → Registrarse
```

### MS-Predios (8082)

```
GET    /api/predios               → Listar predios
GET    /api/predios/{id}          → Obtener predio
POST   /api/predios               → Crear predio ✅
PUT    /api/predios/{id}          → Actualizar predio
DELETE /api/predios/{id}          → Eliminar predio
GET    /api/municipios            → Listar municipios
GET    /api/cultivos              → Listar cultivos
```

### MS-Inspecciones (8083)

```
GET    /api/inspecciones          → Listar inspecciones
GET    /api/inspecciones/{id}     → Obtener inspección
POST   /api/inspecciones          → Crear inspección ✅
PUT    /api/inspecciones/{id}     → Actualizar inspección
DELETE /api/inspecciones/{id}     → Eliminar inspección
GET    /api/plagas                → Listar plagas
GET    /api/resultados            → Listar resultados
```

---

## 🐛 Solución de Problemas

### Error: Puerto en uso

```powershell
# Encontrar proceso
Get-NetTCPConnection -LocalPort 8081 | Select ProcessId

# Terminar proceso
Stop-Process -Id <PID> -Force
```

### Error: No hay conexión Oracle

1. Verificar Oracle: `sqlplus system/oracle`
2. Verificar puerto: `netstat -ano | findstr :1522`
3. Revisar `application.yml`

### Error: Token JWT expirado

- Logout y volver a hacer login
- Token se regenera automáticamente

### Error: CORS

- Confirmar `WebConfig.java` en servicios
- Verificar `/api/**` permitAll en `SecurityConfig.java`

---

## 📊 Stack Tecnológico

| Capa | Tecnología | Versión |
|------|-----------|---------|
| Frontend | HTML5, CSS3, JavaScript ES6+ | 2024 |
| Backend | Spring Boot | 3.5.11 |
| ORM | Hibernate + Spring Data JPA | 6.6.42.Final |
| BD | Oracle Database XE | 10g |
| Migraciones | Flyway | 9.22.3 |
| Seguridad | Spring Security + JWT | 3.5.11 |
| Build | Maven | 3.9.15 |
| Java | OpenJDK | 17 LTS |
| API Docs | Springdoc OpenAPI (Swagger) | 2.x |

---

## 📞 Soporte

- **Issues**: Crear en GitHub
- **Email**: soporte@agrosystem.com
- **Wiki**: Consultar documentación
- **Changelog**: Ver [RELEASES.md](./RELEASES.md)

---

**Versión**: 1.0.0  
**Última actualización**: Mayo 2, 2026

