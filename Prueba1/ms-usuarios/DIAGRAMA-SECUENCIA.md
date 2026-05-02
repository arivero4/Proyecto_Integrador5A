# 📊 Diagramas de Secuencia - MS-USUARIOS

## 1️⃣ sd Crear Productor

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as ProductorRestController
    participant Controller as ProductorController
    participant Service as ProductorService
    participant Repository as ProductorRepository
    participant pProductor as :Productor

    Cliente->>REST: 1: crearProductor(doc, nombres, apellidos, tel): Productor
    REST->>Controller: 1.1: crear(doc, nombres, apellidos, tel): Productor
    Controller->>Service: 1.1.1: crearProductor(doc, nombres, apellidos, tel): Productor
    Service->>Repository: 1.1.1.1: buscarPorDocumento(doc): Productor
    Repository-->>Service: 1.1.1.2: null
    Service->>pProductor: 1.1.1.3: <<create>>
    Service->>Repository: 1.1.1.4: guardar(productor): Productor
    Repository-->>Service: 1.1.1.5: productor
    Service-->>Controller: 1.1.2: productor
    Controller-->>REST: 1.2: productor
    REST-->>Cliente: 1.3: HTTP 201 (productor)
```

---

## 2️⃣ sd Buscar Productor por ID

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as ProductorRestController
    participant Controller as ProductorController
    participant Service as ProductorService
    participant Repository as ProductorRepository

    Cliente->>REST: 1: buscarProductor(id): Productor
    REST->>Controller: 1.1: buscarPorId(id): Productor
    Controller->>Service: 1.1.1: buscarPorDocumento(id): Productor
    Service->>Repository: 1.1.1.1: buscarPorDocumento(id): Productor
    Repository-->>Service: 1.1.1.2: productor
    Service-->>Controller: 1.1.2: productor
    Controller-->>REST: 1.2: productor
    REST-->>Cliente: 1.3: HTTP 200 (productor)
```

---

## 3️⃣ sd Listar Todos los Productores

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as ProductorRestController
    participant Controller as ProductorController
    participant Service as ProductorService
    participant Repository as ProductorRepository

    Cliente->>REST: 1: listarProductores(): List<Productor>
    REST->>Controller: 1.1: listarTodos(): List<Productor>
    Controller->>Service: 1.1.1: listarTodos(): List<Productor>
    Service->>Repository: 1.1.1.1: listarTodos(): List<Productor>
    Repository-->>Service: 1.1.1.2: List<Productor>
    Service-->>Controller: 1.1.2: List<Productor>
    Controller-->>REST: 1.2: List<Productor>
    REST-->>Cliente: 1.3: HTTP 200 (List<Productor>)
```

---

## 4️⃣ sd Actualizar Productor

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as ProductorRestController
    participant Controller as ProductorController
    participant Service as ProductorService
    participant Repository as ProductorRepository

    Cliente->>REST: 1: actualizarProductor(id, nombres, apellidos, tel): Productor
    REST->>Controller: 1.1: actualizar(id, nombres, apellidos, tel): Productor
    Controller->>Service: 1.1.1: actualizarProductor(id, nombres, apellidos, tel): Productor
    Service->>Repository: 1.1.1.1: buscarPorDocumento(id): Productor
    Repository-->>Service: 1.1.1.2: productor
    Service->>Service: 1.1.1.3: setNombres(nombres): void
    Service->>Service: 1.1.1.4: setApellidos(apellidos): void
    Service->>Service: 1.1.1.5: setTelefono(tel): void
    Service->>Repository: 1.1.1.6: guardar(productor): Productor
    Repository-->>Service: 1.1.1.7: productor
    Service-->>Controller: 1.1.2: productor
    Controller-->>REST: 1.2: productor
    REST-->>Cliente: 1.3: HTTP 200 (productor)
```

---

