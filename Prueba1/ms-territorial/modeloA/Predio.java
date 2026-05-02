package modeloA;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Representa una propiedad rural registrada con un número predial único.
 * El predio es una unidad de propiedad que se ubica en una vereda específica y puede albergar varios lugares de producción.
 */
public class Predio {

    /**
     * Número predial nacional único que identifica oficialmente al predio ante las autoridades catastrales.
     * Es el equivalente a la matrícula inmobiliaria en zonas rurales.
     */
    private String numeroPredial;

    /**
     * Nombre o denominación del predio (por ejemplo, "Finca La Esperanza", "Hacienda El Porvenir").
     */
    private String nombrePredio;

    /**
     * Dirección o ubicación descriptiva del predio dentro de la vereda.
     */
    private String direccion;

    /**
     * Código DANE de la vereda donde está ubicado el predio.
     */
    private String veredaId;

    /**
     * Colección de códigos ICA de lugares de producción ubicados en este predio.
     */
    private Collection<String> lugaresProduccionIds;

    /**
     * Constructor por defecto que inicializa el predio con valores vacíos.
     */
    public Predio() {
        this.lugaresProduccionIds = new ArrayList<>();
    }

    /**
     * Asigna el número predial del predio.
     * 
     * @param pNumeroPredial Número predial único
     */
    public void setNumeroPredial(String pNumeroPredial) {
        this.numeroPredial = pNumeroPredial;
    }

    /**
     * Devuelve el número predial del predio.
     * 
     * @return Número predial
     */
    public String getNumeroPredial() {
        return this.numeroPredial;
    }

    /**
     * Establece el nombre del predio.
     * 
     * @param pNombrePredio Nombre del predio
     */
    public void setNombrePredio(String pNombrePredio) {
        this.nombrePredio = pNombrePredio;
    }

    /**
     * Retorna el nombre del predio.
     * 
     * @return Nombre del predio
     */
    public String getNombrePredio() {
        return this.nombrePredio;
    }

    /**
     * Asigna la dirección del predio.
     * 
     * @param pDireccion Dirección del predio
     */
    public void setDireccion(String pDireccion) {
        this.direccion = pDireccion;
    }

    /**
     * Retorna la dirección del predio.
     * 
     * @return Dirección del predio
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     * Asigna el código DANE de la vereda donde se ubica el predio.
     * 
     * @param veredaId Código DANE de la vereda
     */
    public void setVeredaId(String veredaId) {
        this.veredaId = veredaId;
    }

    /**
     * Retorna el código DANE de la vereda asociada.
     * 
     * @return Código DANE de la vereda
     */
    public String getVeredaId() {
        return this.veredaId;
    }

    /**
     * Agrega el código ICA de un lugar de producción al predio.
     * 
     * @param lugarProduccionId Código ICA del lugar de producción
     */
    public void agregarLugarProduccion(String lugarProduccionId) {
        if (lugarProduccionId != null && !this.lugaresProduccionIds.contains(lugarProduccionId)) {
            this.lugaresProduccionIds.add(lugarProduccionId);
        }
    }

    /**
     * Retorna la colección de códigos ICA de lugares de producción en el predio.
     * 
     * @return Colección de códigos de lugares de producción
     */
    public Collection<String> getLugaresProduccionIds() {
        return this.lugaresProduccionIds;
    }

    /**
     * Establece la colección completa de códigos de lugares de producción.
     * 
     * @param lugaresProduccionIds Colección de códigos ICA de lugares de producción
     */
    public void setLugaresProduccionIds(Collection<String> lugaresProduccionIds) {
        this.lugaresProduccionIds = lugaresProduccionIds != null ? lugaresProduccionIds : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Predio{numeroPredial='" + numeroPredial + "', nombre='" + nombrePredio + "', vereda='" + veredaId + "', lugaresProduccion=" + lugaresProduccionIds.size() + "}";
    }
}
