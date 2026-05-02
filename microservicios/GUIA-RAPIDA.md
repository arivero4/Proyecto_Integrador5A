# 🌾 GUÍA RÁPIDA - Frontend Sistema de Gestión Agrícola

## ¿Qué se implementó?

Se creó un **Frontend Web completo** que integra con los 3 microservicios, siguiendo el patrón educativo del PDF de "Integración de APIs en Frontend".

### Carpeta Frontend
```
microservicios/frontend/
├── index.html              ← Interfaz HTML
├── app.js                  ← Lógica JavaScript con Fetch API
├── style.css               ← Estilos modernos
├── package.json            ← Dependencias opcionales
├── open-frontend.ps1       ← Script para iniciar
└── README.md               ← Documentación completa
```

## 🚀 Inicio Rápido (3 pasos)

### Paso 1: Inicia los Microservicios

Abre **3 terminales PowerShell** y ejecuta en cada una:

```powershell
# Terminal 1 - USUARIOS (Puerto 8081)
cd c:\Users\User\Downloads\videosYoutube-main\videosYoutube-main\microservicios\java\usuarios-service
& 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' spring-boot:run

# Terminal 2 - PREDIOS (Puerto 8082)
cd c:\Users\User\Downloads\videosYoutube-main\videosYoutube-main\microservicios\java\predios-service
& 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' spring-boot:run

# Terminal 3 - INSPECCIONES (Puerto 8083)
cd c:\Users\User\Downloads\videosYoutube-main\videosYoutube-main\microservicios\java\inspecciones-service
& 'C:\ProgramData\chocolatey\lib\maven\apache-maven-3.9.15\bin\mvn.cmd' spring-boot:run
```

### Paso 2: Inicia el Frontend

En la carpeta `microservicios/frontend/`:

```powershell
# Opción A: Con Python (recomendado)
python -m http.server 8000

# Opción B: Doble clic en index.html
```

### Paso 3: Accede a la Aplicación

Abre tu navegador en: **http://localhost:8000/frontend**

Credenciales:
- **Email**: test@agro.com
- **Contraseña**: 12345678

---

## 📊 Funcionalidades Implementadas

### ✅ Login Simulado
- Valida email y contraseña
- Genera token simulado
- Almacena token en `localStorage`

### ✅ 3 Secciones de Datos

#### 👥 Usuarios (MS-USUARIOS: 8081)
- Consulta GET `/api/usuarios`
- Muestra: ID, Nombre, Email, Teléfono, Tipo, Estado

#### 📍 Predios (MS-PREDIOS: 8082)
- Consulta GET `/api/predios`
- Muestra: ID, Nombre, Productor, Municipio, Área, Ubicación, Estado

#### 🔍 Inspecciones (MS-INSPECCIONES: 8083)
- Consulta GET `/api/inspecciones`
- Muestra: ID, Predio, Asistente, Fecha, Observaciones, Estado

### ✅ Verificación de Estado
- Chequea salud de los 3 microservicios
- Mostra: UP (verde) o DOWN (rojo)

### ✅ Seguridad Básica
- Token enviado en header `Authorization: Bearer <token>`
- localStorage para persistencia de sesión
- Logout limpia el token

---

## 🎓 Conceptos Educativos Implementados (del PDF)

### 1. **Fetch API**
```javascript
const res = await fetch('/data', {
    method: 'GET',
    headers: {
        'Authorization': `Bearer ${token}`
    }
});
```

### 2. **Async/Await**
```javascript
async function getUsuarios() {
    const response = await fetch(`${API_CONFIG.usuarios}`);
    const data = await response.json();
    // Procesar datos
}
```

### 3. **Manejo de Token**
```javascript
localStorage.setItem('token', authToken);
const token = localStorage.getItem('token');
localStorage.removeItem('token');  // Logout
```

### 4. **Manejo de Errores**
```javascript
try {
    const response = await fetch(...);
    if (!response.ok) {
        throw new Error(`Error ${response.status}`);
    }
} catch (error) {
    console.error('Error:', error);
}
```

### 5. **Manipulación del DOM**
```javascript
document.getElementById('usuariosContainer').innerHTML = 
    data.map(user => `<div>${user.nombre}</div>`).join('');
```

---

## 🔍 Pruebas Sugeridas

### Test 1: Verificar Integración
1. Login con `test@agro.com / 12345678`
2. Haz clic en "Verificar Estado"
3. Deberías ver: UP ✓ para todos los servicios

### Test 2: Consultar Datos
1. Selecciona tab "Usuarios"
2. Haz clic "Cargar Usuarios"
3. Si no hay datos, es normal (la BD está vacía)
4. Repite para Predios e Inspecciones

### Test 3: Cerrar Sesión
1. Haz clic "Cerrar Sesión"
2. Deberías volver a la pantalla de login
3. El token se borra de localStorage

---

## 🐛 Solución de Problemas

| Problema | Solución |
|----------|----------|
| **Error CORS** | Verifica que los microservicios estén corriendo |
| **Error 403** | El token no es válido; haz logout y login nuevamente |
| **Conexión rechazada** | Un microservicio no está corriendo |
| **Página en blanco** | Abre DevTools (F12) → Console para ver errores |

---

## 📝 Estructura del Código

### `index.html`
- Sección de login
- Menú de opciones (3 tabs)
- Contenedores para mostrar datos
- Script que carga app.js

### `app.js`
- `login()` - Autenticación simulada
- `logout()` - Cierra sesión
- `getUsuarios()` - Consulta MS-USUARIOS
- `getPredios()` - Consulta MS-PREDIOS
- `getInspecciones()` - Consulta MS-INSPECCIONES
- `checkAllHealth()` - Verifica estado de servicios
- Funciones auxiliares (showTab, showMessage, etc.)

### `style.css`
- Diseño responsivo
- Tema verde/morado
- Animaciones suaves
- Compatible con mobile/tablet/desktop

---

## 🎯 Próximos Pasos (Mejoras)

Para mejorar el frontend, puedes:

1. **Agregar Formularios de Creación**
   - POST para crear usuarios, predios, inspecciones

2. **Filtrado y Búsqueda**
   - Buscar por nombre, email, municipio, etc.

3. **Edición de Datos**
   - PUT para modificar información existente

4. **Eliminación de Datos**
   - DELETE para remover registros

5. **Gráficas y Reportes**
   - Chart.js para visualizar estadísticas

6. **Autenticación Real**
   - Conectar con endpoint real de login en backend
   - Usar JWT en lugar de tokens simulados

7. **Sistema de Notificaciones**
   - Toast notifications para feedback

---

## 📞 Preguntas de Repaso (del PDF)

1. **¿Qué es Fetch API?**
   - API para hacer solicitudes HTTP desde JavaScript

2. **¿Por qué necesitamos headers?**
   - Para enviar metadatos como autenticación, tipo de contenido, etc.

3. **¿Cómo funciona Authorization: Bearer?**
   - El servidor espera el token en este formato exacto para validar

4. **¿Qué pasa si no envío el token?**
   - El servidor rechaza la solicitud con error 403

5. **¿Es seguro enviar tokens en localStorage?**
   - Para demo sí, pero en producción usar httpOnly cookies

---

**¡Listo! Tu frontend está implementado y listo para usar.** 🎉

Si necesitas ayuda, revisa el archivo `README.md` en la carpeta frontend.
