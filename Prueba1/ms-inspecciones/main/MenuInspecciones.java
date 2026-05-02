package main;

import presentacionB.*;
import modeloB.*;
import java.util.List;
import java.util.Scanner;

/**
 * Menú interactivo para el Microservicio de Inspecciones.
 * Permite gestionar inspecciones fitosanitarias, resultados técnicos y plagas.
 */
public class MenuInspecciones {
    
    private InspeccionFitosanitariaController inspeccionController;
    private ResultadoTecnicoController resultadoController;
    private PlagaController plagaController;
    private Scanner scanner;
    
    public MenuInspecciones(InspeccionFitosanitariaController inspeccionController,
                           ResultadoTecnicoController resultadoController,
                           PlagaController plagaController) {
        this.inspeccionController = inspeccionController;
        this.resultadoController = resultadoController;
        this.plagaController = plagaController;
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║    MENÚ - MICROSERVICIO INSPECCIONES       ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1. Inspecciones Fitosanitarias            ║");
            System.out.println("║  2. Resultados Técnicos                    ║");
            System.out.println("║  3. Plagas                                 ║");
            System.out.println("║  0. Salir                                  ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    menuInspecciones();
                    break;
                case 2:
                    menuResultados();
                    break;
                case 3:
                    menuPlagas();
                    break;
                case 0:
                    System.out.println("\n¡Hasta luego!");
                    break;
                default:
                    System.out.println("\n❌ Opción no válida.");
            }
        } while (opcion != 0);
        
