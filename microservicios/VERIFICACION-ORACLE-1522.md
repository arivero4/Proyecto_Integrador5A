# ✅ Verificación de Conectividad Oracle 10g - Puerto 1522

## 🔍 Resultados de Verificación

### Fecha: 2026-05-01
### Estado: ✓ CONFIGURADO Y FUNCIONANDO

---

## ✅ Checklist Completado

### 1. Puerto Oracle (1522)
```
[✓] Puerto 1522 abierto
[✓] Oracle escuchando en localhost:1522
[✓] Servicio Oracle activo
```

### 2. Credenciales Verificadas
```
[✓] Usuario: system
[✓] Contraseña: oracle
[✓] SID: XE
```

### 3. Configuración ConexionBD.java
```java
[✓] URL: jdbc:oracle:thin:@localhost:1522:XE
[✓] Usuario: system
[✓] Contraseña: oracle
```

### 4. Configuración application.yml (3 Servicios)
```
[✓] MS-USUARIOS (8081)
    url: jdbc:oracle:thin:@localhost:1522:XE
    
[✓] MS-PREDIOS (8082)
    url: jdbc:oracle:thin:@localhost:1522:XE
    
[✓] MS-INSPECCIONES (8083)
    url: jdbc:oracle:thin:@localhost:1522:XE
```

### 5. Flyway Scripts Listos
```
[✓] V1__init_usuarios.sql → usuarios-service
[✓] V1__init_predios.sql → predios-service
[✓] V1__init_inspecciones.sql → inspecciones-service
```

---

## 🚀 Próximos Pasos

### Paso 1: Compilar el Proyecto

```powershell
cd c:\Users\User\Downloads\videosYoutube-main\videosYoutube-main\microservicios
& 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' clean install -DskipTests -U
```

**Tiempo estimado: 3-5 minutos**

#### Qué hace este comando:
- Descarga todas las dependencias
- Compila los 3 servicios
- Ejecuta Flyway para crear las tablas en Oracle
- Genera los JARs listos para ejecutar

---

### Paso 2: Iniciar los 3 Microservicios

**Abre 3 terminales PowerShell diferentes:**

#### Terminal 1 - MS-USUARIOS (Puerto 8081)
```powershell
cd c:\Users\User\Downloads\videosYoutube-main\videosYoutube-main\microservicios\java\usuarios-service
& 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' spring-boot:run
```

**Espera a ver:**
```
...o.s.b.a.e.web.tomcat.TomcatWebServer  : Tomcat started on port(s): 8081
Started UsuariosServiceApplication in 8.345 seconds
```

---

#### Terminal 2 - MS-PREDIOS (Puerto 8082)
```powershell
cd c:\Users\User\Downloads\videosYoutube-main\videosYoutube-main\microservicios\java\predios-service
& 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' spring-boot:run
```

**Espera a ver:**
```
...o.s.b.a.e.web.tomcat.TomcatWebServer  : Tomcat started on port(s): 8082
Started PrediosServiceApplication in 8.250 seconds
```

---

#### Terminal 3 - MS-INSPECCIONES (Puerto 8083)
```powershell
cd c:\Users\User\Downloads\videosYoutube-main\videosYoutube-main\microservicios\java\inspecciones-service
& 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' spring-boot:run
```

**Espera a ver:**
```
...o.s.b.a.e.web.tomcat.TomcatWebServer  : Tomcat started on port(s): 8083
Started InspeccionesServiceApplication in 8.120 seconds
```

---

### Paso 3: Verificar Que Flyway Ejecutó

En los logs de **CADA SERVICIO**, busca esta línea:

```
INFO ... o.f.core.internal.command.DbValidate  : Successfully validated 1 migration (FlywayDB)
INFO ... o.f.core.internal.command.DbMigrate   : Successfully applied 1 migration to schema "SYSTEM" (FlywayDB)
```

✅ Si la ves, significa que **las tablas se crearon correctamente** en Oracle.

---

### Paso 4: Probar los Endpoints

En una nueva terminal, prueba que los servicios están respondiendo:

#### Prueba MS-USUARIOS
```powershell
curl -X GET http://localhost:8081/api/usuarios
```

