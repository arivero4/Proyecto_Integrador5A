package modeloA;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Representa una división administrativa de segundo orden dentro de un departamento en Colombia.
 * Cada municipio está identificado con un código DANE y pertenece a un único departamento.
 */
public class Municipio {

    /**
     * Código único asignado por el DANE para identificar oficialmente al municipio.
     */
    private String codigoDane;

    /**
     * Nombre oficial del municipio (por ejemplo, "Medellín", "Bogotá D.C.", "Cali").
     */
    private String nombreMunicipio;

    /**
     * Código DANE del departamento al que pertenece este municipio.
     */
    private String departamentoId;

    /**
     * Colección de códigos DANE de veredas ubicadas en este municipio.
     */
    private Collection<String> veredasIds;

    /**
     * Constructor por defecto que inicializa el municipio con valores vacíos.
     */
    public Municipio() {
        this.veredasIds = new ArrayList<>();
    }

    /**
     * Asigna el código DANE único del municipio.
     * 
     * @param pCodigoDane Código DANE del municipio
     */
    public void setCodigoDane(String pCodigoDane) {
        this.codigoDane = pCodigoDane;
    }

    /**
     * Devuelve el código DANE del municipio.
     * 
     * @return Código DANE del municipio
     */
    public String getCodigoDane() {
        return this.codigoDane;
    }

    /**
     * Establece el nombre oficial del municipio.
     * 
     * @param pNombreMunicipio Nombre del municipio
     */
    public void setNombreMunicipio(String pNombreMunicipio) {
        this.nombreMunicipio = pNombreMunicipio;
    }

    /**
     * Retorna el nombre del municipio.
     * 
     * @return Nombre del municipio
     */
    public String getNombreMunicipio() {
        return this.nombreMunicipio;
    }

    /**
     * Asigna el código DANE del departamento al que pertenece el municipio.
     * 
     * @param departamentoId Código DANE del departamento
     */
    public void setDepartamentoId(String departamentoId) {
        this.departamentoId = departamentoId;
    }

    /**
     * Retorna el código DANE del departamento asociado.
     * 
     * @return Código DANE del departamento
     */
    public String getDepartamentoId() {
        return this.departamentoId;
    }

    /**
     * Agrega el código DANE de una vereda a la colección del municipio.
     * 
     * @param veredaId Código DANE de la vereda a agregar
     */
    public void agregarVereda(String veredaId) {
        if (veredaId != null && !this.veredasIds.contains(veredaId)) {
            this.veredasIds.add(veredaId);
        }
    }

    /**
     * Retorna la colección de códigos DANE de veredas ubicadas en el municipio.
     * 
     * @return Colección de códigos de veredas
     */
    public Collection<String> getVeredasIds() {
        return this.veredasIds;
    }

    /**
     * Establece la colección completa de códigos de veredas.
     * 
     * @param veredasIds Colección de códigos DANE de veredas
     */
    public void setVeredasIds(Collection<String> veredasIds) {
        this.veredasIds = veredasIds != null ? veredasIds : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Municipio{codigoDane='" + codigoDane + "', nombre='" + nombreMunicipio + "', departamento='" + departamentoId + "', veredas=" + veredasIds.size() + "}";
    }
}
