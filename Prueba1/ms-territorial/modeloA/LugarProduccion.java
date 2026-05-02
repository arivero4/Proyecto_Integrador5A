package modeloA;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Representa un lugar de producción agrícola registrado en el sistema.
 * Es la unidad principal de gestión que relaciona un productor, un predio, un asistente técnico, y contiene lotes cultivables.
 * Cada lugar de producción está identificado por un código ICA único.
 */
public class LugarProduccion {

    /**
     * Código único asignado por el ICA (Instituto Colombiano Agropecuario) 
     * para identificar oficialmente el lugar de producción.
     */
    private String codigoIca;

    /**
     * ID del productor responsable del lugar de producción (referencia a MS-Usuarios).
     */
    private String productorId;

    /**
     * Número predial del predio donde se ubica el lugar de producción (referencia a Predio).
     */
    private String predioId;

    /**
     * ID del asistente técnico asignado al lugar de producción (referencia a MS-Usuarios).
     */
    private String asistenteTecnicoId;

    /**
     * Colección de códigos ICA de lotes que forman parte del lugar de producción.
     */
    private Collection<String> lotesIds;

    /**
     * Constructor por defecto que inicializa el lugar de producción con valores vacíos.
     */
    public LugarProduccion() {
        this.lotesIds = new ArrayList<>();
    }

    /**
     * Asigna el código ICA único del lugar de producción.
     * 
     * @param pCodigoIca Código ICA del lugar de producción
     */
    public void setCodigoIca(String pCodigoIca) {
        this.codigoIca = pCodigoIca;
    }

    /**
     * Devuelve el código ICA del lugar de producción.
     * 
     * @return Código ICA del lugar de producción
     */
    public String getCodigoIca() {
        return this.codigoIca;
    }

    /**
     * Asigna el ID del productor responsable del lugar de producción.
     * 
     * @param pProductorId ID del productor
     */
    public void setProductorId(String pProductorId) {
        this.productorId = pProductorId;
    }

    /**
     * Retorna el ID del productor asociado.
     * 
     * @return ID del productor
     */
    public String getProductorId() {
        return this.productorId;
    }

    /**
     * Asigna el número predial del predio donde se ubica el lugar de producción.
     * 
     * @param pPredioId Número predial del predio
     */
    public void setPredioId(String pPredioId) {
        this.predioId = pPredioId;
    }

    /**
     * Retorna el número predial del predio asociado.
     * 
     * @return Número predial del predio
     */
    public String getPredioId() {
        return this.predioId;
    }

    /**
     * Asigna el ID del asistente técnico que supervisa el lugar de producción.
     * 
     * @param pAsistenteTecnicoId ID del asistente técnico
     */
    public void setAsistenteTecnicoId(String pAsistenteTecnicoId) {
        this.asistenteTecnicoId = pAsistenteTecnicoId;
    }

    /**
     * Retorna el ID del asistente técnico asociado.
     * 
     * @return ID del asistente técnico
     */
    public String getAsistenteTecnicoId() {
        return this.asistenteTecnicoId;
    }

    /**
     * Agrega el código ICA de un lote al lugar de producción.
     * 
     * @param loteId Código ICA del lote a agregar
     */
    public void agregarLote(String loteId) {
        if (loteId != null && !this.lotesIds.contains(loteId)) {
            this.lotesIds.add(loteId);
        }
    }

    /**
     * Retorna la colección de códigos ICA de lotes asociados al lugar de producción.
     * 
     * @return Colección de códigos de lotes
     */
    public Collection<String> getLotesIds() {
        return this.lotesIds;
    }

    /**
     * Establece la colección completa de códigos de lotes.
     * 
     * @param lotesIds Colección de códigos ICA de lotes
     */
    public void setLotesIds(Collection<String> lotesIds) {
        this.lotesIds = lotesIds != null ? lotesIds : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "LugarProduccion{codigoIca='" + codigoIca + "', productor='" + productorId + "', predio='" + predioId + "', lotes=" + lotesIds.size() + "}";
    }
}
