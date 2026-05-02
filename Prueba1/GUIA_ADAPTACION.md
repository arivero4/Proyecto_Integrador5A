# GUÍA DE ADAPTACIÓN - Microservicios a Clases Originales

Este documento te guía para adaptar los microservicios creados a tus clases originales.

---

## 📋 RESUMEN DE CAMBIOS NECESARIOS

### ✅ Lo que ya coincide:
- Estructura de 4 capas (Modelo, Persistencia, Negocio, Presentación)
- Nombres generales de entidades (Usuario, Productor, Cultivo, etc.)
- Patrón de repositorios y servicios

### ⚠️ Lo que necesita adaptarse:
- Atributos específicos de cada clase
- Relaciones entre entidades
- Métodos de negocio especializados
- Usuario debe ser abstracta
- Agregar nuevas entidades (Departamento, Municipio, Vereda, etc.)

---

## 🔄 PLAN DE ADAPTACIÓN POR MICROSERVICIO

### **1. MICROSERVICIO USUARIOS**

#### Cambios en Usuario.java:
```java
// ANTES (versión simple):
private Long id;
private String nombre;
private String apellido;
private String email;
private String password;
private String telefono;
private String tipo;
private boolean activo;

// DESPUÉS (versión original):
public abstract class Usuario {  // ⚠️ Debe ser abstracta
    private String id;  // ⚠️ Cambiar de Long a String
    private String numeroIdentificacion;  // ⚠️ Nuevo
    private String rol;  // ⚠️ En lugar de "tipo"
    private String nombre;
    private String telefonoContacto;  // ⚠️ Cambiar nombre
    private String correoElectronico;  // ⚠️ Cambiar nombre
    // ⚠️ Eliminar: apellido, password, activo
}
```

#### Cambios en Productor.java:
```java
// DESPUÉS (versión original):
public class Productor extends Usuario {  // ⚠️ Hereda de Usuario
    private String id;  // ⚠️ ID específico del productor
    // ⚠️ Eliminar todos los atributos adicionales (documento, dirección, etc.)
    // ⚠️ Agregar métodos:
    void agregarLugarProduccion(LugarProduccion)
    LugarProduccion getLugaresProduccion()
}
```

#### Cambios en AsistenteTecnico.java:
```java
// DESPUÉS (versión original):
public class AsistenteTecnico extends Usuario {  // ⚠️ Hereda de Usuario
    private String id;
    private String numeroTarjetaProfesional;
    // ⚠️ Eliminar: documento, especialidad
    // ⚠️ Agregar métodos:
    void agregarLugarProduccion(LugarProduccion)
    LugarProduccion getLugarProduccion()
    void agregarInspeccionFitosanitaria(InspeccionFitosanitaria)
    InspeccionFitosanitaria getInspeccionFitosanitaria()
}
```

**IMPORTANCIA:** Usuario es **abstracta**, no se puede instanciar directamente.

---

### **2. MICROSERVICIO TERRITORIAL**

#### Nuevas entidades a agregar:

**Departamento.java**
```java
public class Departamento {
    private String id;
    private String codigoDane;  // ⚠️ Nuevo - código oficial
    private String nombre;
    private Collection<Municipio> municipio;  // ⚠️ Relación 1→*
    
    // Métodos:
    void agregarMunicipio(Municipio)
    Municipio getMunicipios()
}
```

**Municipio.java**
```java
public class Municipio {
    private String id;
    private String codigoDane;  // ⚠️ Nuevo
    private String nombre;
    private Collection<Vereda> vereda;  // ⚠️ Relación 1→*
    private Departamento departamento;  // ⚠️ Relación *→1
    
    // Métodos:
    void setDepartamento(Departamento)
    Departamento getDepartamento()
    void agregarVereda(Vereda)
    Vereda getVeredas()
}
```

**Vereda.java**
```java
public class Vereda {
    private String id;
    private String codigoDane;  // ⚠️ Nuevo
    private String nombre;
    private Municipio municipio;  // ⚠️ Relación *→1
    private Collection<Predio> predio;  // ⚠️ Relación 1→*
    
    // Métodos:
    void setMunicipio(Municipio)
    Municipio getMunicipio()
    void agregarLugarProduccion(LugarProduccion)
    LugarProduccion getLugarProduccion()
}
```

