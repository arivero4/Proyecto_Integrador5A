# Frontend - Sistema de Gestión Agrícola

Frontend moderno que integra con los 3 microservicios del sistema de gestión agrícola.

## Características

- ✅ **Login Simulado**: Autenticación básica con token
- ✅ **Integración Multi-Servicio**: Conecta con los 3 microservicios (8081, 8082, 8083)
- ✅ **Consulta de Usuarios**: Gestión de productores y asistentes técnicos
- ✅ **Consulta de Predios**: Visualización de territorios y cultivos
- ✅ **Consulta de Inspecciones**: Registro de inspecciones fitosanitarias
- ✅ **Verificación de Estado**: Chequea salud de todos los microservicios
- ✅ **Interfaz Responsiva**: Funciona en desktop, tablet y móvil
- ✅ **Almacenamiento Local**: Token guardado en localStorage

## Credenciales de Demo

```
Email: test@agro.com
Contraseña: 12345678
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