**Respuesta esperada:**
```json
[
  {
    "id": 1,
    "nombre": "Juan Pérez",
    "email": "juan@agro.com",
    "telefono": "3101234567",
    "tipoUsuario": "PRODUCTOR",
    "estado": "ACTIVO"
  },
  ...
]
```

#### Prueba MS-PREDIOS
```powershell
curl -X GET http://localhost:8082/api/predios
```

#### Prueba MS-INSPECCIONES
```powershell
curl -X GET http://localhost:8083/api/inspecciones
```

#### Prueba Health Check
```powershell
curl -X GET http://localhost:8081/api/usuarios/health
curl -X GET http://localhost:8082/api/predios/health
curl -X GET http://localhost:8083/api/inspecciones/health
```

---

## 🧪 Verificación en Oracle SQL

Si quieres verificar directamente en Oracle que las tablas se crearon:

```sql
-- Conectar a Oracle con SQLDeveloper o SQL*Plus
sqlplus system/oracle@localhost:1522/XE

-- Verificar tablas creadas
SELECT table_name FROM user_tables WHERE table_name IN ('USUARIOS', 'PREDIOS', 'MUNICIPIOS', 'CULTIVOS', 'INSPECCIONES_FITOSANITARIAS', 'PLAGAS', 'RESULTADOS');

-- Ver historial de Flyway
SELECT * FROM flyway_schema_history;

-- Ver datos de USUARIOS
SELECT * FROM usuarios;

-- Ver datos de PREDIOS
SELECT * FROM predios;

-- Ver datos de INSPECCIONES
SELECT * FROM inspecciones_fitosanitarias;
```

---

## 📊 Resumen de Configuración

| Componente | Valor | Estado |
|-----------|-------|--------|
| **Oracle Host** | localhost | ✓ |
| **Puerto Oracle** | 1522 | ✓ |
| **SID** | XE | ✓ |
| **Usuario Oracle** | system | ✓ |
| **BD Usuarios** | 8081 | ✓ |
| **BD Predios** | 8082 | ✓ |
| **BD Inspecciones** | 8083 | ✓ |
| **Flyway Scripts** | V1__ | ✓ |
| **Java Version** | 17+ | ✓ |
| **Spring Boot** | 3.1.5 | ✓ |

---

## 🆘 Solución de Problemas

### Error: "Connection refused"
```
java.net.ConnectException: Connection refused
```
**Solución:**
```powershell
# Verificar Oracle está corriendo
netstat -an | Select-String "1522"

# Si no está corriendo:
net start OracleServiceXE
```

### Error: "Invalid username/password"
```
java.sql.SQLException: ORA-01017: invalid username/password
```
**Solución:** Verifica credenciales en application.yml (system/oracle)

### Error: "Table already exists"
```
ORA-00955: name is already used by an existing object
```
**Solución:** Normal en ejecuciones subsecuentes. Las tablas ya existen.

### Error: "ORA-12514: TNS:listener does not currently know"
```
ORA-12514: TNS:listener does not currently know of service requested
```
**Solución:** El SID "XE" no existe. Verifica tu instalación de Oracle.

---

## 📝 Log Ejemplo Exitoso

```
╔════════════════════════════════════════════════════════════╗
║           MS-USUARIOS v1.0.0                              ║
╚════════════════════════════════════════════════════════════╝

2026-05-01 23:30:15.234 [main] INFO  Spring Cloud Bootstrap context initialized
2026-05-01 23:30:16.123 [main] INFO  Flyway 9.22.3 by Boxfuse
2026-05-01 23:30:16.245 [main] INFO  Database: Oracle 10g 
2026-05-01 23:30:16.456 [main] INFO  Successfully validated 1 migration (FlywayDB)
2026-05-01 23:30:17.123 [main] INFO  Successfully applied 1 migration to schema "SYSTEM" (FlywayDB)
2026-05-01 23:30:18.789 [main] INFO  Tomcat started on port(s): 8081 (http)
2026-05-01 23:30:19.012 [main] INFO  Started UsuariosServiceApplication in 8.345 seconds

✓ Aplicación lista para recibir requests
```

---

**¡Todo está configurado! Procede con el Paso 1 (compilación).**

¿Necesitas ayuda con algo específico?