## 5️⃣ sd Eliminar Productor

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as ProductorRestController
    participant Controller as ProductorController
    participant Service as ProductorService
    participant Repository as ProductorRepository

    Cliente->>REST: 1: eliminarProductor(id): void
    REST->>Controller: 1.1: eliminar(id): void
    Controller->>Service: 1.1.1: eliminarProductor(id): void
    Service->>Repository: 1.1.1.1: buscarPorDocumento(id): Productor
    Repository-->>Service: 1.1.1.2: productor
    Service->>Repository: 1.1.1.3: eliminar(id): void
    Repository-->>Service: 1.1.1.4: void
    Service-->>Controller: 1.1.2: void
    Controller-->>REST: 1.2: void
    REST-->>Cliente: 1.3: HTTP 200
```

---

## 6️⃣ sd Consultar Todos los Usuarios

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as UsuarioRestController
    participant Controller as UsuarioController
    participant ServProd as ProductorService
    participant ServAsist as AsistenteTecnicoService
    participant RepoProd as ProductorRepository
    participant RepoAsist as AsistenteTecnicoRepository

    Cliente->>REST: 1: listarUsuarios(): List<Usuario>
    REST->>Controller: 1.1: consultarTodos(): List<Usuario>
    Controller->>ServProd: 1.1.1: listarTodos(): List<Productor>
    ServProd->>RepoProd: 1.1.1.1: listarTodos(): List<Productor>
    RepoProd-->>ServProd: 1.1.1.2: List<Productor>
    ServProd-->>Controller: 1.1.2: List<Productor>
    Controller->>ServAsist: 1.1.3: listarTodos(): List<AsistenteTecnico>
    ServAsist->>RepoAsist: 1.1.3.1: listarTodos(): List<AsistenteTecnico>
    RepoAsist-->>ServAsist: 1.1.3.2: List<AsistenteTecnico>
    ServAsist-->>Controller: 1.1.4: List<AsistenteTecnico>
    Controller-->>REST: 1.2: List<Usuario>
    REST-->>Cliente: 1.3: HTTP 200 (List<Usuario>)
```

---

## 7️⃣ sd Crear Productor - Documento Duplicado

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as ProductorRestController
    participant Controller as ProductorController
    participant Service as ProductorService
    participant Repository as ProductorRepository

    Cliente->>REST: 1: crearProductor(doc, nombres, apellidos, tel): Productor
    REST->>Controller: 1.1: crear(doc, nombres, apellidos, tel): Productor
    Controller->>Service: 1.1.1: crearProductor(doc, nombres, apellidos, tel): Productor
    Service->>Repository: 1.1.1.1: buscarPorDocumento(doc): Productor
    Repository-->>Service: 1.1.1.2: productor existente
    Service-->>Service: 1.1.1.3: throw Exception("Ya existe")
    Service-->>Controller: 1.1.2: Exception
    Controller-->>REST: 1.2: Exception
    REST-->>Cliente: 1.3: HTTP 400 (error)
```

---

## 📋 Arquitectura de Capas

```
┌─────────────────────────────────────────────────┐
│  CAPA REST (Spring Boot Controllers)           │
│  - Manejo de HTTP (GET, POST, PUT, DELETE)     │
│  - Validación de entrada                        │
│  - Serialización JSON                           │
│  - Status codes HTTP                            │
└─────────────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────────┐
│  CAPA PRESENTACIÓN (Controllers)                │
│  - Coordinación de servicios                    │
│  - Validaciones básicas                         │
│  - Transformación de datos                      │
└─────────────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────────┐
│  CAPA NEGOCIO (Services)                        │
│  - Lógica de negocio                            │
│  - Validaciones de dominio                      │
│  - Reglas de negocio                            │
│  - Manejo de excepciones                        │
└─────────────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────────┐
│  CAPA PERSISTENCIA (Repositories)               │
│  - Acceso a datos                               │
│  - CRUD básico                                  │
│  - Consultas específicas                        │
└─────────────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────────────┐
│  ALMACENAMIENTO (HashMap en memoria)            │
│  - Datos en memoria (puede ser BD)              │
└─────────────────────────────────────────────────┘
```

---

## 🔄 Flujo de Datos

### Petición (Request):
```
HTTP Request (JSON)
    ↓
REST Controller (@RequestBody deserializa JSON → Objeto)
    ↓
Presentación Controller (valida parámetros)
    ↓
Service (lógica de negocio, valida reglas)
    ↓
Repository (acceso a datos)
    ↓
