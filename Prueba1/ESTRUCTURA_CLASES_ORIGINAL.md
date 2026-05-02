# ESTRUCTURA DE CLASES ORIGINAL DEL PROYECTO

Este documento detalla los **atributos y métodos** de todas las clases existentes en el proyecto, organizadas por carpetas.

---

## 📁 CARPETA `modelo` - Gestión de Usuarios

### **Usuario (Clase Abstracta)**
**Atributos:**
- `String id` - Identificador único del usuario
- `String numeroIdentificacion` - Documento de identidad
- `String rol` - Define el rol (Inspector, Administrador, Técnico, Propietario)
- `String nombre` - Nombre completo del usuario
- `String telefonoContacto` - Número de teléfono
- `String correoElectronico` - Email del usuario

**Métodos:**
- `getId()` / `setId(String)`
- `getNumeroIdentificacion()` / `setNumeroIdentificacion(String)`
- `getRol()` / `setRol(String)`
- `getNombre()` / `setNombre(String)`
- `getTelefonoContacto()` / `setTelefonoContacto(String)`
- `getCorreoElectronico()` / `setCorreoElectronico(String)`

---

### **Productor extends Usuario**
**Atributos adicionales:**
- `String id` - Identificador del productor

**Métodos adicionales:**
- `getId()` / `setId(String)`
- `agregarLugarProduccion(LugarProduccion)` - Asocia un lugar de producción
- `getLugaresProduccion()` - Retorna lugares de producción vinculados

**Relaciones:**
- 1 Productor → * LugarProduccion

---

### **AsistenteTecnico extends Usuario**
**Atributos adicionales:**
- `String id` - Identificador del asistente técnico
- `String numeroTarjetaProfesional` - Tarjeta profesional que lo acredita

**Métodos adicionales:**
- `getId()` / `setId(String)`
- `getNumeroTarjetaProfesional()` / `setNumeroTarjetaProfesional(String)`
- `agregarLugarProduccion(LugarProduccion)` - Asocia lugar que supervisa
- `getLugarProduccion()` - Retorna lugar de producción
- `agregarInspeccionFitosanitaria(InspeccionFitosanitaria)` - Registra inspección
- `getInspeccionFitosanitaria()` - Retorna inspección vinculada

**Relaciones:**
- 1 AsistenteTecnico → * LugarProduccion
- 1 AsistenteTecnico → * InspeccionFitosanitaria

---

## 📁 CARPETA `model` - Gestión Territorial y Cultivos

### **Departamento**
**Atributos:**
- `String id` - Identificador único
- `String codigoDane` - Código oficial DANE
- `String nombre` - Nombre del departamento
- `Collection<Municipio> municipio` - Municipios asociados

**Métodos:**
- `getId()` / `setId(String)`
- `getCodigoDane()` / `setCodigoDane(String)`
- `getNombre()` / `setNombre(String)`
- `agregarMunicipio(Municipio)` - Añade municipio
- `getMunicipios()` - Retorna municipios

**Relaciones:**
- 1 Departamento → * Municipio

---

### **Municipio**
**Atributos:**
- `String id` - Identificador único
- `String codigoDane` - Código DANE
- `String nombre` - Nombre del municipio
- `Collection<Vereda> vereda` - Veredas asociadas
- `Departamento departamento` - Departamento al que pertenece

**Métodos:**
- `getId()` / `setId(String)`
- `getCodigoDane()` / `setCodigoDane(String)`
- `getNombre()` / `setNombre(String)`
- `setDepartamento(Departamento)` / `getDepartamento()`
- `agregarVereda(Vereda)` - Añade vereda
- `getVeredas()` - Retorna veredas

**Relaciones:**
- * Municipio → 1 Departamento
- 1 Municipio → * Vereda

---

### **Vereda**
**Atributos:**
- `String id` - Identificador único
- `String codigoDane` - Código DANE
- `String nombre` - Nombre de la vereda
- `Municipio municipio` - Municipio al que pertenece
- `Collection<Predio> predio` - Predios asociados

**Métodos:**
- `getId()` / `setId(String)`
- `getCodigoDane()` / `setCodigoDane(String)`
- `getNombre()` / `setNombre(String)`
- `setMunicipio(Municipio)` / `getMunicipio()`
- `agregarLugarProduccion(LugarProduccion)` - Añade lugar de producción
- `getLugarProduccion()` - Retorna lugar de producción

**Relaciones:**
- * Vereda → 1 Municipio
- 1 Vereda → * Predio

---

