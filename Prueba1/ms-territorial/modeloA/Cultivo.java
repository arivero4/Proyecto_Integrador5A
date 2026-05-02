package modeloA;

/**
 * Representa un cultivo agrícola específico sembrado en un lote.
 * Contiene información sobre el tipo de planta, variedad, cantidad sembrada y área cultivada.
 */
public class Cultivo {

    /**
     * Código único asignado por el ICA para identificar el cultivo dentro del sistema de trazabilidad agrícola.
     */
    private String codigoIca;

    /**
     * Nombre común del cultivo (por ejemplo, "Café", "Plátano", "Aguacate").
     */
    private String nombreComun;

    /**
     * Nombre científico del cultivo (por ejemplo, "Coffea arabica", "Musa paradisiaca", "Persea americana").
     */
    private String nombreCientifico;

    /**
     * Nombre de la variedad específica del cultivo (por ejemplo, "Caturra", "Castilla", "Hass").
     */
    private String nombreVariedad;

    /**
     * Número total de plantas sembradas en el cultivo.
     */
    private int numeroPlantasSembradas;

    /**
     * Área total cultivada expresada en hectáreas.
     */
    private float areaTotal;

    /**
     * Constructor por defecto que permite crear un cultivo vacío para luego asignar información mediante los métodos setter.
     */
    public Cultivo() {
    }

    /**
     * Asigna el código ICA único del cultivo.
     * 
     * @param pCodigoIca Código ICA del cultivo
     */
    public void setCodigoIca(String pCodigoIca) {
        this.codigoIca = pCodigoIca;
    }

    /**
     * Devuelve el código ICA del cultivo.
     * 
     * @return Código ICA del cultivo
     */
    public String getCodigoIca() {
        return this.codigoIca;
    }

    /**
     * Establece el nombre común del cultivo.
     * 
     * @param pNombreComun Nombre común del cultivo
     */
    public void setNombreComun(String pNombreComun) {
        this.nombreComun = pNombreComun;
    }

    /**
     * Retorna el nombre común del cultivo.
     * 
     * @return Nombre común del cultivo
     */
    public String getNombreComun() {
        return this.nombreComun;
    }

    /**
     * Asigna el nombre científico del cultivo.
     * 
     * @param pNombreCientifico Nombre científico del cultivo
     */
    public void setNombreCientifico(String pNombreCientifico) {
        this.nombreCientifico = pNombreCientifico;
    }

    /**
     * Retorna el nombre científico del cultivo.
     * 
     * @return Nombre científico del cultivo
     */
    public String getNombreCientifico() {
        return this.nombreCientifico;
    }

    /**
     * Establece el nombre de la variedad del cultivo.
     * 
     * @param pNombreVariedad Nombre de la variedad
     */
    public void setNombreVariedad(String pNombreVariedad) {
        this.nombreVariedad = pNombreVariedad;
    }

    /**
     * Retorna el nombre de la variedad del cultivo.
     * 
     * @return Nombre de la variedad
     */
    public String getNombreVariedad() {
        return this.nombreVariedad;
    }

    /**
     * Asigna el número de plantas sembradas.
     * 
     * @param pNumeroPlantasSembradas Número de plantas sembradas
     */
    public void setNumeroPlantasSembradas(int pNumeroPlantasSembradas) {
        this.numeroPlantasSembradas = pNumeroPlantasSembradas;
    }

    /**
     * Retorna el número de plantas sembradas.
     * 
     * @return Número de plantas sembradas
     */
    public int getNumeroPlantasSembradas() {
        return this.numeroPlantasSembradas;
    }

    /**
     * Establece el área total cultivada en hectáreas.
     * 
     * @param pAreaTotal Área total en hectáreas
     */
    public void setAreaTotal(float pAreaTotal) {
        this.areaTotal = pAreaTotal;
    }

    /**
     * Retorna el área total cultivada.
     * 
     * @return Área total en hectáreas
     */
    public float getAreaTotal() {
        return this.areaTotal;
    }

    @Override
    public String toString() {
        return "Cultivo{codigoIca='" + codigoIca + "', nombreComun='" + nombreComun + "', variedad='" + nombreVariedad + "', plantas=" + numeroPlantasSembradas + ", area=" + areaTotal + "ha}";
    }
}
