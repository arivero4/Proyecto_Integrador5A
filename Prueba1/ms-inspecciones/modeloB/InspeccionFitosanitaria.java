package modeloB;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Representa una inspección fitosanitaria realizada en un lugar de producción agrícola.
 * Registra información clave sobre el estado fitosanitario del cultivo, incluyendo resultados técnicos y plagas detectadas.
 */
public class InspeccionFitosanitaria {

    /**
     * Código único asignado por el ICA para identificar la inspección fitosanitaria.
     */
    private String codigoIca;

    /**
     * Código ICA del lugar de producción donde se realiza la inspección (referencia a MS-Territorial).
     */
    private String lugarProduccionId;

    /**
     * Fecha en la que se lleva a cabo la inspección fitosanitaria.
     */
    private Date fecha;

    /**
     * ID del asistente técnico que realizó la inspección (referencia a MS-Usuarios).
     */
    private String asistenteTecnicoId;

    /**
     * Total de plantas evaluadas durante la inspección.
     */
    private int totalPlantasEvaluadas;

    /**
     * Número de plantas que presentan algún tipo de afectación fitosanitaria.
     */
    private int plantasAfectadas;

    /**
     * Colección de códigos ICA de resultados técnicos registrados durante la inspección.
     */
    private Collection<String> resultadosTecnicosIds;

    /**
     * Colección de códigos ICA de plagas detectadas durante la inspección.
     */
    private Collection<String> plagasIds;

    /**
     * Constructor por defecto que inicializa la inspección con valores vacíos.
     */
    public InspeccionFitosanitaria() {
        this.resultadosTecnicosIds = new ArrayList<>();
        this.plagasIds = new ArrayList<>();
    }

    /**
     * Asigna el código ICA único de la inspección.
     * 
     * @param pCodigoIca Código ICA de la inspección
     */
    public void setCodigoIca(String pCodigoIca) {
        this.codigoIca = pCodigoIca;
    }

    /**
     * Devuelve el código ICA de la inspección.
     * 
     * @return Código ICA de la inspección
     */
    public String getCodigoIca() {
        return this.codigoIca;
    }

    /**
     * Asigna el código ICA del lugar de producción inspeccionado.
     * 
     * @param lugarProduccionId Código ICA del lugar de producción
     */
    public void setLugarProduccionId(String lugarProduccionId) {
        this.lugarProduccionId = lugarProduccionId;
    }

    /**
     * Retorna el código ICA del lugar de producción.
     * 
     * @return Código ICA del lugar de producción
     */
    public String getLugarProduccionId() {
        return this.lugarProduccionId;
    }

    /**
     * Establece la fecha de realización de la inspección.
     * 
     * @param pFecha Fecha de la inspección
     */
    public void setFecha(Date pFecha) {
        this.fecha = pFecha;
    }

    /**
     * Retorna la fecha de la inspección.
     * 
     * @return Fecha de la inspección
     */
    public Date getFecha() {
        return this.fecha;
    }

    /**
     * Asigna el ID del asistente técnico responsable de la inspección.
     * 
     * @param asistenteTecnicoId ID del asistente técnico
     */
    public void setAsistenteTecnicoId(String asistenteTecnicoId) {
        this.asistenteTecnicoId = asistenteTecnicoId;
    }

    /**
     * Retorna el ID del asistente técnico.
     * 
     * @return ID del asistente técnico
     */
    public String getAsistenteTecnicoId() {
        return this.asistenteTecnicoId;
    }

    /**
     * Establece el número total de plantas evaluadas.
     * 
     * @param pTotalPlantasEvaluadas Total de plantas evaluadas
     */
    public void setTotalPlantasEvaluadas(int pTotalPlantasEvaluadas) {
        this.totalPlantasEvaluadas = pTotalPlantasEvaluadas;
    }

    /**
     * Retorna el total de plantas evaluadas.
     * 
     * @return Total de plantas evaluadas
     */
    public int getTotalPlantasEvaluadas() {
        return this.totalPlantasEvaluadas;
    }

    /**
     * Establece el número de plantas afectadas.
     * 
     * @param pPlantasAfectadas Número de plantas afectadas
     */
    public void setPlantasAfectadas(int pPlantasAfectadas) {
        this.plantasAfectadas = pPlantasAfectadas;
    }

    /**
     * Retorna el número de plantas afectadas.
     * 
     * @return Plantas afectadas
     */
    public int getPlantasAfectadas() {
        return this.plantasAfectadas;
    }

    /**
     * Agrega un resultado técnico a la inspección.
     * 
     * @param resultadoTecnicoId Código ICA del resultado técnico
     */
    public void agregarResultadoTecnico(String resultadoTecnicoId) {
        if (resultadoTecnicoId != null && !this.resultadosTecnicosIds.contains(resultadoTecnicoId)) {
            this.resultadosTecnicosIds.add(resultadoTecnicoId);
        }
    }

    /**
     * Retorna la colección de códigos ICA de resultados técnicos.
     * 
     * @return Colección de códigos de resultados técnicos
     */
    public Collection<String> getResultadosTecnicosIds() {
        return this.resultadosTecnicosIds;
    }

    /**
     * Establece la colección completa de códigos de resultados técnicos.
     * 
     * @param resultadosTecnicosIds Colección de códigos ICA de resultados técnicos
     */
    public void setResultadosTecnicosIds(Collection<String> resultadosTecnicosIds) {
        this.resultadosTecnicosIds = resultadosTecnicosIds != null ? resultadosTecnicosIds : new ArrayList<>();
    }

    /**
     * Agrega una plaga detectada a la inspección.
     * 
     * @param plagaId Código ICA de la plaga
     */
    public void agregarPlaga(String plagaId) {
        if (plagaId != null && !this.plagasIds.contains(plagaId)) {
            this.plagasIds.add(plagaId);
        }
    }

    /**
     * Retorna la colección de códigos ICA de plagas detectadas.
     * 
     * @return Colección de códigos de plagas
     */
    public Collection<String> getPlagasIds() {
        return this.plagasIds;
    }

    /**
     * Establece la colección completa de códigos de plagas.
     * 
     * @param plagasIds Colección de códigos ICA de plagas
     */
    public void setPlagasIds(Collection<String> plagasIds) {
        this.plagasIds = plagasIds != null ? plagasIds : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "InspeccionFitosanitaria{codigoIca='" + codigoIca + "', fecha=" + fecha + ", plantas=" + totalPlantasEvaluadas + ", afectadas=" + plantasAfectadas + "}";
    }
}
