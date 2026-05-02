package modeloA;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Representa una división territorial rural de tercer orden dentro de un municipio en Colombia.
 * Las veredas son áreas rurales que agrupan varios predios y están identificadas por un código DANE.
 */
public class Vereda {

    /**
     * Código único asignado por el DANE para identificar oficialmente a la vereda.
     */
    private String codigoDane;

    /**
     * Nombre de la vereda (por ejemplo, "La Esperanza", "El Retiro", "San José").
     */
    private String nombreVereda;

    /**
     * Código DANE del municipio al que pertenece esta vereda.
     */
    private String municipioId;

    /**
     * Colección de números prediales de los predios ubicados en esta vereda.
     */
    private Collection<String> prediosIds;

    /**
     * Constructor por defecto que inicializa la vereda con valores vacíos.
     */
    public Vereda() {
        this.prediosIds = new ArrayList<>();
    }

    /**
     * Asigna el código DANE único de la vereda.
     * 
     * @param pCodigoDane Código DANE de la vereda
     */
    public void setCodigoDane(String pCodigoDane) {
        this.codigoDane = pCodigoDane;
    }

    /**
     * Devuelve el código DANE de la vereda.
     * 
     * @return Código DANE de la vereda
     */
    public String getCodigoDane() {
        return this.codigoDane;
    }

    /**
     * Establece el nombre de la vereda.
     * 
     * @param pNombreVereda Nombre de la vereda
     */
    public void setNombreVereda(String pNombreVereda) {
        this.nombreVereda = pNombreVereda;
    }

    /**
     * Retorna el nombre de la vereda.
     * 
     * @return Nombre de la vereda
     */
    public String getNombreVereda() {
        return this.nombreVereda;
    }

    /**
     * Asigna el código DANE del municipio al que pertenece la vereda.
     * 
     * @param municipioId Código DANE del municipio
     */
    public void setMunicipioId(String municipioId) {
        this.municipioId = municipioId;
    }

    /**
     * Retorna el código DANE del municipio asociado.
     * 
     * @return Código DANE del municipio
     */
    public String getMunicipioId() {
        return this.municipioId;
    }

    /**
     * Agrega el número predial de un predio a la colección de la vereda.
     * 
     * @param predioId Número predial del predio a agregar
     */
    public void agregarPredio(String predioId) {
        if (predioId != null && !this.prediosIds.contains(predioId)) {
            this.prediosIds.add(predioId);
        }
    }

    /**
     * Retorna la colección de números prediales de los predios en la vereda.
     * 
     * @return Colección de números prediales
     */
    public Collection<String> getPrediosIds() {
        return this.prediosIds;
    }

    /**
     * Establece la colección completa de números prediales de predios.
     * 
     * @param prediosIds Colección de números prediales
     */
    public void setPrediosIds(Collection<String> prediosIds) {
        this.prediosIds = prediosIds != null ? prediosIds : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Vereda{codigoDane='" + codigoDane + "', nombre='" + nombreVereda + "', municipio='" + municipioId + "', predios=" + prediosIds.size() + "}";
    }
}
