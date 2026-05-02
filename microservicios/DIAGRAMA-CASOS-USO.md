# 📊 Diagramas de Casos de Uso y Flujos de Negocio

## 📌 Actores del Sistema

- **Productor**: Propietario de predios agrícolas
- **Asistente Técnico**: Profesional que realiza inspecciones
- **Sistema**: Backend de gestión agrícola
- **Base de Datos**: Almacenamiento de información

---

## 🎯 Casos de Uso Principales

### Caso 1: Registrar Nuevo Productor

```
Actor: Administrador
Precondición: Sistema disponible

1. Administrador abre aplicación
2. Sistema muestra menú principal
3. Administrador selecciona "Crear Usuario"
4. Sistema solicita datos (nombre, email, teléfono)
5. Administrador ingresa datos
6. Sistema valida datos
7. Si validación OK:
   a. Sistema crea usuario en BD
   b. Sistema crea registro de productor
   c. Sistema muestra confirmación
8. Si validación falla:
   a. Sistema muestra errores
   b. Volver a paso 4

Postcondición: Productor registrado en sistema
```

---

### Caso 2: Crear Predio

```
Actor: Productor
Precondición: Productor existe en sistema

1. Productor abre aplicación
2. Sistema autentica productor
3. Productor selecciona "Crear Predio"
4. Sistema solicita:
   - Nombre del predio
   - Municipio
   - Área en hectáreas
   - Ubicación (lat, long)
5. Productor ingresa datos
6. Sistema valida datos
7. Si validación OK:
   a. Sistema crea predio en BD
   b. Sistema muestra confirmación
   c. Permite crear cultivos
8. Si validación falla:
   a. Sistema muestra errores
   b. Volver a paso 4

Postcondición: Predio registrado y disponible
```

---

### Caso 3: Realizar Inspección Fitosanitaria

```
Actor: Asistente Técnico
Precondición: Predio existe en sistema

1. Asistente abre aplicación móvil/web
2. Sistema autentica asistente
3. Asistente selecciona "Nueva Inspección"
4. Sistema muestra lista de predios
5. Asistente selecciona predio
6. Sistema abre formulario de inspección
7. Asistente ingresa:
   - Fecha de inspección
   - Observaciones generales
8. Sistema inicia sesión de inspección
9. Asistente identifica plagas:
   a. Selecciona tipo de plaga
   b. Ingresa nombre específico
   c. Indica severidad (BAJA/MEDIA/ALTA/CRÍTICA)
   d. Indica % de afectación
   e. Toma fotos (opcional)
10. Sistema guarda cada plaga detectada
11. Asistente completa inspección
12. Sistema genera recomendaciones automáticas:
    a. Basadas en tipo de plaga
    b. Basadas en severidad
13. Sistema permite:
    a. Guardar recomendaciones
    b. Agendar seguimiento
    c. Generar reporte PDF
14. Sistema sincroniza con servidor

Postcondición: Inspección registrada con plagas y recomendaciones
```

---

### Caso 4: Aplicar Tratamiento y Seguimiento

```
Actor: Asistente Técnico / Productor
Precondición: Inspección completada con plagas

1. Sistema notifica necesidad de seguimiento
2. Productor/Asistente accede a resultados de inspección
3. Sistema muestra:
   - Plagas detectadas
   - Recomendaciones
   - Fecha de seguimiento programada
4. Asistente o Productor aplica tratamiento
5. En fecha programada:
   a. Sistema envía recordatorio
   b. Asistente realiza nueva inspección
   c. Registra resultados
   d. Compara con inspección anterior
6. Sistema evalúa:
   - Si plagas están controladas
   - Si necesita repetir tratamiento
   - Próximo seguimiento

Postcondición: Seguimiento registrado, tratamiento evaluado
```

---

## 📈 Diagrama de Entidades y Relaciones

```
┌──────────────────┐         ┌──────────────────┐
│    T_USUARIOS    │         │  T_PRODUCTORES   │
├──────────────────┤         ├──────────────────┤
│ ID (PK)          │<────────│ ID (PK)          │
│ NOMBRE           │   1:N   │ ID_USUARIO (FK)  │
│ EMAIL (UNIQUE)   │         │ CEDULA           │
│ TELEFONO         │         │ RAZON_SOCIAL     │
│ TIPO_USUARIO     │         │ NUM_HECTAREAS    │
│ ESTADO           │         │ CERTIFICACIONES  │
└──────────────────┘         └──────────────────┘
```

---

## 🔄 Flujo de Datos - Diagrama de Secuencia

```
Productor    Sistema       BD Oracle
    │          │              │
    │ 1. Crear User ──────────>│
    │          │              │
    │          │ ◄─ Confirmación
    │ 2. Crear Predio ────────>│
    │          │              │
    │          │ ◄─ Confirmación
    │          │              │
    │ 3. Crear Inspección ───>│
    │          │              │
    │          │ ◄─ Confirmación
    │          │              │
    │          │ 4. Detectar plagas
    │          │              │
    │ 5. Generar reporte ───────>│
    │          │              │
    │          │ ◄─ Datos plagas
    │ ◄─────────────────────────│
    │          │              │
```

---

## 🌳 Estructura Jerárquica de Datos

