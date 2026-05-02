# 🗄️ Configuración Oracle 10g + Flyway

## ✅ Cambios Realizados

### 1. **Application.yml Actualizado**
Los 3 servicios están configurados para usar Oracle 10g:

```yaml
datasource:
  url: jdbc:oracle:thin:@localhost:1521:XE
  username: system
  password: oracle
  driver-class-name: oracle.jdbc.driver.OracleDriver
```

### 2. **Flyway Habilitado**
Cada servicio ejecutará automáticamente scripts SQL en orden:

```
usuarios-service/src/main/resources/db/migration/V1__init_usuarios.sql
predios-service/src/main/resources/db/migration/V1__init_predios.sql
inspecciones-service/src/main/resources/db/migration/V1__init_inspecciones.sql
```

### 3. **Scripts SQL Creados**

#### 📋 V1__init_usuarios.sql
- Tabla: `USUARIOS`
- Secuencia: `seq_usuario_id`
- Datos de ejemplo: 3 usuarios (productores y asistentes)
- Índices en: email, tipo_usuario
- Trigger para actualizar fecha_actualizacion

#### 📋 V1__init_predios.sql
- Tabla: `MUNICIPIOS`
- Tabla: `PREDIOS`
- Tabla: `CULTIVOS`
- Secuencias: seq_municipio_id, seq_predio_id, seq_cultivo_id
- Datos: 3 municipios, 2 predios, referencias a usuarios
- Constraints de validación

#### 📋 V1__init_inspecciones.sql
- Tabla: `INSPECCIONES_FITOSANITARIAS`
- Tabla: `PLAGAS`
- Tabla: `RESULTADOS`
- Secuencias: seq_inspeccion_id, seq_plaga_id, seq_resultado_id
- Datos: 1 inspección, 2 plagas, 1 resultado
- Enums para tipos (INSECTO, HONGO, BACTERIA, VIRUS, MALEZA)

---

## 🚀 Pasos para Ejecutar

### Prerequisito 1: Oracle 10g instalado y corriendo

Verifica que Oracle está activo:
```bash
# En Windows
sqlplus / as sysdba
SQL> SELECT * FROM v$version;
```

**Credenciales por defecto:**
- Usuario: `system`
- Contraseña: `oracle`
- SID: `XE`
- Puerto: `1521`

Si tienes Oracle pero con credenciales diferentes, actualiza:
```yaml
# En application.yml
datasource:
  username: TU_USUARIO
  password: TU_PASSWORD
```

### Prerequisito 2: Oracle JDBC Driver

Ya está en el `pom.xml` del padre:
```xml
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc8</artifactId>
    <version>21.1.0.0</version>
</dependency>
```

### Paso 1: Compilar el proyecto

```powershell
cd microservicios
& 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' clean install -DskipTests
```

### Paso 2: Iniciar los 3 microservicios

**Terminal 1 - USUARIOS (8081)**
```powershell
cd microservicios\java\usuarios-service
& 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' spring-boot:run
```

**Terminal 2 - PREDIOS (8082)**
```powershell
cd microservicios\java\predios-service
& 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' spring-boot:run
```

**Terminal 3 - INSPECCIONES (8083)**
```powershell
cd microservicios\java\inspecciones-service
& 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' spring-boot:run
```

### Paso 3: Verificar que Flyway ejecutó los scripts

En los logs de cada servicio, deberías ver:
```
[INFO] ... Successfully applied 1 migration to schema "SYSTEM" (FlywayDB 9.22.3 by Boxfuse)
```

### Paso 4: Probar los endpoints

```bash
# Obtener usuarios
curl -X GET http://localhost:8081/api/usuarios

# Obtener predios
curl -X GET http://localhost:8082/api/predios

# Obtener inspecciones
curl -X GET http://localhost:8083/api/inspecciones
```

---

## 📊 Estructura de Datos Creada