### **Predio**
**Atributos:**
- `String id` - Identificador único
- `String numeroPredial` - Código catastral oficial
- `String direccion` - Ubicación física
- `float area` - Tamaño en hectáreas
- `LugarProduccion lugarProduccion` - Lugar de producción asociado
- `Vereda vereda` - Vereda donde se ubica

**Métodos:**
- `getId()` / `setId(String)`
- `getNumeroPredial()` / `setNumeroPredial(String)`
- `getDireccion()` / `setDireccion(String)`
- `getArea()` / `setArea(float)`
- `setPropietario(int)` / `getPropietario()` - Asocia propietario
- `setLugarProduccion(LugarProduccion)` / `getLugaresProduccion()`
- `setVereda(Vereda)` / `getVereda()`

**Relaciones:**
- * Predio → 1 Vereda
- * Predio → 1 LugarProduccion
- * Predio → 1 Propietario

---

### **LugarProduccion**
**Atributos:**
- `String id` - Identificador único
- `String codigoIca` - Código ICA oficial
- `String nombre` - Nombre del lugar
- `Collection<Predio> predio` - Predios asociados
- `Collection<Lote> lote` - Lotes del lugar

**Métodos:**
- `getId()` / `setId(String)`
- `getCodigoIca()` / `setCodigoIca(String)`
- `getNombre()` / `setNombre(String)`
- `agregarPredio(Predio)` / `getPredio()`
- `agregarLote(Lote)` / `getLote()`
- `setProductor(int)` / `getProductor()`
- `setAsistenteTecnico(int)` / `getAsistenteTecnico()`
- `agregarInspeccionFitosanitaria(int)` / `getInspeccionFitosanitaria()`

**Relaciones:**
- 1 LugarProduccion → * Predio
- 1 LugarProduccion → * Lote
- * LugarProduccion → 1 Productor
- * LugarProduccion → 1 AsistenteTecnico
- 1 LugarProduccion → * InspeccionFitosanitaria

---

### **Lote**
**Atributos:**
- `String id` - Identificador único
- `String descripcion` - Detalles del lote
- `float extension` - Área del lote
- `LugarProduccion lugarProduccion` - Lugar al que pertenece
- `Collection<Cultivo> cultivo` - Cultivos del lote

**Métodos:**
- `getId()` / `setId(String)`
- `getDescripcion()` / `setDescripcion(String)`
- `getExtension()` / `setExtension(float)`
- `setPredio(Predio)` / `getPredio()`
- `agregarCultivo(Cultivo)` / `getCultivo()`
- `agregarResultadoTecnico(int)` / `getResultadoTecnico()`

**Relaciones:**
- * Lote → 1 LugarProduccion
- * Lote → 1 Predio
- 1 Lote → * Cultivo
- 1 Lote → * ResultadoTecnico

---

### **Cultivo**
**Atributos:**
- `String id` - Identificador único
- `String nombreComun` - Nombre común (ej: Café, Maíz)
- `String descripcion` - Características del cultivo
- `String nombreCientifico` - Nombre botánico (ej: Coffea arabica)
- `String nombreVariedad` - Variedad específica (ej: Café Caturra)
- `Lote lote` - Lote donde está sembrado

**Métodos:**
- `getId()` / `setId(String)`
- `getNombreComun()` / `setNombreComun(String)`
- `getDescripcion()` / `setDescripcion(String)`
- `getNombreCientifico()` / `setNombreCientifico(String)`
- `getNombreVariedad()` / `setNombreVariedad(String)`
- `getNombreCultivo()` - Retorna nombre
- `agregarLote(Lote)` - Asocia lote
- `setPlaga(int)` / `getPlaga()` - Asocia plaga
- `agregarResultadoTecnico(int)` / `getResultadoTecnico()`

**Relaciones:**
- * Cultivo → 1 Lote
- 1 Cultivo → * Plaga
- 1 Cultivo → * ResultadoTecnico

---

## 📁 CARPETA `modelos` - Gestión de Inspecciones

### **InspeccionFitosanitaria**
**Atributos:**
- `String id` - Identificador único
- `String codigoIca` - Código ICA del lugar inspeccionado
- `String fechaInspeccion` - Fecha de la inspección
- `Collection<ResultadoTecnico> resultadoTecnico` - Resultados obtenidos

**Métodos:**
- `getId()` / `setId(String)`
- `getCodigoIca()` / `setCodigoIca(String)`
- `setFechaInspeccion(String)` - Registra fecha
- `agregarResultadoTecnico(ResultadoTecnico)` / `getResultadoTecnico()`
- `setAsistenteTecnico(int)` / `getAsistenteTecnico()`
- `setLugarProduccion(LugarProduccion)` / `getLugaresProduccion()`