Almacenamiento (guarda/consulta)
```

### Respuesta (Response):
```
Almacenamiento (retorna datos)
    ↓
Repository (retorna entidad)
    ↓
Service (procesa resultado)
    ↓
Presentación Controller (prepara respuesta)
    ↓
REST Controller (serializa Objeto → JSON, asigna status code)
    ↓
HTTP Response (JSON + Status Code)
```

---

## 📊 Tipos de Respuestas HTTP

| Status Code | Operación | Descripción |
|------------|-----------|-------------|
| **200 OK** | GET, PUT, DELETE | Operación exitosa |
| **201 Created** | POST | Recurso creado exitosamente |
| **400 Bad Request** | Cualquiera | Error en validación o datos inválidos |
| **404 Not Found** | GET, PUT, DELETE | Recurso no encontrado |
| **500 Internal Server Error** | Cualquiera | Error interno del servidor |

---

## 🎯 Componentes del MS-USUARIOS

### REST Controllers:
- `ProductorRestController` - Endpoints de productores
- `AsistenteTecnicoRestController` - Endpoints de asistentes técnicos
- `UsuarioRestController` - Endpoints generales de usuarios

### Controllers (Presentación):
- `ProductorController` - Lógica de coordinación productores
- `AsistenteTecnicoController` - Lógica de coordinación asistentes
- `UsuarioController` - Consultas combinadas

### Services (Negocio):
- `ProductorService` - Reglas de negocio productores
- `AsistenteTecnicoService` - Reglas de negocio asistentes
- `UsuarioService` - Lógica general usuarios

### Repositories (Persistencia):
- `ProductorRepository` - CRUD productores
- `AsistenteTecnicoRepository` - CRUD asistentes técnicos
- `UsuarioRepository` - Consultas generales

### Modelos:
- `Productor` - Entidad productor
- `AsistenteTecnico` - Entidad asistente técnico
- `Usuario` - Clase base abstracta

---

## 🔗 URLs del MS-USUARIOS

Base URL: `http://localhost:8081`

### Endpoints Productores:
- `POST /api/productores` - Crear
- `GET /api/productores` - Listar todos
- `GET /api/productores/{id}` - Buscar por ID
- `PUT /api/productores/{id}` - Actualizar
- `DELETE /api/productores/{id}` - Eliminar

### Endpoints Asistentes Técnicos:
- `POST /api/asistentes-tecnicos` - Crear
- `GET /api/asistentes-tecnicos` - Listar todos
- `GET /api/asistentes-tecnicos/{id}` - Buscar por ID
- `PUT /api/asistentes-tecnicos/{id}` - Actualizar
- `DELETE /api/asistentes-tecnicos/{id}` - Eliminar

### Endpoints Generales:
- `GET /api/usuarios` - Listar todos los usuarios

---

# 📋 DIAGRAMAS DE SECUENCIA - ASISTENTE TÉCNICO

---

## 8️⃣ sd Crear AsistenteTecnico

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as AsistenteTecnicoRestController
    participant Controller as AsistenteTecnicoController
    participant Service as AsistenteTecnicoService
    participant Repository as AsistenteTecnicoRepository
    participant :AsistenteTecnico

    Cliente->>REST: 1: crearAsistente(doc, nombres, apellidos, tel, esp): AsistenteTecnico
    REST->>Controller: 1.1: crear(doc, nombres, apellidos, tel, esp): AsistenteTecnico
    Controller->>Service: 1.1.1: crearAsistenteTecnico(doc, nombres, apellidos, tel, esp): AsistenteTecnico
    Service->>Repository: 1.1.1.1: buscarPorDocumento(doc): AsistenteTecnico
    Repository-->>Service: 1.1.1.2: null
    Service->>:AsistenteTecnico: 1.1.1.3: <<create>>
    Service->>Repository: 1.1.1.4: guardar(asistente): AsistenteTecnico
    Repository-->>Service: 1.1.1.5: asistente
    Service-->>Controller: 1.1.2: asistente
    Controller-->>REST: 1.2: asistente
    REST-->>Cliente: 1.3: HTTP 201 (asistente)