### Tabla USUARIOS
```
ID | NOMBRE | EMAIL | TELEFONO | TIPO_USUARIO | ESTADO | FECHA_CREACION
1  | Juan Pérez | juan@agro.com | 3101234567 | PRODUCTOR | ACTIVO | ...
2  | María García | maria@agro.com | 3107654321 | ASISTENTE_TECNICO | ACTIVO | ...
3  | Carlos López | carlos@agro.com | 3102345678 | PRODUCTOR | ACTIVO | ...
```

### Tabla PREDIOS
```
ID | ID_PRODUCTOR | NOMBRE | ID_MUNICIPIO | AREA_HECTAREAS | LATITUD | LONGITUD
1  | 1 | Finca El Descanso | 1 | 25.5 | 7.1289 | -73.1156
2  | 1 | Finca Las Colinas | 2 | 15.8 | 7.0642 | -73.1652
```

### Tabla INSPECCIONES_FITOSANITARIAS
```
ID | ID_PREDIO | ID_ASISTENTE | FECHA_INSPECCION | OBSERVACIONES | ESTADO
1  | 1 | 2 | 2026-05-01 | Inspección regular mensual | ACTIVO
```

---

## 🔄 Workflow de Flyway

1. **Primera ejecución**: Flyway ejecuta V1__init_usuarios.sql, V1__init_predios.sql, V1__init_inspecciones.sql
2. **Tabla de control**: Flyway crea tabla `flyway_schema_history` para registrar versiones ejecutadas
3. **Ejecutar nuevas migraciones**: Crea V2__cambio.sql, V3__otro_cambio.sql en orden

### Ejemplo - Agregar una nueva columna:

```sql
-- V2__add_fecha_nacimiento_usuarios.sql
ALTER TABLE usuarios ADD fecha_nacimiento DATE;
```

Flyway lo ejecutará automáticamente en la siguiente startup.

---

## 🐛 Solución de Problemas

### Error: "Connection refused"
```
Error: Unable to connect to localhost:1521
```
**Solución**: Oracle no está corriendo
```bash
# En Windows, inicia Oracle
net start OracleServiceXE
```

### Error: "Invalid username/password"
```
java.sql.SQLException: ORA-01017: invalid username/password
```
**Solución**: Verifica credenciales en application.yml

### Error: "Table already exists"
```
ORA-00955: name is already used by an existing object
```
**Solución**: Las tablas ya existen (esto es normal en subsecuentes ejecuciones)

### Error: "Driver not found"
```
ClassNotFoundException: oracle.jdbc.driver.OracleDriver
```
**Solución**: JDBC driver ya está en pom.xml, ejecuta `mvn clean install`

---

## 📝 Comandos SQL Útiles

```sql
-- Ver todas las tablas creadas
SELECT table_name FROM user_tables;

-- Ver contenido de USUARIOS
SELECT * FROM usuarios;

-- Ver contenido de PREDIOS
SELECT * FROM predios;

-- Ver contenido de INSPECCIONES
SELECT * FROM inspecciones_fitosanitarias;

-- Ver historial de Flyway
SELECT * FROM flyway_schema_history;

-- Insertar nuevo usuario
INSERT INTO usuarios (id, nombre, email, telefono, tipo_usuario, estado)
VALUES (seq_usuario_id.NEXTVAL, 'Nuevo Usuario', 'nuevo@agro.com', '3105551234', 'PRODUCTOR', 'ACTIVO');
COMMIT;
```

---

## ✅ Verificación Final

Si todo está funcionando correctamente:

1. ✅ Los 3 servicios inician sin errores de conexión
2. ✅ Logs muestran "Successfully applied 1 migration"
3. ✅ GET /api/usuarios devuelve datos de ejemplo
4. ✅ GET /api/predios devuelve datos de ejemplo
5. ✅ GET /api/inspecciones devuelve datos de ejemplo

**¡Listo para usar!** 🎉
