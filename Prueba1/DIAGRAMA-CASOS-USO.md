# 📐 DIAGRAMA DE CASOS DE USO - SISTEMA DE GESTIÓN AGROPECUARIA

**Proyecto:** Sistema de Microservicios para Gestión Agropecuaria  
**Arquitectura:** 3 Microservicios independientes  
**Estilo:** UML Académico

---

## 🎭 ACTORES DEL SISTEMA

### Actores Primarios:
1. **Productor** - Agricultor dueño de predios agrícolas
2. **Asistente Técnico** - Profesional que realiza inspecciones fitosanitarias
3. **Administrador del Sistema** - Responsable de gestionar la información territorial

### Actores Secundarios:
4. **Sistema Externo** - Otros microservicios que consumen las APIs REST

---

## 📊 DIAGRAMA DE CASOS DE USO

```
┌─────────────────────────────────────────────────────────────────────────────────────┐
│                    SISTEMA DE GESTIÓN AGROPECUARIA                                  │
│                                                                                     │
│  ┌─────────────────────────────────────────────────────────────────────────────┐   │
│  │                    MS-USUARIOS (Puerto 8081)                                │   │
│  │                                                                             │   │
│  │    Productor                                                                │   │
│  │       │                                                                     │   │
│  │       ├──────────> (UC-01) Registrar Productor                             │   │
│  │       ├──────────> (UC-02) Consultar Productor                             │   │
│  │       ├──────────> (UC-03) Actualizar Datos de Productor                   │   │
│  │       ├──────────> (UC-04) Eliminar Productor                              │   │
│  │       └──────────> (UC-05) Listar Todos los Productores                    │   │
│  │                                                                             │   │
│  │    Asistente Técnico                                                        │   │
│  │       │                                                                     │   │
│  │       ├──────────> (UC-06) Registrar Asistente Técnico                     │   │
│  │       ├──────────> (UC-07) Consultar Asistente Técnico                     │   │
│  │       ├──────────> (UC-08) Actualizar Datos de Asistente                   │   │
│  │       ├──────────> (UC-09) Eliminar Asistente Técnico                      │   │
│  │       └──────────> (UC-10) Listar Todos los Asistentes                     │   │
│  │                                                                             │   │
│  │    Administrador                                                            │   │
│  │       │                                                                     │   │
│  │       └──────────> (UC-11) Consultar Todos los Usuarios                    │   │
│  │                                                                             │   │
│  └─────────────────────────────────────────────────────────────────────────────┘   │
│                                                                                     │
│  ┌─────────────────────────────────────────────────────────────────────────────┐   │
│  │                    MS-TERRITORIAL (Puerto 8082)                             │   │
│  │                                                                             │   │
│  │    Administrador                                                            │   │
│  │       │                                                                     │   │
│  │       ├──────────> (UC-12) Registrar Departamento                          │   │
│  │       ├──────────> (UC-13) Consultar Departamento                          │   │
│  │       ├──────────> (UC-14) Listar Departamentos                            │   │
│  │       │                                                                     │   │
│  │       ├──────────> (UC-15) Registrar Lugar de Producción                   │   │
│  │       ├──────────> (UC-16) Consultar Lugar de Producción                   │   │
│  │       ├──────────> (UC-17) Actualizar Lugar de Producción                  │   │
│  │       ├──────────> (UC-18) Eliminar Lugar de Producción                    │   │
│  │       ├──────────> (UC-19) Listar Lugares por Predio                       │   │
│  │       └──────────> (UC-20) Listar Todos los Lugares                        │   │
│  │                                                                             │   │
│  │    Productor                                                                │   │
│  │       │                                                                     │   │
│  │       ├──────────> (UC-21) Registrar Cultivo                               │   │
│  │       ├──────────> (UC-22) Consultar Cultivo                               │   │
│  │       ├──────────> (UC-23) Listar Cultivos de un Lugar                     │   │
│  │       └──────────> (UC-24) Eliminar Cultivo                                │   │
│  │                                                                             │   │
│  └─────────────────────────────────────────────────────────────────────────────┘   │
│                                                                                     │
│  ┌─────────────────────────────────────────────────────────────────────────────┐   │
│  │                    MS-INSPECCIONES (Puerto 8083)                            │   │
│  │                                                                             │   │
│  │    Asistente Técnico                                                        │   │
│  │       │                                                                     │   │
│  │       ├──────────> (UC-25) Crear Inspección Fitosanitaria                  │   │
│  │       ├──────────> (UC-26) Consultar Inspección                            │   │
│  │       ├──────────> (UC-27) Actualizar Inspección                           │   │
│  │       ├──────────> (UC-28) Eliminar Inspección                             │   │
│  │       ├──────────> (UC-29) Listar Inspecciones por Lugar                   │   │
│  │       ├──────────> (UC-30) Listar Todas las Inspecciones                   │   │
│  │       │                                                                     │   │
│  │       ├──────────> (UC-31) Registrar Resultado Técnico                     │   │
│  │       ├──────────> (UC-32) Consultar Resultado Técnico                     │   │
│  │       ├──────────> (UC-33) Listar Resultados de Inspección                 │   │
│  │       ├──────────> (UC-34) Eliminar Resultado Técnico                      │   │
│  │       │                                                                     │   │
│  │       ├──────────> (UC-35) Registrar Plaga                                 │   │
│  │       ├──────────> (UC-36) Consultar Plaga                                 │   │
│  │       ├──────────> (UC-37) Listar Plagas de Inspección                     │   │
│  │       └──────────> (UC-38) Eliminar Plaga                                  │   │
│  │                                                                             │   │
│  └─────────────────────────────────────────────────────────────────────────────┘   │
│                                                                                     │
│  ┌─────────────────────────────────────────────────────────────────────────────┐   │
│  │                    CASOS DE USO TRANSVERSALES                               │   │
│  │                                                                             │   │
│  │    Sistema Externo                                                          │   │
│  │       │                                                                     │   │
│  │       ├──────────> (UC-39) Validar Lugar de Producción (Inter-MS)          │   │
│  │       └──────────> (UC-40) Consultar API REST                              │   │
│  │                                                                             │   │
│  └─────────────────────────────────────────────────────────────────────────────┘   │
│                                                                                     │
└─────────────────────────────────────────────────────────────────────────────────────┘
```

