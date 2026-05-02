package modeloB;

/**
 * Representa una plaga fitosanitaria detectada durante una inspección.
 * Contiene información sobre el tipo de organismo, nivel de afectación y severidad del daño causado.
 */
public class Plaga {

    /**
     * Código único asignado por el ICA para identificar la plaga detectada.
     */
    private String codigoIca;

    /**
     * Nombre común de la plaga (por ejemplo, "Broca del café", "Minador de la hoja", "Roya del cafeto").
     */
    private String nombreComun;

    /**
     * Nombre científico de la plaga (por ejemplo, "Hypothenemus hampei", "Leucoptera coffeella", "Hemileia vastatrix").
     */
    private String nombreCientifico;

    /**
     * Tipo de organismo causante de la plaga (por ejemplo, "Insecto", "Hongo", "Bacteria", "Virus", "Nematodo", "Ácaro").
     */
    private String tipoOrganismo;

    /**
     * Número de plantas afectadas por la plaga durante la inspección.
     */
    private int numeroPlantasAfectadas;

    /**
     * Nivel de severidad del daño causado por la plaga.
     * Puede ser "Leve", "Moderado" o "Severo" según el impacto en el cultivo.
     */
    private String nivelSeveridad;

    /**
     * Código ICA de la inspección fitosanitaria donde se detectó la plaga (referencia a InspeccionFitosanitaria).
     */
    private String inspeccionFitosanitariaId;

    /**
     * Constructor por defecto que permite crear una plaga vacía.
     */
    public Plaga() {
    }

    /**
     * Asigna el código ICA único de la plaga.
     * 
     * @param pCodigoIca Código ICA de la plaga
     */
    public void setCodigoIca(String pCodigoIca) {
        this.codigoIca = pCodigoIca;
    }

    /**
     * Devuelve el código ICA de la plaga.
     * 
     * @return Código ICA de la plaga
     */
    public String getCodigoIca() {
        return this.codigoIca;
    }

    /**
     * Establece el nombre común de la plaga.
     * 
     * @param pNombreComun Nombre común de la plaga
     */
    public void setNombreComun(String pNombreComun) {
        this.nombreComun = pNombreComun;
    }

    /**
     * Retorna el nombre común de la plaga.
     * 
     * @return Nombre común de la plaga
     */
    public String getNombreComun() {
        return this.nombreComun;
    }

    /**
     * Asigna el nombre científico de la plaga.
     * 
     * @param pNombreCientifico Nombre científico de la plaga
     */
    public void setNombreCientifico(String pNombreCientifico) {
        this.nombreCientifico = pNombreCientifico;
    }

    /**
     * Retorna el nombre científico de la plaga.
     * 
     * @return Nombre científico de la plaga
     */
    public String getNombreCientifico() {
        return this.nombreCientifico;
    }

    /**
     * Establece el tipo de organismo causante de la plaga.
     * 
     * @param pTipoOrganismo Tipo de organismo
     */
    public void setTipoOrganismo(String pTipoOrganismo) {
        this.tipoOrganismo = pTipoOrganismo;
    }

    /**
     * Retorna el tipo de organismo.
     * 
     * @return Tipo de organismo
     */
    public String getTipoOrganismo() {
        return this.tipoOrganismo;
    }

    /**
     * Asigna el número de plantas afectadas por la plaga.
     * 
     * @param pNumeroPlantasAfectadas Número de plantas afectadas
     */
    public void setNumeroPlantasAfectadas(int pNumeroPlantasAfectadas) {
        this.numeroPlantasAfectadas = pNumeroPlantasAfectadas;
    }

    /**
     * Retorna el número de plantas afectadas.
     * 
     * @return Número de plantas afectadas
     */
    public int getNumeroPlantasAfectadas() {
        return this.numeroPlantasAfectadas;
    }

    /**
     * Establece el nivel de severidad del daño.
     * 
     * @param pNivelSeveridad Nivel de severidad ("Leve", "Moderado", "Severo")
     */
    public void setNivelSeveridad(String pNivelSeveridad) {
        this.nivelSeveridad = pNivelSeveridad;
    }

    /**
     * Retorna el nivel de severidad del daño.
     * 
     * @return Nivel de severidad
     */
    public String getNivelSeveridad() {
        return this.nivelSeveridad;
    }

   /**
     * Asigna el código ICA de la inspección fitosanitaria donde se detectó la plaga.
     * 
     * @param inspeccionFitosanitariaId Código ICA de la inspección
     */
    public void setInspeccionFitosanitariaId(String inspeccionFitosanitariaId) {
        this.inspeccionFitosanitariaId = inspeccionFitosanitariaId;
    }

    /**
     * Retorna el código ICA de la inspección fitosanitaria asociada.
     * 
     * @return Código ICA de la inspección
     */
    public String getInspeccionFitosanitariaId() {
        return this.inspeccionFitosanitariaId;
    }

    @Override
    public String toString() {
        return "Plaga{codigoIca='" + codigoIca + "', nombreComun='" + nombreComun + "', tipo='" + tipoOrganismo + "', severidad='" + nivelSeveridad + "', plantasAfectadas=" + numeroPlantasAfectadas + "}";
    }
}