#### Cambios en Predio.java:
```java
// ANTES:
private Long id;
private String codigoPredio;
private String nombrePredio;
private Long productorId;
private String direccion;
private String municipio;  // ⚠️ Era String
private String departamento;  // ⚠️ Era String
private Double area;
private String tipoUso;

// DESPUÉS:
private String id;  // ⚠️ String en lugar de Long
private String numeroPredial;  // ⚠️ Cambio de nombre
private String direccion;
private float area;  // ⚠️ float en lugar de Double
private LugarProduccion lugarProduccion;  // ⚠️ Relación con objeto
private Vereda vereda;  // ⚠️ Relación con objeto (no String)
// ⚠️ Eliminar: codigoPredio, nombrePredio, productorId, tipoUso
```

#### Nueva entidad LugarProduccion.java:
```java
public class LugarProduccion {
    private String id;
    private String codigoIca;  // ⚠️ Código oficial ICA
    private String nombre;
    private Collection<Predio> predio;
    private Collection<Lote> lote;
    
    // Métodos:
    void agregarPredio(Predio)
    Predio getPredio()
    void agregarLote(Lote)
    Lote getLote()
    void setProductor(int)  // ⚠️ Futura referencia a MS Usuarios
    int getProductor()
    void setAsistenteTecnico(int)
    int getAsistenteTecnico()
    void agregarInspeccionFitosanitaria(int)
    int getInspeccionFitosanitaria()
}
```

#### Cambios en Lote.java:
```java
// ANTES:
private Long id;
private Long predioId;
private String numeroLote;
private String nombreLote;
private Double area;
private Long cultivoId;
private String estado;

// DESPUÉS:
private String id;  // ⚠️ String
private String descripcion;  // ⚠️ En lugar de nombreLote
private float extension;  // ⚠️ En lugar de area
private LugarProduccion lugarProduccion;  // ⚠️ Relación con objeto
private Collection<Cultivo> cultivo;  // ⚠️ Collection en lugar de ID
// ⚠️ Eliminar: predioId, numeroLote, nombreLote, estado
```

#### Cambios en Cultivo.java:
```java
// ANTES:
private Long id;
private String nombre;
private String nombreCientifico;
private String categoria;
private Integer diasCiclo;
private boolean activo;

// DESPUÉS:
private String id;  // ⚠️ String
private String nombreComun;  // ⚠️ En lugar de nombre
private String descripcion;
private String nombreCientifico;
private String nombreVariedad;  // ⚠️ Nuevo
private Lote lote;  // ⚠️ Relación con objeto
// ⚠️ Eliminar: categoria, diasCiclo, activo

// ⚠️ Métodos adicionales:
String getNombreCultivo()
void agregarLote(Lote)
void setPlaga(int)
int getPlaga()
void agregarResultadoTecnico(int)
int getResultadoTecnico()
```

---

### **3. MICROSERVICIO INSPECCIONES**

#### Cambios en Inspeccion.java → InspeccionFitosanitaria.java:
```java
// ANTES (Inspeccion):
private Long id;
private String codigoInspeccion;
private Long predioId;
private Long loteId;
private Long asistenteTecnicoId;
private String tipo;
private String estado;
private LocalDateTime fechaProgramada;
private LocalDateTime fechaRealizada;
private String observaciones;

// DESPUÉS (InspeccionFitosanitaria):
private String id;  // ⚠️ String
private String codigoIca;  // ⚠️ En lugar de codigoInspeccion
private String fechaInspeccion;  // ⚠️ String en lugar de LocalDateTime
private Collection<ResultadoTecnico> resultadoTecnico;  // ⚠️ Nuevo
// ⚠️ Eliminar: predioId, loteId, tipo, estado, fechaProgramada, fechaRealizada, observaciones

// ⚠️ Métodos:
void agregarResultadoTecnico(ResultadoTecnico)
ResultadoTecnico getResultadoTecnico()
void setAsistenteTecnico(int)
int getAsistenteTecnico()
void setLugarProduccion(LugarProduccion)
LugarProduccion getLugaresProduccion()
```

#### Cambios en DetalleInspeccion.java → ResultadoTecnico.java:
```java
// ANTES (DetalleInspeccion):
private Long id;
private Long inspeccionId;
private String aspecto;
private String descripcion;
private String calificacion;
private String recomendaciones;
private List<String> fotosUrls;

// DESPUÉS (ResultadoTecnico):
private String id;  // ⚠️ String
private int totalPlantasEvaluadas;  // ⚠️ Nuevo
private int plantasAfectadas;  // ⚠️ Nuevo
private String observaciones;
private Plaga plaga;  // ⚠️ Relación
private InspeccionFitosanitaria inspeccionFitosanitaria;  // ⚠️ Relación
// ⚠️ Eliminar: inspeccionId, aspecto, calificacion, recomendaciones, fotosUrls

// ⚠️ Métodos calculados:
float getNivelInsidencia()
String getNivelAlerta()
void agregarCultivo(Cultivo)
Cultivo getCultivo()
void agregarPlaga(Plaga)
Plaga getPlaga()
```