---

## 📋 DESCRIPCIÓN DETALLADA DE CASOS DE USO

### 🔷 MS-USUARIOS (11 Casos de Uso)

#### **UC-01: Registrar Productor**
- **Actor:** Productor, Administrador
- **Descripción:** Registrar un nuevo productor en el sistema con documento, nombres, apellidos y teléfono
- **Precondición:** El documento no debe existir en el sistema
- **Postcondición:** Productor creado y almacenado
- **Endpoint:** `POST /api/productores`

#### **UC-02: Consultar Productor**
- **Actor:** Productor, Asistente Técnico, Administrador
- **Descripción:** Buscar productor por documento de identidad
- **Endpoint:** `GET /api/productores/{id}`

#### **UC-03: Actualizar Datos de Productor**
- **Actor:** Productor, Administrador
- **Descripción:** Modificar nombres, apellidos o teléfono de un productor existente
- **Endpoint:** `PUT /api/productores/{id}`

#### **UC-04: Eliminar Productor**
- **Actor:** Administrador
- **Descripción:** Eliminar un productor del sistema
- **Precondición:** El productor debe existir
- **Endpoint:** `DELETE /api/productores/{id}`

#### **UC-05: Listar Todos los Productores**
- **Actor:** Administrador, Asistente Técnico
- **Descripción:** Obtener lista completa de productores registrados
- **Endpoint:** `GET /api/productores`

#### **UC-06: Registrar Asistente Técnico**
- **Actor:** Administrador
- **Descripción:** Registrar un nuevo asistente técnico con especialidad
- **Endpoint:** `POST /api/asistentes-tecnicos`

#### **UC-07: Consultar Asistente Técnico**
- **Actor:** Administrador, Productor
- **Descripción:** Buscar asistente técnico por documento
- **Endpoint:** `GET /api/asistentes-tecnicos/{id}`

