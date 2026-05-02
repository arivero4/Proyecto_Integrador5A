package modeloA;

/**
 * Representa una porción específica de terreno dentro de un lugar de producción, destinada al cultivo de un producto agrícola.
 * Cada lote tiene un código ICA y contiene información sobre su extensión y descripción.
 */
public class Lote {

    /**
     * Código único asignado por el ICA para identificar oficialmente el lote dentro del sistema de trazabilidad.
     */
    private String codigoIca;

    /**
     * Número o código interno que identifica el lote dentro del lugar de producción (por ejemplo, "Lote 1", "A1", "Norte-01").
     */
    private String numeroLote;

    /**
     * Descripción detallada del lote que puede incluir ubicación, características del suelo, o características distintivas.
     */
    private String descripcion;

    /**
     * Extensión del lote medida en hectáreas. Indica el tamaño físico del área cultivable.
     */
    private float extension;

    /**
     * Código ICA del lugar de producción al que pertenece este lote.
     */
    private String lugarProduccionId;

    /**
     * Código ICA del cultivo sembrado en el lote (puede ser null si el lote está sin sembrar).
     */
    private String cultivoId;

    /**
     * Constructor por defecto que permite crear un lote vacío para luego asignar información mediante los métodos setter.
     */
    public Lote() {
    }

    /**
     * Asigna el código ICA del lote.
     * 
     * @param pCodigoIca Código ICA del lote
     */
    public void setCodigoIca(String pCodigoIca) {
        this.codigoIca = pCodigoIca;
    }

    /**
     * Devuelve el código ICA del lote.
     * 
     * @return Código ICA del lote
     */
    public String getCodigoIca() {
        return this.codigoIca;
    }

    /**
     * Establece el número interno del lote dentro del lugar de producción.
     * 
     * @param pNumeroLote Número del lote
     */
    public void setNumeroLote(String pNumeroLote) {
        this.numeroLote = pNumeroLote;
    }

    /**
     * Retorna el número del lote.
     * 
     * @return Número del lote
     */
    public String getNumeroLote() {
        return this.numeroLote;
    }

    /**
     * Asigna la descripción del lote.
     * 
     * @param pDescripcion Descripción del lote
     */
    public void setDescripcion(String pDescripcion) {
        this.descripcion = pDescripcion;
    }

    /**
     * Retorna la descripción del lote.
     * 
     * @return Descripción del lote
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Establece la extensión del lote en hectáreas.
     * 
     * @param pExtension Extensión en hectáreas
     */
    public void setExtension(float pExtension) {
        this.extension = pExtension;
    }

    /**
     * Retorna la extensión del lote.
     * 
     * @return Extensión en hectáreas
     */
    public float getExtension() {
        return this.extension;
    }

    /**
     * Asigna el código ICA del lugar de producción al que pertenece el lote.
     * 
     * @param lugarProduccionId Código ICA del lugar de producción
     */
    public void setLugarProduccionId(String lugarProduccionId) {
        this.lugarProduccionId = lugarProduccionId;
    }

    /**
     * Retorna el código ICA del lugar de producción asociado.
     * 
     * @return Código ICA del lugar de producción
     */
    public String getLugarProduccionId() {
        return this.lugarProduccionId;
    }

    /**
     * Asigna el código ICA del cultivo sembrado en el lote.
     * 
     * @param cultivoId Código ICA del cultivo
     */
    public void setCultivoId(String cultivoId) {
        this.cultivoId = cultivoId;
    }

    /**
     * Retorna el código ICA del cultivo sembrado.
     * 
     * @return Código ICA del cultivo
     */
    public String getCultivoId() {
        return this.cultivoId;
    }

    @Override
    public String toString() {
        return "Lote{codigoIca='" + codigoIca + "', numero='" + numeroLote + "', extension=" + extension + "ha, cultivo='" + cultivoId + "'}";
    }
}
