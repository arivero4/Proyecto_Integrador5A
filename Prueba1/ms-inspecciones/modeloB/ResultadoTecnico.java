package modeloB;

/**
 * Representa el resultado técnico de un análisis específico realizado durante una inspección fitosanitaria.
 * Registra observaciones, diagnósticos y recomendaciones sobre aspectos específicos del cultivo.
 */
public class ResultadoTecnico {

    /**
     * Código único asignado por el ICA para identificar el resultado técnico.
     */
    private String codigoIca;

    /**
     * Descripción detallada de las observaciones realizadas durante el análisis técnico.
     * Puede incluir características visuales, mediciones o condiciones observadas en el cultivo.
     */
    private String descripcion;

    /**
     * Diagnóstico técnico basado en las observaciones y el análisis realizado.
     * Puede incluir identificación de problemas, evaluación de condiciones, o estado general del cultivo.
     */
    private String diagnostico;

    /**
     * Recomendaciones técnicas sugeridas basadas en el diagnóstico realizado.
     * Incluye acciones a tomar, tratamientos sugeridos, o medidas preventivas.
     */
    private String recomendacion;

    /**
     * Código ICA de la inspección fitosanitaria a la que pertenece este resultado (referencia a InspeccionFitosanitaria).
     */
    private String inspeccionFitosanitariaId;

    /**
     * Constructor por defecto que permite crear un resultado técnico vacío.
     */
    public ResultadoTecnico() {
    }

    /**
     * Asigna el código ICA único del resultado técnico.
     * 
     * @param pCodigoIca Código ICA del resultado técnico
     */
    public void setCodigoIca(String pCodigoIca) {
        this.codigoIca = pCodigoIca;
    }

    /**
     * Devuelve el código ICA del resultado técnico.
     * 
     * @return Código ICA del resultado técnico
     */
    public String getCodigoIca() {
        return this.codigoIca;
    }

    /**
     * Establece la descripción detallada del resultado técnico.
     * 
     * @param pDescripcion Descripción del resultado
     */
    public void setDescripcion(String pDescripcion) {
        this.descripcion = pDescripcion;
    }

    /**
     * Retorna la descripción del resultado técnico.
     * 
     * @return Descripción del resultado
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Asigna el diagnóstico técnico resultado del análisis.
     * 
     * @param pDiagnostico Diagnóstico técnico
     */
    public void setDiagnostico(String pDiagnostico) {
        this.diagnostico = pDiagnostico;
    }

    /**
     * Retorna el diagnóstico técnico.
     * 
     * @return Diagnóstico técnico
     */
    public String getDiagnostico() {
        return this.diagnostico;
    }

    /**
     * Establece las recomendaciones técnicas basadas en el diagnóstico.
     * 
     * @param pRecomendacion Recomendaciones técnicas
     */
    public void setRecomendacion(String pRecomendacion) {
        this.recomendacion = pRecomendacion;
    }

    /**
     * Retorna las recomendaciones técnicas.
     * 
     * @return Recomendaciones técnicas
     */
    public String getRecomendacion() {
        return this.recomendacion;
    }

    /**
     * Asigna el código ICA de la inspección fitosanitaria asociada.
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
        return "ResultadoTecnico{codigoIca='" + codigoIca + "', diagnostico='" + diagnostico + "', inspeccion='" + inspeccionFitosanitariaId + "'}";
    }
}
