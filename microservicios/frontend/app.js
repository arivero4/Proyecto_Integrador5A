// Configuración de APIs
const API_CONFIG = {
    usuarios: 'http://localhost:8081/api/usuarios',
    predios: 'http://localhost:8082/api/predios',
    inspecciones: 'http://localhost:8083/api/inspecciones'
};

// Usuario simulado para demo
const DEMO_USER = {
    email: 'test@agro.com',
    password: '12345678'
};

// Token simulado (en producción sería JWT real)
let authToken = null;

/**
 * Función de Login (simulado)
 * En producción, esto conectaría a un endpoint real de autenticación
 */
async function login() {
    try {
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        if (!email || !password) {
            showMessage('loginMessage', 'Por favor ingresa email y contraseña', 'error');
            return;
        }

        // Validación simulada
        if (email === DEMO_USER.email && password === DEMO_USER.password) {
            // Generar token simulado
            authToken = generateToken();
            localStorage.setItem('token', authToken);
            localStorage.setItem('user', email);

            showMessage('loginMessage', 'Login exitoso ✓', 'success');
            
            // Mostrar sección principal
            setTimeout(() => {
                document.getElementById('loginSection').classList.add('hidden');
                document.getElementById('mainSection').classList.remove('hidden');
                showTab('usuarios');
            }, 1000);
        } else {
            showMessage('loginMessage', 'Credenciales inválidas ✗', 'error');
        }
    } catch (error) {
        showMessage('loginMessage', `Error en login: ${error.message}`, 'error');
    }
}

/**
 * Función de Logout
 */
function logout() {
    authToken = null;
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    
    document.getElementById('mainSection').classList.add('hidden');
    document.getElementById('loginSection').classList.remove('hidden');
    document.getElementById('email').value = '';
    document.getElementById('password').value = '';
    document.getElementById('loginMessage').textContent = '';
}

/**
 * Genera un token simulado
 */
function generateToken() {
    return 'Bearer_' + btoa(new Date().getTime().toString()).substring(0, 20);
}

/**
 * Obtener datos de Usuarios desde MS-USUARIOS (Puerto 8081)
 */
