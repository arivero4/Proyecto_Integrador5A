package modeloC;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa al técnico encargado de realizar las inspecciones fitosanitarias y brindar apoyo técnico a productores.
 * Extiende de Usuario para incluir información profesional adicional.
 */
public class AsistenteTecnico extends Usuario {

    /**
     * Identificador único del asistente técnico.
     */
    private String id;

    /**
     * Número de la tarjeta profesional que acredita al asistente técnico.
     */
    private String numeroTarjetaProfesional;

    /**
     * Lista de IDs de lugares de producción que el asistente supervisa (referencias a MS-Territorial)
     */
    private List<String> lugaresProduccionIds;

    /**
     * Lista de IDs de inspecciones fitosanitarias realizadas (referencias a MS-Inspecciones)
     */
    private List<String> inspeccionesIds;

    /**
     * Constructor por defecto que permite crear un objeto productor vacío para luego asignarle datos mediante los métodos setter.
     */
    public AsistenteTecnico() {
        super();
        this.lugaresProduccionIds = new ArrayList<>();
        this.inspeccionesIds = new ArrayList<>();
    }

    /**
     * Asigna un valor único al identificador del asistente técnico. Garantiza la unicidad en el registro del sistema.
     */
    public void setId(String pId) {
        this.id = pId;
    }

    /**
     * Devuelve el identificador actual del asistente técnico.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Permite registrar o modificar el número de la tarjeta profesional del asistente técnico.
     */
    public void setNumeroTarjetaProfesional(String pNumeroTarjetaProfesional) {
        this.numeroTarjetaProfesional = pNumeroTarjetaProfesional;
    }

    /**
     * Retorna el número de la tarjeta profesional registrada.
     */
    public String getNumeroTarjetaProfesional() {
        return this.numeroTarjetaProfesional;
    }

    /**
     * Asocia un lugar de producción que el asistente técnico supervisa o asesora mediante su ID.
     * Este método establece una relación directa entre el técnico y los lugares donde realiza su labor técnica.
     * 
     * @param lugarProduccionId ID del lugar de producción a asociar
     */
    public void agregarLugarProduccion(String lugarProduccionId) {
        if (lugarProduccionId != null && !this.lugaresProduccionIds.contains(lugarProduccionId)) {
            this.lugaresProduccionIds.add(lugarProduccionId);
        }
    }

    /**
     * Retorna la lista de IDs de lugares de producción supervisados.
     */
    public List<String> getLugaresProduccionIds() {
        return this.lugaresProduccionIds;
    }

    /**
     * Establece la lista completa de IDs de lugares de producción.
     */
    public void setLugaresProduccionIds(List<String> lugaresProduccionIds) {
        this.lugaresProduccionIds = lugaresProduccionIds != null ? lugaresProduccionIds : new ArrayList<>();
    }

    /**
     * Registra una inspección fitosanitaria realizada por el asistente técnico.
     * Permite llevar control sobre las inspecciones ejecutadas.
     * 
     * @param inspeccionId ID de la inspección fitosanitaria a registrar
     */
    public void agregarInspeccionFitosanitaria(String inspeccionId) {
        if (inspeccionId != null && !this.inspeccionesIds.contains(inspeccionId)) {
            this.inspeccionesIds.add(inspeccionId);
        }
    }

    /**
     * Retorna la lista de IDs de inspecciones fitosanitarias realizadas.
     */
    public List<String> getInspeccionesIds() {
        return this.inspeccionesIds;
    }

    /**
     * Establece la lista completa de IDs de inspecciones.
     */
    public void setInspeccionesIds(List<String> inspeccionesIds) {
        this.inspeccionesIds = inspeccionesIds != null ? inspeccionesIds : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "AsistenteTecnico{id='" + id + "', nombre='" + getNombre() + "', tarjetaProfesional='" + numeroTarjetaProfesional + "', inspecciones=" + inspeccionesIds.size() + "}";
    }
}