```

---

## 9️⃣ sd Buscar AsistenteTecnico por ID

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as AsistenteTecnicoRestController
    participant Controller as AsistenteTecnicoController
    participant Service as AsistenteTecnicoService
    participant Repository as AsistenteTecnicoRepository

    Cliente->>REST: 1: buscarAsistente(id): AsistenteTecnico
    REST->>Controller: 1.1: buscarPorId(id): AsistenteTecnico
    Controller->>Service: 1.1.1: buscarPorDocumento(id): AsistenteTecnico
    Service->>Repository: 1.1.1.1: buscarPorDocumento(id): AsistenteTecnico
    Repository-->>Service: 1.1.1.2: asistente
    Service-->>Controller: 1.1.2: asistente
    Controller-->>REST: 1.2: asistente
    REST-->>Cliente: 1.3: HTTP 200 (asistente)
```

---

## 🔟 sd Listar Todos los AsistentesTecnicos

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as AsistenteTecnicoRestController
    participant Controller as AsistenteTecnicoController
    participant Service as AsistenteTecnicoService
    participant Repository as AsistenteTecnicoRepository

    Cliente->>REST: 1: listarAsistentes(): List<AsistenteTecnico>
    REST->>Controller: 1.1: listarTodos(): List<AsistenteTecnico>
    Controller->>Service: 1.1.1: listarTodos(): List<AsistenteTecnico>
    Service->>Repository: 1.1.1.1: listarTodos(): List<AsistenteTecnico>
    Repository-->>Service: 1.1.1.2: List<AsistenteTecnico>
    Service-->>Controller: 1.1.2: List<AsistenteTecnico>
    Controller-->>REST: 1.2: List<AsistenteTecnico>
    REST-->>Cliente: 1.3: HTTP 200 (List<AsistenteTecnico>)
```

---

## 11. sd Actualizar AsistenteTecnico

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as AsistenteTecnicoRestController
    participant Controller as AsistenteTecnicoController
    participant Service as AsistenteTecnicoService
    participant Repository as AsistenteTecnicoRepository

    Cliente->>REST: 1: actualizarAsistente(id, nombres, apellidos, tel, esp): AsistenteTecnico
    REST->>Controller: 1.1: actualizar(id, nombres, apellidos, tel, esp): AsistenteTecnico
    Controller->>Service: 1.1.1: actualizarAsistenteTecnico(id, nombres, apellidos, tel, esp): AsistenteTecnico
    Service->>Repository: 1.1.1.1: buscarPorDocumento(id): AsistenteTecnico
    Repository-->>Service: 1.1.1.2: asistente
    Service->>Service: 1.1.1.3: setNombres(nombres): void
    Service->>Service: 1.1.1.4: setApellidos(apellidos): void
    Service->>Service: 1.1.1.5: setTelefono(tel): void
    Service->>Service: 1.1.1.6: setEspecialidad(esp): void
    Service->>Repository: 1.1.1.7: guardar(asistente): AsistenteTecnico
    Repository-->>Service: 1.1.1.8: asistente
    Service-->>Controller: 1.1.2: asistente
    Controller-->>REST: 1.2: asistente
    REST-->>Cliente: 1.3: HTTP 200 (asistente)
```

---

## 12. sd Eliminar AsistenteTecnico

```mermaid
sequenceDiagram
    participant Cliente
    participant REST as AsistenteTecnicoRestController
    participant Controller as AsistenteTecnicoController
    participant Service as AsistenteTecnicoService
    participant Repository as AsistenteTecnicoRepository

    Cliente->>REST: 1: eliminarAsistente(id): void
    REST->>Controller: 1.1: eliminar(id): void
    Controller->>Service: 1.1.1: eliminarAsistenteTecnico(id): void
    Service->>Repository: 1.1.1.1: buscarPorDocumento(id): AsistenteTecnico
    Repository-->>Service: 1.1.1.2: asistente
    Service->>Repository: 1.1.1.3: eliminar(id): void
    Repository-->>Service: 1.1.1.4: void
    Service-->>Controller: 1.1.2: void
    Controller-->>REST: 1.2: void
    REST-->>Cliente: 1.3: HTTP 200
```
- `GET /api/usuarios/healthcheck` - Verificar estado del MS