async function getUsuarios() {
    try {
        const container = document.getElementById('usuariosContainer');
        container.innerHTML = '<p class="loading">Cargando usuarios...</p>';

        const response = await fetch(`${API_CONFIG.usuarios}`, {
            method: 'GET',
            headers: {
                'Authorization': authToken,
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();
        
        if (Array.isArray(data) && data.length > 0) {
            container.innerHTML = '<h3>Usuarios Registrados:</h3>' + 
                data.map(user => `
                    <div class="data-card">
                        <strong>ID:</strong> ${user.id || 'N/A'}<br>
                        <strong>Nombre:</strong> ${user.nombre || 'N/A'}<br>
                        <strong>Email:</strong> ${user.email || 'N/A'}<br>
                        <strong>Teléfono:</strong> ${user.telefono || 'N/A'}<br>
                        <strong>Tipo:</strong> ${user.tipoUsuario || 'N/A'}<br>
                        <strong>Estado:</strong> ${user.estado || 'N/A'}
                    </div>
                `).join('');
        } else {
            container.innerHTML = '<p class="info">No hay usuarios registrados. Comienza creando uno.</p>';
        }
    } catch (error) {
        document.getElementById('usuariosContainer').innerHTML = 
            `<p class="error">Error al cargar usuarios: ${error.message}</p>`;
        console.error('Error en getUsuarios:', error);
    }
}

/**
 * Obtener datos de Predios desde MS-TERRITORIAL (Puerto 8082)
 */
async function getPredios() {
    try {
        const container = document.getElementById('prediosContainer');
        container.innerHTML = '<p class="loading">Cargando predios...</p>';

        const response = await fetch(`${API_CONFIG.predios}`, {
            method: 'GET',
            headers: {
                'Authorization': authToken,
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();
        
        if (Array.isArray(data) && data.length > 0) {
            container.innerHTML = '<h3>Predios Registrados:</h3>' + 
                data.map(predio => `
                    <div class="data-card">
                        <strong>ID:</strong> ${predio.id || 'N/A'}<br>
                        <strong>Nombre:</strong> ${predio.nombre || 'N/A'}<br>
                        <strong>Productor ID:</strong> ${predio.idProductor || 'N/A'}<br>
                        <strong>Municipio:</strong> ${predio.idMunicipio || 'N/A'}<br>
                        <strong>Área (hectáreas):</strong> ${predio.areaHectareas || 'N/A'}<br>
                        <strong>Ubicación:</strong> ${predio.latitud || 'N/A'}, ${predio.longitud || 'N/A'}<br>
                        <strong>Estado:</strong> ${predio.estado || 'N/A'}
                    </div>
                `).join('');
        } else {
            container.innerHTML = '<p class="info">No hay predios registrados.</p>';
        }
    } catch (error) {
        document.getElementById('prediosContainer').innerHTML = 
            `<p class="error">Error al cargar predios: ${error.message}</p>`;
        console.error('Error en getPredios:', error);
    }
}

/**
 * Obtener datos de Inspecciones desde MS-INSPECCIONES (Puerto 8083)
 */
async function getInspecciones() {
    try {
        const container = document.getElementById('inspeccionesContainer');
        container.innerHTML = '<p class="loading">Cargando inspecciones...</p>';

        const response = await fetch(`${API_CONFIG.inspecciones}`, {
            method: 'GET',
            headers: {
                'Authorization': authToken,
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();
        
        if (Array.isArray(data) && data.length > 0) {
            container.innerHTML = '<h3>Inspecciones Fitosanitarias:</h3>' + 
                data.map(insp => `
                    <div class="data-card">
                        <strong>ID:</strong> ${insp.id || 'N/A'}<br>
                        <strong>Predio ID:</strong> ${insp.idPredio || 'N/A'}<br>
                        <strong>Asistente ID:</strong> ${insp.idAsistente || 'N/A'}<br>
                        <strong>Fecha:</strong> ${insp.fechaInspeccion || 'N/A'}<br>
                        <strong>Observaciones:</strong> ${insp.observaciones || 'N/A'}<br>
                        <strong>Estado:</strong> ${insp.estado || 'N/A'}
                    </div>
                `).join('');
        } else {
            container.innerHTML = '<p class="info">No hay inspecciones registradas.</p>';
        }
    } catch (error) {
        document.getElementById('inspeccionesContainer').innerHTML = 
            `<p class="error">Error al cargar inspecciones: ${error.message}</p>`;
        console.error('Error en getInspecciones:', error);
    }
}

/**
 * Verificar estado de todos los microservicios
 */
async function checkAllHealth() {
    try {
        const container = document.getElementById('healthContainer');
        container.innerHTML = '<p class="loading">Verificando estado de microservicios...</p>';

        const services = [
            { name: 'Usuarios (8081)', url: `${API_CONFIG.usuarios}/health` },
            { name: 'Predios (8082)', url: `${API_CONFIG.predios.split('/api')[0]}/api/predios/health` },
            { name: 'Inspecciones (8083)', url: `${API_CONFIG.inspecciones.split('/api')[0]}/api/inspecciones/health` }
        ];

        const results = await Promise.allSettled(
            services.map(service => 
                fetch(service.url, {
                    headers: { 'Authorization': authToken }
                })
                .then(res => ({ 
                    name: service.name, 
                    status: res.ok ? 'UP ✓' : 'DOWN ✗',
                    code: res.status
                }))
                .catch(() => ({ 
                    name: service.name, 
                    status: 'DOWN ✗',
                    code: 'Conexión rechazada'
                }))
            )
        );

        container.innerHTML = '<h3>Estado de Microservicios:</h3>' +
            results.map(result => {
                if (result.status === 'fulfilled') {
                    const { name, status, code } = result.value;
                    const isUp = status.includes('✓');
                    return `
                        <div class="data-card ${isUp ? 'success' : 'error'}">
                            <strong>${name}:</strong> ${status}<br>
                            <small>Código: ${code}</small>
                        </div>
                    `;
                } else {
                    return `<div class="data-card error"><strong>Error:</strong> ${result.reason}</div>`;
                }
            }).join('');
    } catch (error) {
        document.getElementById('healthContainer').innerHTML = 
            `<p class="error">Error al verificar estado: ${error.message}</p>`;
        console.error('Error en checkAllHealth:', error);
    }
}

/**
 * Mostrar/ocultar tabs
 */
function showTab(tabName) {
    // Ocultar todos los tabs
    document.querySelectorAll('.tab').forEach(tab => {
        tab.classList.add('hidden');
    });

    // Mostrar el tab seleccionado
    const tabElement = document.getElementById(tabName + 'Tab');
    if (tabElement) {
        tabElement.classList.remove('hidden');
    }
}

/**
 * Mostrar mensajes en la UI
 */
function showMessage(elementId, message, type = 'info') {
    const element = document.getElementById(elementId);
    if (element) {
        element.textContent = message;
        element.className = `message ${type}`;
    }
}

/**
 * Inicializar - Verificar si hay sesión activa
 */
function initApp() {
    const savedToken = localStorage.getItem('token');
    if (savedToken) {
        authToken = savedToken;
        document.getElementById('loginSection').classList.add('hidden');
        document.getElementById('mainSection').classList.remove('hidden');
        showTab('usuarios');
    }
}

// Ejecutar inicialización al cargar la página
window.addEventListener('load', initApp);

// Permitir login con Enter
document.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        const emailInput = document.getElementById('email');
        const passwordInput = document.getElementById('password');
        if (document.activeElement === emailInput || document.activeElement === passwordInput) {
            login();
        }
    }
});