#### **UC-08: Actualizar Datos de Asistente**
- **Actor:** Administrador, Asistente Técnico
- **Descripción:** Modificar datos personales o especialidad
- **Endpoint:** `PUT /api/asistentes-tecnicos/{id}`

#### **UC-09: Eliminar Asistente Técnico**
- **Actor:** Administrador
- **Descripción:** Eliminar asistente del sistema
- **Endpoint:** `DELETE /api/asistentes-tecnicos/{id}`

#### **UC-10: Listar Todos los Asistentes**
- **Actor:** Administrador
- **Descripción:** Obtener lista completa de asistentes técnicos
- **Endpoint:** `GET /api/asistentes-tecnicos`

#### **UC-11: Consultar Todos los Usuarios**
- **Actor:** Administrador
- **Descripción:** Obtener lista combinada de productores y asistentes técnicos
- **Endpoint:** `GET /api/usuarios`

---

### 🔷 MS-TERRITORIAL (13 Casos de Uso)

#### **UC-12: Registrar Departamento**
- **Actor:** Administrador
- **Descripción:** Crear un nuevo departamento con código y nombre
- **Endpoint:** `POST /api/departamentos`

#### **UC-13: Consultar Departamento**
- **Actor:** Administrador, Productor
- **Descripción:** Buscar departamento por código
- **Endpoint:** `GET /api/departamentos/{codigo}`

#### **UC-14: Listar Departamentos**
- **Actor:** Todos los actores
- **Descripción:** Obtener lista completa de departamentos
- **Endpoint:** `GET /api/departamentos`

#### **UC-15: Registrar Lugar de Producción**
- **Actor:** Administrador, Productor
- **Descripción:** Crear lugar de producción con código ICA, nombre, área, tipo de explotación y predio
- **Precondición:** Código ICA único
- **Endpoint:** `POST /api/lugares-produccion`

#### **UC-16: Consultar Lugar de Producción**
- **Actor:** Todos los actores
- **Descripción:** Buscar lugar por código ICA
- **Endpoint:** `GET /api/lugares-produccion/{codigoIca}`

#### **UC-17: Actualizar Lugar de Producción**
- **Actor:** Administrador, Productor
- **Descripción:** Modificar nombre, área o tipo de explotación
- **Endpoint:** `PUT /api/lugares-produccion/{codigoIca}`

#### **UC-18: Eliminar Lugar de Producción**
- **Actor:** Administrador
- **Descripción:** Eliminar lugar del sistema
- **Endpoint:** `DELETE /api/lugares-produccion/{codigoIca}`

#### **UC-19: Listar Lugares por Predio**
- **Actor:** Productor, Administrador
- **Descripción:** Consultar todos los lugares asociados a un predio específico
- **Endpoint:** `GET /api/lugares-produccion?numeroPredial={num}`

#### **UC-20: Listar Todos los Lugares**
- **Actor:** Administrador, Asistente Técnico
- **Descripción:** Obtener lista completa de lugares de producción
- **Endpoint:** `GET /api/lugares-produccion`

#### **UC-21: Registrar Cultivo**
- **Actor:** Productor, Administrador
- **Descripción:** Registrar cultivo en un lugar de producción con nombre, variedad y área
- **Endpoint:** `POST /api/cultivos`

#### **UC-22: Consultar Cultivo**
- **Actor:** Todos los actores
- **Descripción:** Buscar cultivo por ID
- **Endpoint:** `GET /api/cultivos/{id}`

#### **UC-23: Listar Cultivos de un Lugar**
- **Actor:** Productor, Asistente Técnico
- **Descripción:** Obtener todos los cultivos de un lugar específico
- **Endpoint:** `GET /api/cultivos?codigoIcaLugar={codigo}`

#### **UC-24: Eliminar Cultivo**
- **Actor:** Administrador, Productor
- **Descripción:** Eliminar cultivo del sistema
- **Endpoint:** `DELETE /api/cultivos/{id}`

---

### 🔷 MS-INSPECCIONES (14 Casos de Uso)

