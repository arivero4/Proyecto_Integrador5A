# 🚀 INICIO RÁPIDO - Sistema de Gestión Agrícola

## ✅ Lo que tienes implementado

```
Microservicios Java + Spring Boot + Oracle Database
├── MS-USUARIOS (8081)           → Gestión de productores y asistentes
├── MS-TERRITORIAL (8082)        → Gestión de predios, cultivos
├── MS-INSPECCIONES (8083)       → Inspecciones fitosanitarias y plagas
└── Base de Datos Oracle (1521)  → Persistencia centralizada
```

---

## 🎯 OPCIÓN 1: Iniciar con Docker (Recomendado)

```bash
cd microservicios

# Construir e iniciar todo
docker-compose up --build

# En otra terminal, verificar servicios
docker-compose ps

# Ver logs
docker-compose logs -f

# Detener
docker-compose down
```

### Verificar servicios
```bash
# Health checks
curl http://localhost:8081/api/usuarios/health
curl http://localhost:8082/api/predios/health
curl http://localhost:8083/api/inspecciones/health
```

---

## 🎯 OPCIÓN 2: Iniciar Localmente con Maven

### Terminal 1 - Usuarios Service
```bash
cd microservicios/java/usuarios-service
mvn spring-boot:run
```

### Terminal 2 - Predios Service
```bash
cd microservicios/java/predios-service
mvn spring-boot:run
```

### Terminal 3 - Inspecciones Service
```bash
cd microservicios/java/inspecciones-service
mvn spring-boot:run
```

---

## 🎯 OPCIÓN 3: Script PowerShell (Windows)

```powershell
# Abrir PowerShell como Administrador
cd microservicios

# Ejecutar script
.\start-microservices.ps1

# Se abrirán 3 ventanas automáticamente con cada servicio
```

---

## 📡 Pruebas Rápidas de APIs

### Con Postman
1. Abrir Postman
2. Importar: `Postman-Collection.json`
3. Ejecutar requests

### Con PowerShell
```powershell
# Probar todas las APIs
.\test-apis.ps1
```

### Con cURL

**Crear Usuario:**
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

**Listar Usuarios:**
```bash
curl http://localhost:8081/api/usuarios
```

**Crear Municipio:**
```bash
curl -X POST http://localhost:8082/api/municipios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Bogotá",
    "departamento": "Cundinamarca",
    "codigoDane": "11001"
  }'
```

**Listar Municipios:**
```bash
curl http://localhost:8082/api/municipios
```

---

## 🔌 URLs de Servicios

| Servicio | Puerto | URL Base | Health Check |
|----------|--------|----------|--------------|
| **Usuarios** | 8081 | http://localhost:8081 | /api/usuarios/health |
| **Predios** | 8082 | http://localhost:8082 | /api/predios/health |
| **Inspecciones** | 8083 | http://localhost:8083 | /api/inspecciones/health |
| **Oracle DB** | 1521 | - | - |

---

## 📚 Próximas Pruebas

1. **Crear Usuario**
   ```bash
   POST /api/usuarios
   ```

2. **Crear Productor**
   ```bash
   POST /api/productores
   ```

3. **Crear Municipio**
   ```bash
   POST /api/municipios
   ```

4. **Crear Predio**
   ```bash
   POST /api/predios
   ```

5. **Crear Inspección**
   ```bash
   POST /api/inspecciones
   ```

---

## 🐛 Troubleshooting

### Puerto ya en uso
```bash
# Encontrar qué proceso usa el puerto
netstat -an | findstr :8081

# Liberar puerto (Windows)
netsh int ipv4 set dynamic tcp start=49152 num=16384
```

### Oracle no conecta
```bash
# Verificar estado de Oracle
docker-compose logs oracle-db

# Esperar 40 segundos después de levantarlo
```

### Maven no compila
```bash
# Limpiar y reinstalar
mvn clean install

# Descargar dependencias nuevamente
mvn dependency:resolve
```

---

## 📖 Documentación Completa

- [README.md](./README.md) - Guía general
- [README-API.md](./README-API.md) - Detalle de APIs
- [Postman-Collection.json](./Postman-Collection.json) - Importar en Postman

---

## ✨ ¡Listo!

Tu sistema está completo y listo para usar. Selecciona tu forma preferida de iniciar y comienza a explorar las APIs.

```
🎉 ¡Felicitaciones! 🎉
Tu Sistema de Gestión Agrícola está en funcionamiento
```
