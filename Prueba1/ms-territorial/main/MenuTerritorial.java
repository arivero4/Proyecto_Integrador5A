package main;

import presentacionA.*;
import modeloA.*;
import java.util.List;
import java.util.Scanner;

/**
 * Menú interactivo para el Microservicio Territorial.
 * Permite gestionar la estructura territorial y lugares de producción.
 */
public class MenuTerritorial {
    
    private DepartamentoController departamentoController;
    private MunicipioController municipioController;
    private VeredaController veredaController;
    private PredioController predioController;
    private LugarProduccionController lugarProduccionController;
    private LoteController loteController;
    private CultivoController cultivoController;
    private Scanner scanner;
    
    public MenuTerritorial(DepartamentoController departamentoController,
                          MunicipioController municipioController,
                          VeredaController veredaController,
                          PredioController predioController,
                          LugarProduccionController lugarProduccionController,
                          LoteController loteController,
                          CultivoController cultivoController) {
        this.departamentoController = departamentoController;
        this.municipioController = municipioController;
        this.veredaController = veredaController;
        this.predioController = predioController;
        this.lugarProduccionController = lugarProduccionController;
        this.loteController = loteController;
        this.cultivoController = cultivoController;
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║    MENÚ - MICROSERVICIO TERRITORIAL        ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1. Gestión de Departamentos               ║");
            System.out.println("║  2. Gestión de Municipios                  ║");
            System.out.println("║  3. Gestión de Veredas                     ║");
            System.out.println("║  4. Gestión de Predios                     ║");
            System.out.println("║  5. Gestión de Lugares de Producción       ║");
            System.out.println("║  6. Gestión de Lotes                       ║");
            System.out.println("║  7. Gestión de Cultivos                    ║");
            System.out.println("║  0. Salir                                  ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    gestionarDepartamentos();
                    break;
                case 2:
                    gestionarMunicipios();
                    break;
                case 3:
                    gestionarVeredas();
                    break;
                case 4:
                    gestionarPredios();
                    break;
                case 5:
                    gestionarLugaresProduccion();
                    break;
                case 6:
                    gestionarLotes();
                    break;
                case 7:
                    gestionarCultivos();
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
    
    // ===== GESTIÓN DE DEPARTAMENTOS =====
    
    private void gestionarDepartamentos() {
        System.out.println("\n--- GESTIÓN DE DEPARTAMENTOS ---");
        System.out.println("1. Crear departamento");
        System.out.println("2. Buscar por código DANE");
        System.out.println("3. Listar todos");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcion) {
            case 1:
                crearDepartamento();
                break;
            case 2:
                buscarDepartamento();
                break;
            case 3:
                listarDepartamentos();
                break;
        }
    }
    
    private void crearDepartamento() {
        try {
            System.out.println("\n=== CREAR DEPARTAMENTO ===");
            System.out.print("Código DANE: ");
            String codigoDane = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Región: ");
            String region = scanner.nextLine();
            
            Departamento depto = departamentoController.crear(codigoDane, nombre, region);
            System.out.println("✅ Departamento creado: " + depto.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarDepartamento() {
        System.out.print("\nCódigo DANE: ");
        String codigo = scanner.nextLine();
        try {
            Departamento depto = departamentoController.buscarPorCodigoDane(codigo);
            System.out.println("\n📋 DEPARTAMENTO:");
            System.out.println("   Código DANE: " + depto.getCodigoDane());
            System.out.println("   Nombre: " + depto.getNombre());
            System.out.println("   Región: " + depto.getRegion());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarDepartamentos() {
        try {
            List<Departamento> departamentos = departamentoController.listarTodos();
            System.out.println("\n📋 DEPARTAMENTOS (" + departamentos.size() + "):");
            for (Departamento d : departamentos) {
                System.out.println("• " + d.getNombre() + " (" + d.getCodigoDane() + ") - " + d.getRegion());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    // ===== GESTIÓN DE MUNICIPIOS =====
    
    private void gestionarMunicipios() {
        System.out.println("\n--- GESTIÓN DE MUNICIPIOS ---");
        System.out.println("1. Crear municipio");
        System.out.println("2. Buscar por código DANE");
        System.out.println("3. Listar por departamento");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcion) {
            case 1:
                crearMunicipio();
                break;
            case 2:
                buscarMunicipio();
                break;
            case 3:
                listarMunicipiosPorDepartamento();
                break;
        }
    }
    
    private void crearMunicipio() {
        try {
            System.out.println("\n=== CREAR MUNICIPIO ===");
            System.out.print("Código DANE: ");
            String codigoDane = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Código DANE del departamento: ");
            String codigoDaneDepartamento = scanner.nextLine();
            
            Municipio mpio = municipioController.crear(codigoDane, nombre, codigoDaneDepartamento);
            System.out.println("✅ Municipio creado: " + mpio.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarMunicipio() {
        System.out.print("\nCódigo DANE: ");
        String codigo = scanner.nextLine();
        try {
            Municipio mpio = municipioController.buscarPorCodigoDane(codigo);
            System.out.println("\n📋 MUNICIPIO:");
            System.out.println("   Código DANE: " + mpio.getCodigoDane());
            System.out.println("   Nombre: " + mpio.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarMunicipiosPorDepartamento() {
        System.out.print("\nCódigo DANE del departamento: ");
        String codigoDane = scanner.nextLine();
        try {
            List<Municipio> municipios = municipioController.buscarPorDepartamento(codigoDane);
            System.out.println("\n📋 MUNICIPIOS (" + municipios.size() + "):");
            for (Municipio m : municipios) {
                System.out.println("• " + m.getNombre() + " (" + m.getCodigoDane() + ")");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    // ===== GESTIÓN DE VEREDAS =====
    
    private void gestionarVeredas() {
        System.out.println("\n--- GESTIÓN DE VEREDAS ---");
        System.out.println("1. Crear vereda");
        System.out.println("2. Buscar por código DANE");
        System.out.println("3. Listar por municipio");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcion) {
            case 1:
                crearVereda();
                break;
            case 2:
                buscarVereda();
                break;
            case 3:
                listarVeredasPorMunicipio();
                break;
        }
    }
    
    private void crearVereda() {
        try {
            System.out.println("\n=== CREAR VEREDA ===");
            System.out.print("Código DANE: ");
            String codigoDane = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Código DANE del municipio: ");
            String codigoDaneMunicipio = scanner.nextLine();
            
            Vereda vereda = veredaController.crear(codigoDane, nombre, codigoDaneMunicipio);
            System.out.println("✅ Vereda creada: " + vereda.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarVereda() {
        System.out.print("\nCódigo DANE: ");
        String codigo = scanner.nextLine();
        try {
            Vereda vereda = veredaController.buscarPorCodigoDane(codigo);
            System.out.println("\n📋 VEREDA:");
            System.out.println("   Código DANE: " + vereda.getCodigoDane());
            System.out.println("   Nombre: " + vereda.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarVeredasPorMunicipio() {
        System.out.print("\nCódigo DANE del municipio: ");
        String codigoDane = scanner.nextLine();
        try {
            List<Vereda> veredas = veredaController.buscarPorMunicipio(codigoDane);
            System.out.println("\n📋 VEREDAS (" + veredas.size() + "):");
            for (Vereda v : veredas) {
                System.out.println("• " + v.getNombre() + " (" + v.getCodigoDane() + ")");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    // ===== GESTIÓN DE PREDIOS =====
    
    private void gestionarPredios() {
        System.out.println("\n--- GESTIÓN DE PREDIOS ---");
        System.out.println("1. Crear predio");
        System.out.println("2. Buscar por número predial");
        System.out.println("3. Listar por vereda");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcion) {
            case 1:
                crearPredio();
                break;
            case 2:
                buscarPredio();
                break;
            case 3:
                listarPrediosPorVereda();
                break;
        }
    }
    
    private void crearPredio() {
        try {
            System.out.println("\n=== CREAR PREDIO ===");
            System.out.print("Número predial: ");
            String numeroPredial = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Área total (hectáreas): ");
            float areaTotal = scanner.nextFloat();
            scanner.nextLine();
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            System.out.print("Código DANE de la vereda: ");
            String codigoDaneVereda = scanner.nextLine();
            System.out.print("ID del productor: ");
            String productorId = scanner.nextLine();
            
            Predio predio = predioController.crear(numeroPredial, nombre, areaTotal, 
                                                   direccion, codigoDaneVereda, productorId);
            System.out.println("✅ Predio creado: " + predio.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarPredio() {
        System.out.print("\nNúmero predial: ");
        String numero = scanner.nextLine();
        try {
            Predio predio = predioController.buscarPorNumeroPredial(numero);
            System.out.println("\n📋 PREDIO:");
            System.out.println("   Número predial: " + predio.getNumeroPredial());
            System.out.println("   Nombre: " + predio.getNombre());
            System.out.println("   Área: " + predio.getAreaTotal() + " ha");
            System.out.println("   Dirección: " + predio.getDireccion());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarPrediosPorVereda() {
        System.out.print("\nCódigo DANE de la vereda: ");
        String codigoDane = scanner.nextLine();
        try {
            List<Predio> predios = predioController.buscarPorVereda(codigoDane);
            System.out.println("\n📋 PREDIOS (" + predios.size() + "):");
            for (Predio p : predios) {
                System.out.println("• " + p.getNombre() + " - " + p.getAreaTotal() + " ha");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    // ===== GESTIÓN DE LUGARES DE PRODUCCIÓN =====
    
    private void gestionarLugaresProduccion() {
        System.out.println("\n--- GESTIÓN DE LUGARES DE PRODUCCIÓN ---");
        System.out.println("1. Crear lugar de producción");
        System.out.println("2. Buscar por código ICA");
        System.out.println("3. Listar por predio");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcion) {
            case 1:
                crearLugarProduccion();
                break;
            case 2:
                buscarLugarProduccion();
                break;
            case 3:
                listarLugaresProduccionPorPredio();
                break;
        }
    }
    
    private void crearLugarProduccion() {
        try {
            System.out.println("\n=== CREAR LUGAR DE PRODUCCIÓN ===");
            System.out.print("Código ICA: ");
            String codigoIca = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Área productiva (hectáreas): ");
            float areaProductiva = scanner.nextFloat();
            scanner.nextLine();
            System.out.print("Tipo de explotación: ");
            String tipoExplotacion = scanner.nextLine();
            System.out.print("Número predial del predio: ");
            String numeroPredialPredio = scanner.nextLine();
            
            LugarProduccion lugar = lugarProduccionController.crear(codigoIca, nombre, 
                                        areaProductiva, tipoExplotacion, numeroPredialPredio);
            System.out.println("✅ Lugar de producción creado: " + lugar.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarLugarProduccion() {
        System.out.print("\nCódigo ICA: ");
        String codigo = scanner.nextLine();
        try {
            LugarProduccion lugar = lugarProduccionController.buscarPorCodigoIca(codigo);
            System.out.println("\n📋 LUGAR DE PRODUCCIÓN:");
            System.out.println("   Código ICA: " + lugar.getCodigoIca());
            System.out.println("   Nombre: " + lugar.getNombre());
            System.out.println("   Área productiva: " + lugar.getAreaProductiva() + " ha");
            System.out.println("   Tipo: " + lugar.getTipoExplotacion());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarLugaresProduccionPorPredio() {
        System.out.print("\nNúmero predial: ");
        String numero = scanner.nextLine();
        try {
            List<LugarProduccion> lugares = lugarProduccionController.buscarPorPredio(numero);
            System.out.println("\n📋 LUGARES DE PRODUCCIÓN (" + lugares.size() + "):");
            for (LugarProduccion l : lugares) {
                System.out.println("• " + l.getNombre() + " - " + l.getAreaProductiva() + " ha");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    // ===== GESTIÓN DE LOTES =====
    
    private void gestionarLotes() {
        System.out.println("\n--- GESTIÓN DE LOTES ---");
        System.out.println("1. Crear lote");
        System.out.println("2. Buscar por código ICA");
        System.out.println("3. Listar por lugar de producción");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcion) {
            case 1:
                crearLote();
                break;
            case 2:
                buscarLote();
                break;
            case 3:
                listarLotesPorLugarProduccion();
                break;
        }
    }
    
    private void crearLote() {
        try {
            System.out.println("\n=== CREAR LOTE ===");
            System.out.print("Código ICA: ");
            String codigoIca = scanner.nextLine();
            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();
            System.out.print("Área (hectáreas): ");
            float area = scanner.nextFloat();
            scanner.nextLine();
            System.out.print("Código ICA del lugar de producción: ");
            String codigoIcaLugarProduccion = scanner.nextLine();
            
            Lote lote = loteController.crear(codigoIca, descripcion, area, codigoIcaLugarProduccion);
            System.out.println("✅ Lote creado con código: " + lote.getCodigoIca());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarLote() {
        System.out.print("\nCódigo ICA: ");
        String codigo = scanner.nextLine();
        try {
            Lote lote = loteController.buscarPorCodigoIca(codigo);
            System.out.println("\n📋 LOTE:");
            System.out.println("   Código ICA: " + lote.getCodigoIca());
            System.out.println("   Descripción: " + lote.getDescripcion());
            System.out.println("   Área: " + lote.getArea() + " ha");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarLotesPorLugarProduccion() {
        System.out.print("\nCódigo ICA del lugar de producción: ");
        String codigo = scanner.nextLine();
        try {
            List<Lote> lotes = loteController.buscarPorLugarProduccion(codigo);
            System.out.println("\n📋 LOTES (" + lotes.size() + "):");
            for (Lote l : lotes) {
                System.out.println("• " + l.getCodigoIca() + " - " + l.getArea() + " ha");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    // ===== GESTIÓN DE CULTIVOS =====
    
    private void gestionarCultivos() {
        System.out.println("\n--- GESTIÓN DE CULTIVOS ---");
        System.out.println("1. Crear cultivo");
        System.out.println("2. Buscar por código ICA");
        System.out.println("3. Listar por lote");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcion) {
            case 1:
                crearCultivo();
                break;
            case 2:
                buscarCultivo();
                break;
            case 3:
                listarCultivosPorLote();
                break;
        }
    }
    
    private void crearCultivo() {
        try {
            System.out.println("\n=== CREAR CULTIVO ===");
            System.out.print("Código ICA: ");
            String codigoIca = scanner.nextLine();
            System.out.print("Tipo de planta: ");
            String tipoPlanta = scanner.nextLine();
            System.out.print("Variedad: ");
            String variedad = scanner.nextLine();
            System.out.print("Número de plantas: ");
            int numeroPlantasSembradas = scanner.nextInt();
            System.out.print("Área cultivada (hectáreas): ");
            float areaCultivada = scanner.nextFloat();
            scanner.nextLine();
            System.out.print("Código ICA del lote: ");
            String codigoIcaLote = scanner.nextLine();
            
            Cultivo cultivo = cultivoController.crear(codigoIca, tipoPlanta, variedad, 
                                    numeroPlantasSembradas, areaCultivada, codigoIcaLote);
            System.out.println("✅ Cultivo creado: " + cultivo.getTipoPlanta());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarCultivo() {
        System.out.print("\nCódigo ICA: ");
        String codigo = scanner.nextLine();
        try {
            Cultivo cultivo = cultivoController.buscarPorCodigoIca(codigo);
            System.out.println("\n📋 CULTIVO:");
            System.out.println("   Código ICA: " + cultivo.getCodigoIca());
            System.out.println("   Tipo: " + cultivo.getTipoPlanta());
            System.out.println("   Variedad: " + cultivo.getVariedad());
            System.out.println("   Plantas: " + cultivo.getNumeroPlantasSembradas());
            System.out.println("   Área: " + cultivo.getAreaCultivada() + " ha");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarCultivosPorLote() {
        System.out.print("\nCódigo ICA del lote: ");
        String codigo = scanner.nextLine();
        try {
            List<Cultivo> cultivos = cultivoController.buscarPorLote(codigo);
            System.out.println("\n📋 CULTIVOS (" + cultivos.size() + "):");
            for (Cultivo c : cultivos) {
                System.out.println("• " + c.getTipoPlanta() + " (" + c.getVariedad() + ") - " + 
                                 c.getAreaCultivada() + " ha");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