#### **UC-25: Crear Inspección Fitosanitaria**
- **Actor:** Asistente Técnico
- **Descripción:** Registrar inspección con fecha, estado plaga, concepto técnico, lugar de producción y asistente
- **Precondición:** El lugar de producción debe existir en MS-TERRITORIAL
- **Postcondición:** Inspección registrada, validación inter-microservicio exitosa
- **Endpoint:** `POST /api/inspecciones`
- **Nota:** Valida existencia del lugar usando RestTemplate

#### **UC-26: Consultar Inspección**
- **Actor:** Asistente Técnico, Productor, Administrador
- **Descripción:** Buscar inspección por ID
- **Endpoint:** `GET /api/inspecciones/{id}`

#### **UC-27: Actualizar Inspección**
- **Actor:** Asistente Técnico
- **Descripción:** Modificar fecha, estado o concepto técnico
- **Endpoint:** `PUT /api/inspecciones/{id}`

#### **UC-28: Eliminar Inspección**
- **Actor:** Administrador
- **Descripción:** Eliminar inspección del sistema
- **Endpoint:** `DELETE /api/inspecciones/{id}`

#### **UC-29: Listar Inspecciones por Lugar**
- **Actor:** Productor, Asistente Técnico
- **Descripción:** Obtener todas las inspecciones de un lugar específico
- **Endpoint:** `GET /api/inspecciones?codigoIcaLugar={codigo}`

#### **UC-30: Listar Todas las Inspecciones**
- **Actor:** Administrador, Asistente Técnico
- **Descripción:** Obtener lista completa de inspecciones
- **Endpoint:** `GET /api/inspecciones`

#### **UC-31: Registrar Resultado Técnico**
- **Actor:** Asistente Técnico
- **Descripción:** Registrar resultado técnico asociado a una inspección con tipo, valor observado y unidad
- **Precondición:** La inspección debe existir
- **Endpoint:** `POST /api/resultados-tecnicos`

#### **UC-32: Consultar Resultado Técnico**
- **Actor:** Asistente Técnico, Productor
- **Descripción:** Buscar resultado técnico por ID
- **Endpoint:** `GET /api/resultados-tecnicos/{id}`

#### **UC-33: Listar Resultados de Inspección**
- **Actor:** Asistente Técnico, Productor
- **Descripción:** Obtener todos los resultados de una inspección específica
- **Endpoint:** `GET /api/resultados-tecnicos?idInspeccion={id}`

#### **UC-34: Eliminar Resultado Técnico**
- **Actor:** Asistente Técnico, Administrador
- **Descripción:** Eliminar resultado técnico
- **Endpoint:** `DELETE /api/resultados-tecnicos/{id}`

#### **UC-35: Registrar Plaga**
- **Actor:** Asistente Técnico
- **Descripción:** Registrar plaga con nombre común, científico y tipo de organismo
- **Endpoint:** `POST /api/plagas`

#### **UC-36: Consultar Plaga**
- **Actor:** Asistente Técnico, Productor
- **Descripción:** Buscar plaga por ID
- **Endpoint:** `GET /api/plagas/{id}`

#### **UC-37: Listar Plagas de Inspección**
- **Actor:** Asistente Técnico, Productor
- **Descripción:** Obtener todas las plagas detectadas en una inspección
- **Endpoint:** `GET /api/plagas?idInspeccion={id}`

#### **UC-38: Eliminar Plaga**
- **Actor:** Asistente Técnico, Administrador
- **Descripción:** Eliminar registro de plaga
- **Endpoint:** `DELETE /api/plagas/{id}`

---

### 🔷 CASOS DE USO TRANSVERSALES (2 Casos de Uso)

#### **UC-39: Validar Lugar de Producción (Inter-MS)**
- **Actor:** MS-INSPECCIONES (como cliente), MS-TERRITORIAL (como servidor)
- **Descripción:** Validación automática de existencia de lugar de producción antes de crear inspección
- **Tipo:** Comunicación entre microservicios
- **Tecnología:** RestTemplate, HTTP GET
- **Flujo:**
  1. MS-INSPECCIONES recibe petición POST para crear inspección
  2. Extrae codigoIcaLugarProduccion
  3. Llama a MS-TERRITORIAL: `GET http://localhost:8082/api/lugares-produccion/{codigo}`
  4. Si HTTP 200 → procede con creación
  5. Si HTTP 404 → retorna HTTP 400 Bad Request

