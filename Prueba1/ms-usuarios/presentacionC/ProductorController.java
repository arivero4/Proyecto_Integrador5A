package presentacionC;

import modeloC.Productor;
import negocioC.ProductorService;
import java.util.List;

/**
 * Controlador REST para gestión de productores
 * Expone endpoints HTTP para operaciones CRUD de productores
 */
public class ProductorController {
    private ProductorService service;
    
    public ProductorController() {
        this.service = new ProductorService();
    }
    
    // POST /api/productores
    public Productor crear(String numeroIdentificacion, String rol, String nombre, 
                          String telefonoContacto, String correoElectronico) {
        try {
            return service.crearProductor(numeroIdentificacion, rol, nombre, 
                                         telefonoContacto, correoElectronico);
        } catch (Exception e) {
            System.err.println("Error al crear productor: " + e.getMessage());
            throw e;
        }
    }
    
    // GET /api/productores/{id}
    public Productor obtenerPorId(String id) {
        return service.obtenerProductor(id);
    }
    
    // GET /api/productores/identificacion/{numeroIdentificacion}
    public Productor obtenerPorNumeroIdentificacion(String numeroIdentificacion) {
        return service.obtenerPorNumeroIdentificacion(numeroIdentificacion);
    }
    
    // GET /api/productores
    public List<Productor> listarTodos() {
        return service.listarTodos();
    }
    
    // GET /api/productores/lugar-produccion/{lugarProduccionId}
    public List<Productor> listarPorLugarProduccion(String lugarProduccionId) {
        return service.listarPorLugarProduccion(lugarProduccionId);
    }
    
    // POST /api/productores/{id}/lugares-produccion
    public Productor agregarLugarProduccion(String productorId, String lugarProduccionId) {
        try {
            return service.agregarLugarProduccion(productorId, lugarProduccionId);
        } catch (Exception e) {
            System.err.println("Error al agregar lugar de producción: " + e.getMessage());
            throw e;
        }
    }
    
    // PUT /api/productores/{id}
    public Productor actualizar(String id, String nombre, String telefonoContacto, String correoElectronico) {
        return service.actualizarProductor(id, nombre, telefonoContacto, correoElectronico);
    }
    
    // DELETE /api/productores/{id}
    public void eliminar(String id) {
        try {
            service.eliminarProductor(id);
            System.out.println("Productor eliminado exitosamente: " + id);
        } catch (Exception e) {
            System.err.println("Error al eliminar productor: " + e.getMessage());
            throw e;
        }
    }
}