        scanner.close();
    }
    
    // ===== MENÚ INSPECCIONES FITOSANITARIAS =====
    
    private void menuInspecciones() {
        System.out.println("\n--- INSPECCIONES FITOSANITARIAS ---");
        System.out.println("1. Crear inspección");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Listar todas");
        System.out.println("4. Listar por lugar de producción");
        System.out.println("5. Actualizar");
        System.out.println("6. Eliminar");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcion) {
            case 1:
                crearInspeccion();
                break;
            case 2:
                buscarInspeccion();
                break;
            case 3:
                listarInspecciones();
                break;
            case 4:
                listarInspeccionesPorLugarProduccion();
                break;
            case 5:
                actualizarInspeccion();
                break;
            case 6:
                eliminarInspeccion();
                break;
        }
    }
    
    private void crearInspeccion() {
        try {
            System.out.println("\n=== CREAR INSPECCIÓN FITOSANITARIA ===");
            System.out.print("Fecha visita (dd/mm/aaaa): ");
            String fechaVisita = scanner.nextLine();
            System.out.print("Estado plaga: ");
            String estadoPlaga = scanner.nextLine();
            System.out.print("Concepto técnico: ");
            String conceptoTecnico = scanner.nextLine();
            System.out.print("Código ICA del lugar de producción: ");
            String codigoIcaLugarProduccion = scanner.nextLine();
            System.out.print("ID del asistente técnico: ");
            String idAsistenteTecnico = scanner.nextLine();
            
            InspeccionFitosanitaria inspeccion = inspeccionController.crear(
                fechaVisita, estadoPlaga, conceptoTecnico, 
                codigoIcaLugarProduccion, idAsistenteTecnico
            );
            System.out.println("✅ Inspección creada con ID: " + inspeccion.getId());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarInspeccion() {
        System.out.print("\nID de la inspección: ");
        String id = scanner.nextLine();
        try {
            InspeccionFitosanitaria insp = inspeccionController.buscarPorId(id);
            System.out.println("\n📋 INSPECCIÓN FITOSANITARIA:");
            System.out.println("   ID: " + insp.getId());
            System.out.println("   Fecha: " + insp.getFechaVisita());
            System.out.println("   Estado plaga: " + insp.getEstadoPlaga());
            System.out.println("   Concepto: " + insp.getConceptoTecnico());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarInspecciones() {
        try {
            List<InspeccionFitosanitaria> inspecciones = inspeccionController.listarTodas();
            System.out.println("\n📋 INSPECCIONES (" + inspecciones.size() + "):");
            for (InspeccionFitosanitaria i : inspecciones) {
                System.out.println("• ID: " + i.getId() + " | Fecha: " + i.getFechaVisita() + 
                                 " | Estado: " + i.getEstadoPlaga());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarInspeccionesPorLugarProduccion() {
        System.out.print("\nCódigo ICA del lugar de producción: ");
        String codigo = scanner.nextLine();
        try {
            List<InspeccionFitosanitaria> inspecciones = 
                inspeccionController.buscarPorLugarProduccion(codigo);
            System.out.println("\n📋 INSPECCIONES (" + inspecciones.size() + "):");
            for (InspeccionFitosanitaria i : inspecciones) {
                System.out.println("• ID: " + i.getId() + " | " + i.getFechaVisita() + 
                                 " | " + i.getConceptoTecnico());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void actualizarInspeccion() {
        try {
            System.out.print("\nID de la inspección: ");
            String id = scanner.nextLine();
            
            System.out.println("Deje en blanco para mantener el valor actual");
            System.out.print("Nueva fecha visita (dd/mm/aaaa): ");
            String fechaVisita = scanner.nextLine();
            System.out.print("Nuevo estado plaga: ");
            String estadoPlaga = scanner.nextLine();
            System.out.print("Nuevo concepto técnico: ");
            String conceptoTecnico = scanner.nextLine();
            
            InspeccionFitosanitaria insp = inspeccionController.actualizar(
                id, fechaVisita, estadoPlaga, conceptoTecnico
            );
            System.out.println("✅ Inspección actualizada");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void eliminarInspeccion() {
        System.out.print("\nID de la inspección: ");
        String id = scanner.nextLine();
        System.out.print("¿Está seguro de eliminar esta inspección? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            try {
                inspeccionController.eliminar(id);
                System.out.println("✅ Inspección eliminada");
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
    
    // ===== MENÚ RESULTADOS TÉCNICOS =====
    
    private void menuResultados() {
        System.out.println("\n--- RESULTADOS TÉCNICOS ---");
        System.out.println("1. Crear resultado");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Listar todos");
        System.out.println("4. Listar por inspección");
        System.out.println("5. Actualizar");
        System.out.println("6. Eliminar");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcion) {
            case 1:
                crearResultado();
                break;
            case 2:
                buscarResultado();
                break;
            case 3:
                listarResultados();
                break;
            case 4:
                listarResultadosPorInspeccion();
                break;
            case 5:
                actualizarResultado();
                break;
            case 6:
                eliminarResultado();
                break;
        }
    }
    
    private void crearResultado() {
        try {
            System.out.println("\n=== CREAR RESULTADO TÉCNICO ===");
            System.out.print("Observación: ");
            String observacion = scanner.nextLine();
            System.out.print("Recomendación: ");
            String recomendacion = scanner.nextLine();
            System.out.print("ID de la inspección: ");
            String idInspeccion = scanner.nextLine();
            
            ResultadoTecnico resultado = resultadoController.crear(
                observacion, recomendacion, idInspeccion
            );
            System.out.println("✅ Resultado técnico creado con ID: " + resultado.getId());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarResultado() {
        System.out.print("\nID del resultado: ");
        String id = scanner.nextLine();
        try {
            ResultadoTecnico resultado = resultadoController.buscarPorId(id);
            System.out.println("\n📋 RESULTADO TÉCNICO:");
            System.out.println("   ID: " + resultado.getId());
            System.out.println("   Observación: " + resultado.getObservacion());
            System.out.println("   Recomendación: " + resultado.getRecomendacion());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarResultados() {
        try {
            List<ResultadoTecnico> resultados = resultadoController.listarTodos();
            System.out.println("\n📋 RESULTADOS TÉCNICOS (" + resultados.size() + "):");
            for (ResultadoTecnico r : resultados) {
                System.out.println("• ID: " + r.getId() + " | " + r.getObservacion());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarResultadosPorInspeccion() {
        System.out.print("\nID de la inspección: ");
        String idInspeccion = scanner.nextLine();
        try {
            List<ResultadoTecnico> resultados = 
                resultadoController.buscarPorInspeccion(idInspeccion);
            System.out.println("\n📋 RESULTADOS (" + resultados.size() + "):");
            for (ResultadoTecnico r : resultados) {
                System.out.println("• " + r.getObservacion() + " → " + r.getRecomendacion());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void actualizarResultado() {
        try {
            System.out.print("\nID del resultado: ");
            String id = scanner.nextLine();
            
            System.out.println("Deje en blanco para mantener el valor actual");
            System.out.print("Nueva observación: ");
            String observacion = scanner.nextLine();
            System.out.print("Nueva recomendación: ");
            String recomendacion = scanner.nextLine();
            
            ResultadoTecnico resultado = resultadoController.actualizar(
                id, observacion, recomendacion
            );
            System.out.println("✅ Resultado actualizado");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void eliminarResultado() {
        System.out.print("\nID del resultado: ");
        String id = scanner.nextLine();
        System.out.print("¿Está seguro de eliminar este resultado? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            try {
                resultadoController.eliminar(id);
                System.out.println("✅ Resultado eliminado");
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
    
    // ===== MENÚ PLAGAS =====
    
    private void menuPlagas() {
        System.out.println("\n--- GESTIÓN DE PLAGAS ---");
        System.out.println("1. Crear plaga");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Listar todas");
        System.out.println("4. Listar por resultado técnico");
        System.out.println("5. Actualizar");
        System.out.println("6. Eliminar");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcion) {
            case 1:
                crearPlaga();
                break;
            case 2:
                buscarPlaga();
                break;
            case 3:
                listarPlagas();
                break;
            case 4:
                listarPlagasPorResultado();
                break;
            case 5:
                actualizarPlaga();
                break;
            case 6:
                eliminarPlaga();
                break;
        }
    }
    
    private void crearPlaga() {
        try {
            System.out.println("\n=== CREAR PLAGA ===");
            System.out.print("Nombre común: ");
            String nombreComun = scanner.nextLine();
            System.out.print("Nombre científico: ");
            String nombreCientifico = scanner.nextLine();
            System.out.print("Tipo: ");
            String tipo = scanner.nextLine();
            System.out.print("ID del resultado técnico: ");
            String idResultadoTecnico = scanner.nextLine();
            
            Plaga plaga = plagaController.crear(
                nombreComun, nombreCientifico, tipo, idResultadoTecnico
            );
            System.out.println("✅ Plaga creada con ID: " + plaga.getId());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarPlaga() {
        System.out.print("\nID de la plaga: ");
        String id = scanner.nextLine();
        try {
            Plaga plaga = plagaController.buscarPorId(id);
            System.out.println("\n📋 PLAGA:");
            System.out.println("   ID: " + plaga.getId());
            System.out.println("   Nombre común: " + plaga.getNombreComun());
            System.out.println("   Nombre científico: " + plaga.getNombreCientifico());
            System.out.println("   Tipo: " + plaga.getTipo());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarPlagas() {
        try {
            List<Plaga> plagas = plagaController.listarTodas();
            System.out.println("\n📋 PLAGAS (" + plagas.size() + "):");
            for (Plaga p : plagas) {
                System.out.println("• " + p.getNombreComun() + " (" + 
                                 p.getNombreCientifico() + ") - " + p.getTipo());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarPlagasPorResultado() {
        System.out.print("\nID del resultado técnico: ");
        String idResultado = scanner.nextLine();
        try {
            List<Plaga> plagas = plagaController.buscarPorResultadoTecnico(idResultado);
            System.out.println("\n📋 PLAGAS (" + plagas.size() + "):");
            for (Plaga p : plagas) {
                System.out.println("• " + p.getNombreComun() + " - " + p.getTipo());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void actualizarPlaga() {
        try {
            System.out.print("\nID de la plaga: ");
            String id = scanner.nextLine();
            
            System.out.println("Deje en blanco para mantener el valor actual");
            System.out.print("Nuevo nombre común: ");
            String nombreComun = scanner.nextLine();
            System.out.print("Nuevo nombre científico: ");
            String nombreCientifico = scanner.nextLine();
            System.out.print("Nuevo tipo: ");
            String tipo = scanner.nextLine();
            
            Plaga plaga = plagaController.actualizar(
                id, nombreComun, nombreCientifico, tipo
            );
            System.out.println("✅ Plaga actualizada");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void eliminarPlaga() {
        System.out.print("\nID de la plaga: ");
        String id = scanner.nextLine();
        System.out.print("¿Está seguro de eliminar esta plaga? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            try {
                plagaController.eliminar(id);
                System.out.println("✅ Plaga eliminada");
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
}