```
SISTEMA AGRÍCOLA
├── Usuario 1
│   ├── Productor A
│   │   ├── Predio 1
│   │   │   ├── Cultivo 1 (Café)
│   │   │   ├── Cultivo 2 (Cacao)
│   │   │   └── Inspecciones
│   │   │       └── Inspección 2024-05-01
│   │   │           ├── Plaga 1 (Roya)
│   │   │           │   └── Resultado 1
│   │   │           └── Plaga 2 (Broca)
│   │   │               └── Resultado 2
│   │   └── Predio 2
│   │       └── Inspecciones (...)
│   │
│   └── Productor B
│       └── Predios (...)
│
├── Usuario 2
│   └── AsistenteTécnico
│       └── Inspecciones realizadas
│
└── Municipios (Datos maestros)
    ├── Bogotá
    ├── Medellín
    └── ...
```

---

## 🔀 Transacciones de Negocio

### Transacción 1: Registro Completo de Productor

```
BEGIN TRANSACTION

  1. INSERT INTO T_USUARIOS (nombre, email, ...)
     → T_USUARIOS_ID = 1
  
  2. INSERT INTO T_PRODUCTORES (id_usuario, cedula, ...)
     → T_PRODUCTORES_ID = 1
  
  3. INSERT INTO T_PREDIOS (id_productor, nombre, ...)
     → T_PREDIOS_ID = 1
  
  4. INSERT INTO T_CULTIVOS (id_predio, nombre, ...)
     → T_CULTIVOS_ID = 1

COMMIT TRANSACTION
```

---

### Transacción 2: Inspección con Plagas

```
BEGIN TRANSACTION

  1. INSERT INTO T_INSPECCIONES 
     (id_predio, id_asistente, fecha_inspeccion, ...)
     → T_INSPECCIONES_ID = 1
  
  2. INSERT INTO T_PLAGAS (id_inspeccion, nombre_plaga, ...)
     → T_PLAGAS_ID = 1
  
  3. INSERT INTO T_PLAGAS (id_inspeccion, nombre_plaga, ...)
     → T_PLAGAS_ID = 2
  
  4. INSERT INTO T_RESULTADOS 
     (id_inspeccion, recomendacion, ...)
     → T_RESULTADOS_ID = 1

COMMIT TRANSACTION
```

---

## 🎬 Estados y Transiciones

### Estados de Inspección

```
                    ┌─────────────┐
                    │  PENDIENTE  │
                    └──────┬──────┘
                           │
                    ┌──────▼──────┐
                    │ EN_PROCESO  │
                    └──────┬──────┘
                           │
                    ┌──────▼──────┐
                    │ COMPLETADA  │
                    └─────────────┘
```

### Estados de Resultado

```
                    ┌──────────────┐
                    │  PENDIENTE   │
                    └──────┬───────┘
                           │
                    ┌──────▼────────┐
                    │ EN_EJECUCION  │
                    └──────┬────────┘
                           │
                    ┌──────▼────────┐
                    │ COMPLETADO    │
                    └───────────────┘
```

---

## 🔍 Validaciones de Negocio

### Al Crear Usuario
1. ✅ Email único en sistema
2. ✅ Nombre no vacío
3. ✅ Teléfono con formato válido
4. ✅ Tipo de usuario válido
5. ✅ Estado válido

### Al Crear Predio
1. ✅ Productor debe existir
2. ✅ Municipio debe existir
3. ✅ Área debe ser positiva
4. ✅ Coordenadas válidas (si se proporcionan)

### Al Crear Inspección
1. ✅ Predio debe existir
2. ✅ Asistente debe existir
3. ✅ Fecha debe ser válida
4. ✅ Estado debe ser válido

### Al Crear Plaga
1. ✅ Inspección debe existir
2. ✅ Tipo de plaga válido
3. ✅ Severidad válida
4. ✅ % de afectación entre 0-100

---

## 📋 Matriz de Permisos

| Acción | Productor | Asistente | Admin |
|--------|-----------|-----------|-------|
| Ver mis predios | ✅ | ❌ | ✅ |
| Crear predio | ✅ | ❌ | ✅ |
| Ver inspecciones | ✅ | ✅ | ✅ |
| Crear inspección | ❌ | ✅ | ✅ |
| Ver otros predios | ❌ | ✅ | ✅ |
| Crear usuario | ❌ | ❌ | ✅ |
| Eliminar datos | ❌ | ❌ | ✅ |

---

## 🔐 Flujo de Seguridad (Futuro)

```
                    ┌─────────────┐
                    │   Cliente   │
                    └──────┬──────┘
                           │ HTTP
                    ┌──────▼──────────┐
                    │   Autenticación │
                    │   (JWT Token)   │
                    └──────┬──────────┘
                           │ Validado
                    ┌──────▼───────────┐
                    │   Autorización   │
                    │   (Roles/RBAC)   │
                    └──────┬───────────┘
                           │ Autorizado
                    ┌──────▼──────────┐
                    │  Lógica Negocio │
                    │  (Servicios)    │
                    └──────┬──────────┘
                           │
                    ┌──────▼──────────┐
                    │   BD Oracle     │
                    └─────────────────┘
```

---

**Última actualización:** Mayo 2024
