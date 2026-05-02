package modeloC;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a la persona o entidad responsable de las actividades agrícolas dentro de un lugar de producción. 
 * Su rol es esencial para la administración, ya que está vinculado directamente con los lotes y las producciones que gestiona.
 * Extiende de Usuario heredando todos sus atributos y métodos.
 */
public class Productor extends Usuario {

    /**
     * Identificador único del productor. Este valor permite relacionarlo con sus lugares de producción y mantener una trazabilidad clara en el sistema.
     */
    private String id;

    /**
     * Lista de IDs de lugares de producción asociados al productor (referencias a MS-Territorial)
     */
    private List<String> lugaresProduccionIds;

    /**
     * Constructor por defecto que permite crear un objeto productor vacío para luego asignarle datos mediante los métodos setter.
     */
    public Productor() {
        super();
        this.lugaresProduccionIds = new ArrayList<>();
    }

    /**
     * Establece el identificador único del productor, asegurando su diferenciación dentro del sistema.
     */
    public void setId(String pId) {
        this.id = pId;
    }

    /**
     * Devuelve el identificador actual del productor, usado en búsquedas o vínculos con otros elementos (por ejemplo, lugares de producción).
     */
    public String getId() {
        return this.id;
    }

    /**
     * Asocia un nuevo lugar de producción al productor mediante su ID.
     * En un ambiente de microservicios, este método almacena la referencia al lugar de producción en MS-Territorial.
     * 
     * @param lugarProduccionId ID del lugar de producción a asociar
     */
    public void agregarLugarProduccion(String lugarProduccionId) {
        if (lugarProduccionId != null && !this.lugaresProduccionIds.contains(lugarProduccionId)) {
            this.lugaresProduccionIds.add(lugarProduccionId);
        }
    }

    /**
     * Retorna la lista de IDs de lugares de producción vinculados al productor.
     * Para obtener los objetos completos se debe hacer una llamada al MS-Territorial.
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

    @Override
    public String toString() {
        return "Productor{id='" + id + "', nombre='" + getNombre() + "', numeroIdentificacion='" + getNumeroIdentificacion() + "', lugaresProduccion=" + lugaresProduccionIds.size() + "}";
    }
}
