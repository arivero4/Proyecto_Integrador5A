# 🚀 Microservicios - Sistema de Gestión Agrícola

**Documentación técnica de la arquitectura de microservicios**

---

## 📋 Contenido

Este directorio contiene:

1. **Frontend** - Aplicación web cliente
2. **Java** - 3 Microservicios Spring Boot
3. Configuración, scripts y documentación

---

## 🏛️ Estructura de Microservicios

```
┌──────────────────────────────────────────┐
│                Frontend                  │
│           (HTML/CSS/JavaScript)          │
│           http://localhost:8000          │
└──────────────┬──────────────┬────────────┘
               │              │
        ┌──────▼─────┐  ┌─────▼────────┐
        │ MS-Usuarios│  │ MS-Predios   │
        │ :8081      │  │ :8082        │
        └──────┬─────┘  └─────┬────────┘
               │              │
        ┌──────▼────────────────────┐
        │  MS-Inspecciones         │
        │  :8083                   │
        └──────┬───────────────────┘
               │
               ▼
        ┌────────────────┐
        │ Oracle DB 10g  │
        │ :1522          │
        └────────────────┘
```

---

## 📚 Documentación por Componente

| Componente | Descripción | Documentación |
|-----------|-------------|---------------|
| **Frontend** | Cliente web HTML5/CSS3/JS | [Ver README](./frontend/README.md) |
| **MS-Usuarios** | API de gestión de usuarios | [Ver README](./java/usuarios-service/README.md) |
| **MS-Predios** | API de gestión de predios | [Ver README](./java/predios-service/README.md) |
| **MS-Inspecciones** | API de inspecciones fitosanitarias | [Ver README](./java/inspecciones-service/README.md) |

---

## 🚀 Inicio Rápido

### Compilar Todo

```bash
cd microservicios
mvn clean install -DskipTests
```

### Ejecutar Servicios

**Terminal 1 - MS-Usuarios**
```bash
cd java/usuarios-service
java -jar target/usuarios-service.jar
```

**Terminal 2 - MS-Predios**
```bash
cd java/predios-service
java -jar target/predios-service.jar
```

**Terminal 3 - MS-Inspecciones**
```bash
cd java/inspecciones-service
java -jar target/inspecciones-service.jar
```

**Terminal 4 - Frontend**
```bash
cd frontend
python -m http.server 8000
```

---

## 🌐 Acceso a Servicios

| Servicio | Puerto | URL |
|----------|--------|-----|
| Frontend | 8000 | http://localhost:8000 |
| Usuarios API | 8081 | http://localhost:8081/api/usuarios |
| Predios API | 8082 | http://localhost:8082/api/predios |
| Inspecciones API | 8083 | http://localhost:8083/api/inspecciones |

---

## 📊 Swagger UI

Documentación interactiva disponible en:

- **Usuarios**: http://localhost:8081/swagger-ui/index.html
- **Predios**: http://localhost:8082/swagger-ui/index.html
- **Inspecciones**: http://localhost:8083/swagger-ui/index.html

---

## 🔐 Autenticación

### Credenciales de Prueba

```
Usuario: test@agro.com
Contraseña: 12345678
Rol: PRODUCTOR
```

### Otros usuarios disponibles

```
admin@agro.com / 12345678 (ADMIN)
productor@agro.com / 12345678 (PRODUCTOR)
user2@agro.com / 12345678 (ASISTENTE_TECNICO)
```

---

## 📁 Archivos Importantes

| Archivo | Descripción |
|---------|------------|
| `pom.xml` | Maven parent - gestiona versiones |
| `docker-compose.yaml` | Orquestación de contenedores |
| `start-microservices.ps1` | Script para iniciar todo |
| `GUIA-RAPIDA.md` | Guía de inicio rápido |
| `DIAGRAMA-CASOS-USO.md` | Casos de uso del sistema |
| `ORACLE-FLYWAY-CONFIG.md` | Configuración BD |

---

## 📞 Soporte

Consultar documentación específica en carpetas individuales

---