#### Cambios en Hallazgo.java → Plaga.java:
```java
// ANTES (Hallazgo):
private Long id;
private Long inspeccionId;
private String tipo;
private String gravedad;
private String descripcion;
private String accionCorrectiva;
private String estado;
private LocalDateTime fechaDeteccion;
private LocalDateTime fechaCierre;

// DESPUÉS (Plaga):
private String id;  // ⚠️ String
private String nombreComun;  // ⚠️ Nuevo
private String nombreCientifico;  // ⚠️ Nuevo
private String descripcion;
private ResultadoTecnico resultadoTecnico;  // ⚠️ Relación
// ⚠️ Eliminar: inspeccionId, tipo, gravedad, accionCorrectiva, estado, fechas

// ⚠️ Métodos:
void setCultivo(Cultivo)
Cultivo getCultivo()
void agregarResultadoTecnico(ResultadoTecnico)
ResultadoTecnico getResultadoTecnico()
```

---

## 🎯 ORDEN DE IMPLEMENTACIÓN RECOMENDADO

### Fase 1: Estructura Base
1. ✅ Actualizar **Usuario** (abstracta) con atributos correctos
2. ✅ Actualizar **Productor** y **AsistenteTecnico** (herencia)
3. ✅ Crear entidades territoriales: **Departamento → Municipio → Vereda**

### Fase 2: Territorio y Cultivos
4. ✅ Actualizar **Predio** con relaciones correctas
5. ✅ Crear **LugarProduccion** (entidad intermedia clave)
6. ✅ Actualizar **Lote** con Collection<Cultivo>
7. ✅ Actualizar **Cultivo** con atributos correctos

### Fase 3: Inspecciones
8. ✅ Renombrar y actualizar **Inspeccion → InspeccionFitosanitaria**
9. ✅ Renombrar y actualizar **DetalleInspeccion → ResultadoTecnico**
10. ✅ Renombrar y actualizar **Hallazgo → Plaga**

### Fase 4: Relaciones y Comunicación
11. ⚠️ Implementar relaciones entre microservicios (IDs cruzados)
12. ⚠️ Crear DTOs para comunicación
13. ⚠️ Implementar validaciones cruzadas

---

## ⚙️ CONSIDERACIONES TÉCNICAS

### Tipo de ID: Long vs String
**Original:** Usa `String` para IDs  
**Recomendación:** Mantener String en modelos pero usar UUID o generadores específicos

### Relaciones entre Microservicios
```java
// En LugarProduccion (MS Territorial):
private int productor;  // ⚠️ Referencia a MS Usuarios

// Cuando se crea un LugarProduccion:
// 1. Validar que el productor existe → llamada REST a MS Usuarios
// 2. Almacenar solo el ID
// 3. Cuando se necesite info completa → consultar MS Usuarios
```

### Collection<> vs Listas simples
**Original:** Usa `Collection<>`  
**Recomendación en microservicios:** 
- En modelo: mantener Collection o List
- En persistencia: almacenar solo IDs (referencias)
- En servicios: hacer consultas cruzadas cuando se necesite

---

## 📝 EJEMPLO PRÁCTICO: Crear InspeccionFitosanitaria

```java
// 1. Cliente llama al endpoint:
POST /api/inspecciones/crear
{
    "codigoIca": "ICA-12345",
    "fechaInspeccion": "2026-03-07",
    "lugarProduccionId": "LP-001",
    "asistenteTecnicoId": "AT-001"
}

// 2. InspeccionService valida:
// a) ¿Existe el LugarProduccion en MS Territorial?
//    → GET http://ms-territorial:8082/api/lugares-produccion/LP-001
// b) ¿Existe el AsistenteTecnico en MS Usuarios?
//    → GET http://ms-usuarios:8081/api/asistentes/AT-001
// c) ¿Está disponible el asistente?

// 3. Si todo OK, crea la inspección y retorna:
{
    "id": "INS-001",
    "codigoIca": "ICA-12345",
    "fechaInspeccion": "2026-03-07",
    "estado": "CREADA"
}
```

---

## 🚧 PRÓXIMOS PASOS INMEDIATOS

1. **Revisar este documento** y validar los cambios propuestos
2. **Decidir el orden de implementación** (¿empezar por usuarios o territorial?)
3. **Actualizar las clases del modelo** una por una
4. **Adaptar repositorios y servicios** según los nuevos atributos
5. **Implementar las relaciones** entre entidades
6. **Crear pruebas unitarias** para validar la lógica

---

¿Quieres que empiece a actualizar las clases de algún microservicio específico?