**Relaciones:**
- * InspeccionFitosanitaria → 1 AsistenteTecnico
- * InspeccionFitosanitaria → 1 LugarProduccion
- 1 InspeccionFitosanitaria → * ResultadoTecnico

---

### **ResultadoTecnico**
**Atributos:**
- `String id` - Identificador único
- `int totalPlantasEvaluadas` - Total de plantas inspeccionadas
- `int plantasAfectadas` - Plantas con afectación
- `String observaciones` - Comentarios técnicos
- `Plaga plaga` - Plaga detectada
- `InspeccionFitosanitaria inspeccionFitosanitaria` - Inspección asociada

**Métodos:**
- `getId()` / `setId(String)`
- `getTotalPlantasEvaluadas()` / `setTotalPlantasEvaluadas(int)`
- `getPlantasAfectadas()` / `setPlantasAfectadas(int)`
- `getObservaciones()` / `setObservaciones(String)`
- `getNivelInsidencia()` - Calcula nivel de incidencia
- `getNivelAlerta()` - Determina nivel de alerta
- `agregarCultivo(Cultivo)` / `getCultivo()`
- `agregarPlaga(Plaga)` / `getPlaga()`
- `setInspeccionFitosanitaria(InspeccionFitosanitaria)` / `getInspeccionFitosanitaria()`

**Relaciones:**
- * ResultadoTecnico → 1 InspeccionFitosanitaria
- * ResultadoTecnico → 1 Cultivo
- * ResultadoTecnico → 1 Plaga

---

### **Plaga**
**Atributos:**
- `String id` - Identificador único
- `String nombreComun` - Nombre común (ej: Gusano cogollero)
- `String nombreCientifico` - Nombre científico (ej: Spodoptera frugiperda)
- `String descripcion` - Detalles de la plaga
- `ResultadoTecnico resultadoTecnico` - Resultado donde fue detectada

**Métodos:**
- `getId()` / `setId(String)`
- `getNombreComun()` / `setNombreComun(String)`
- `getNombreCientifico()` / `setNombreCientifico(String)`
- `getDescriopcion()` / `setDescripcion(String)`
- `setCultivo(Cultivo)` / `getCultivo()`
- `agregarResultadoTecnico(ResultadoTecnico)` / `getResultadoTecnico()`

**Relaciones:**
- * Plaga → 1 Cultivo
- * Plaga → 1 ResultadoTecnico

---

## 🔗 DIAGRAMA DE RELACIONES PRINCIPALES

```
Departamento (1) → (*) Municipio (1) → (*) Vereda (1) → (*) Predio
                                                              ↓
                                                     LugarProduccion
                                                        ↓     ↓
                                           Productor ←  *     * → Lote → Cultivo
                                           AsistenteTecnico ←  *
                                                              ↓
                                                   InspeccionFitosanitaria
                                                              ↓
                                                      ResultadoTecnico
                                                         ↓        ↓
                                                     Cultivo    Plaga
```

---

## 📊 DISTRIBUCIÓN SUGERIDA EN MICROSERVICIOS

### **MS-USUARIOS**
- Usuario (abstracta)
- Productor
- AsistenteTecnico

### **MS-TERRITORIAL**
- Departamento
- Municipio
- Vereda
- Predio
- LugarProduccion
- Lote
- Cultivo

### **MS-INSPECCIONES**
- InspeccionFitosanitaria
- ResultadoTecnico
- Plaga

---

## ⚠️ CONSIDERACIONES IMPORTANTES

1. **Herencia**: Usuario es clase abstracta, Productor y AsistenteTecnico heredan de ella
2. **Colecciones**: Varias clases manejan Collection<> para relaciones 1-a-muchos
3. **Tipos de retorno**: Algunos métodos retornan `int` para relaciones (deben ser objetos)
4. **Códigos oficiales**: Se usan codigoDane (DANE) y codigoIca (ICA)
5. **Métodos de cálculo**: ResultadoTecnico tiene métodos calculados (nivelIncidencia, nivelAlerta)

---

## 🎯 PRÓXIMOS PASOS

1. Actualizar las clases de los microservicios con los atributos correctos
2. Implementar las relaciones entre entidades
3. Definir los DTOs (Data Transfer Objects) para comunicación entre microservicios
4. Implementar los métodos de negocio específicos
5. Agregar validaciones según las reglas del dominio

---

**Documento generado:** 7 de marzo de 2026  
**Versión:** 1.0