**Versión**: 1.0.0  
**Última actualización**: Mayo 2, 2026
```

## Estructura de Archivos

```
frontend/
├── index.html          # Interfaz HTML de usuario
├── app.js              # Lógica del frontend (fetch, autenticación)
├── style.css           # Estilos CSS
└── README.md           # Esta documentación
```

## Cómo Usar

### 1. Iniciar los Microservicios

Asegúrate que los 3 microservicios estén corriendo en sus puertos:

- **MS-USUARIOS**: http://localhost:8081
- **MS-PREDIOS**: http://localhost:8082
- **MS-INSPECCIONES**: http://localhost:8083

```bash
# Terminal 1 - Usuarios
cd microservicios/java/usuarios-service
mvn spring-boot:run

# Terminal 2 - Predios
cd microservicios/java/predios-service
mvn spring-boot:run

# Terminal 3 - Inspecciones
cd microservicios/java/inspecciones-service
mvn spring-boot:run
```

### 2. Abrir el Frontend

Abre el archivo `index.html` en un navegador:

```bash
# Opción 1: Doble clic en index.html
# Opción 2: Con servidor web (recomendado)

# Si tienes Python instalado:
python -m http.server 8000

# Si tienes Node.js con http-server:
npx http-server -c-1

# Luego accede a: http://localhost:8000/frontend
```

### 3. Usar la Aplicación

1. **Login**: Ingresa las credenciales de demo
2. **Selecciona una opción** del menú (Usuarios, Predios, Inspecciones)
3. **Haz clic en "Cargar [Datos]"** para obtener información
4. **Verifica Estado** para confirmar que los microservicios funcionan

## API Endpoints Utilizados

### MS-USUARIOS (8081)
- `GET /api/usuarios` - Obtener todos los usuarios
- `GET /api/usuarios/health` - Verificar estado

### MS-PREDIOS (8082)
- `GET /api/predios` - Obtener todos los predios
- `GET /api/predios/health` - Verificar estado

### MS-INSPECCIONES (8083)
- `GET /api/inspecciones` - Obtener todas las inspecciones
- `GET /api/inspecciones/health` - Verificar estado

## Conceptos Educativos (del PDF)

### 1. **Fetch API**
```javascript
const res = await fetch('/data', {
    method: 'GET',
    headers: {
        'Authorization': `Bearer ${token}`
    }
});
```
- Realiza solicitudes HTTP desde JavaScript
- Usa `await` para esperar la respuesta
- Los `headers` son metadatos de la solicitud

### 2. **Autenticación con Token**
```javascript
// El token se almacena en localStorage
localStorage.setItem('token', authToken);

// Se envía en cada solicitud
headers: {
    'Authorization': `Bearer ${token}`
}
```
- El token actúa como credencial después del login
- Se envía en el header `Authorization`
- El servidor valida el token

### 3. **Manejo de Respuestas**
```javascript
if (res.ok) {
    const data = await res.json();
    // Procesar datos
} else if (res.status === 403) {
    // No autorizado (token inválido)
}
```

### 4. **Async/Await**
```javascript
async function getData() {
    const response = await fetch('/data');
    const data = await response.json();
    return data;
}
```
- `async` marca una función como asincrónica
- `await` pausa la ejecución hasta obtener resultado
- Mejora la legibilidad respecto a Promises

## Solución de Problemas

### Error: "CORS policy"
**Problema**: El navegador bloquea solicitudes de origen cruzado

**Solución**: Verifica que los microservicios tengan CORS habilitado:
```yaml
# En application.yml de cada microservicio:
server:
  servlet:
    context-path: /api
```

### Error 403: "No autorizado"
**Problema**: El token no es válido o no se envía

**Solución**: 
- Verifica que hayas hecho login
- Abre DevTools → Network → verifica el header `Authorization`
- Recarga la página

### Error: "Conexión rechazada"
**Problema**: El microservicio no está corriendo

**Solución**: Inicia el microservicio correspondiente con `mvn spring-boot:run`

## Características Avanzadas

### Login Real (Opcional)
Para conectar con autenticación real, reemplaza en `app.js`:
```javascript
const loginResponse = await fetch('http://localhost:8080/api/auth/login', {
    method: 'POST',
    body: JSON.stringify({ email, password })
});
```

### Crear Nuevos Datos
Agrega funciones POST en `app.js`:
```javascript
async function createUsuario(usuario) {
    const response = await fetch(`${API_CONFIG.usuarios}`, {
        method: 'POST',
        headers: {
            'Authorization': authToken,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    });
    return response.json();
}
```

### Filtrado y Búsqueda
Agrega inputs de búsqueda en `index.html` y filtra resultados en `app.js`

## Licencia
Proyecto educativo - UDI 2026
