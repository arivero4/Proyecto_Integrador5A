package modeloA;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Representa una división territorial administrativa de primer orden en Colombia.
 * Cada departamento agrupa varios municipios y está identificado por su código DANE.
 */
public class Departamento {

    /**
     * Código único asignado por el DANE (Departamento Administrativo Nacional de Estadística) 
     * para identificar de manera oficial al departamento dentro del territorio nacional.
     */
    private String codigoDane;

    /**
     * Nombre oficial del departamento (por ejemplo, "Antioquia", "Cundinamarca", "Valle del Cauca").
     */
    private String nombreDepartamento;

    /**
     * Colección de códigos DANE de municipios pertenecientes a este departamento.
     * En arquitectura de microservicios, usamos IDs para referenciar entidades relacionadas.
     */
    private Collection<String> municipiosIds;

    /**
     * Constructor por defecto que inicializa el departamento con valores vacíos.
     */
    public Departamento() {
        this.municipiosIds = new ArrayList<>();
    }

    /**
     * Asigna el código DANE único del departamento.
     * 
     * @param pCodigoDane Código DANE del departamento
     */
    public void setCodigoDane(String pCodigoDane) {
        this.codigoDane = pCodigoDane;
    }

    /**
     * Devuelve el código DANE del departamento.
     * 
     * @return Código DANE del departamento
     */
    public String getCodigoDane() {
        return this.codigoDane;
    }

    /**
     * Establece el nombre oficial del departamento.
     * 
     * @param pNombreDepartamento Nombre del departamento
     */
    public void setNombreDepartamento(String pNombreDepartamento) {
        this.nombreDepartamento = pNombreDepartamento;
    }

    /**
     * Retorna el nombre del departamento.
     * 
     * @return Nombre del departamento
     */
    public String getNombreDepartamento() {
        return this.nombreDepartamento;
    }

    /**
     * Agrega el código DANE de un municipio a la colección del departamento.
     * 
     * @param municipioId Código DANE del municipio a agregar
     */
    public void agregarMunicipio(String municipioId) {
        if (municipioId != null && !this.municipiosIds.contains(municipioId)) {
            this.municipiosIds.add(municipioId);
        }
    }

    /**
     * Retorna la colección de códigos DANE de municipios asociados al departamento.
     * 
     * @return Colección de códigos de municipios
     */
    public Collection<String> getMunicipiosIds() {
        return this.municipiosIds;
    }

    /**
     * Establece la colección completa de códigos de municipios.
     * 
     * @param municipiosIds Colección de códigos DANE de municipios
     */
    public void setMunicipiosIds(Collection<String> municipiosIds) {
        this.municipiosIds = municipiosIds != null ? municipiosIds : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Departamento{codigoDane='" + codigoDane + "', nombre='" + nombreDepartamento + "', municipios=" + municipiosIds.size() + "}";
    }
}