#### **UC-40: Consultar API REST**
- **Actor:** Sistema Externo, Frontend, Cliente HTTP
- **Descripción:** Acceso genérico a cualquier endpoint REST de los microservicios
- **Tecnología:** HTTP/REST, JSON
- **Endpoints:** 26 endpoints REST totales

---

## 📊 RESUMEN ESTADÍSTICO

### Por Microservicio:
- **MS-USUARIOS:** 11 casos de uso
- **MS-TERRITORIAL:** 13 casos de uso
- **MS-INSPECCIONES:** 14 casos de uso
- **Transversales:** 2 casos de uso
- **TOTAL:** **40 casos de uso**

### Por Actor:
- **Productor:** 12 casos de uso
- **Asistente Técnico:** 19 casos de uso
- **Administrador del Sistema:** 25 casos de uso
- **Sistema Externo:** 2 casos de uso

### Por Tipo de Operación:
- **Crear/Registrar:** 11 casos de uso
- **Consultar/Buscar:** 12 casos de uso
- **Actualizar/Modificar:** 4 casos de uso
- **Eliminar:** 8 casos de uso
- **Listar:** 13 casos de uso
- **Transversales:** 2 casos de uso

---

## 🔗 RELACIONES ENTRE CASOS DE USO

### Dependencias (<<include>>):
- **UC-25 (Crear Inspección)** incluye **UC-39 (Validar Lugar de Producción)**
  - La creación de inspecciones siempre valida el lugar en MS-TERRITORIAL

### Extensiones (<<extend>>):
- **UC-05 (Listar Productores)** extiende **UC-11 (Consultar Todos los Usuarios)**
- **UC-10 (Listar Asistentes)** extiende **UC-11 (Consultar Todos los Usuarios)**

### Generalizaciones:
- **UC-40 (Consultar API REST)** generaliza todos los casos de uso que exponen endpoints REST

---

## 🏗️ ARQUITECTURA - COMUNICACIÓN ENTRE CASOS DE USO

```
┌─────────────────────┐         RestTemplate          ┌─────────────────────┐
│   MS-INSPECCIONES   │ ─────────────────────────────> │   MS-TERRITORIAL    │
│                     │                                │                     │
│  UC-25: Crear       │  HTTP GET                      │  UC-16: Consultar   │
│  Inspección         │  /api/lugares-produccion/{id}  │  Lugar              │
│                     │ <───────────────────────────── │                     │
│                     │  HTTP 200 OK / 404 Not Found   │                     │
└─────────────────────┘                                └─────────────────────┘
```

---

## 📝 NOTAS ADICIONALES

### Tecnologías Utilizadas:
- **Framework:** Spring Boot 3.2.0
- **Arquitectura:** REST API + Microservicios
- **Comunicación:** HTTP/JSON, RestTemplate
- **Puertos:** 8081 (Usuarios), 8082 (Territorial), 8083 (Inspecciones)
- **Base de Datos:** HashMap en memoria (puede ser BD real)

### Patrones de Diseño:
- **MVC (Model-View-Controller)** en cada microservicio
- **Layered Architecture:** Modelo, Persistencia, Negocio, Presentación, REST
- **Repository Pattern** para acceso a datos
- **Service Pattern** para lógica de negocio
- **DTO Pattern** para transferencia de datos entre capas
- **Inter-Service Communication** mediante RestTemplate

### Características del Sistema:
- ✅ 3 microservicios independientes
- ✅ 26 endpoints REST documentados
- ✅ Validación de datos en capa de negocio
- ✅ Comunicación síncrona entre microservicios
- ✅ Manejo de errores HTTP (200, 201, 400, 404, 500)
- ✅ CORS habilitado para frontend
- ✅ API totalmente REST compliant
