package main;

import presentacionC.*;
import modeloC.*;
import java.util.List;
import java.util.Scanner;

/**
 * Menú interactivo para el Microservicio de Usuarios.
 * Permite gestionar Productores y Asistentes Técnicos.
 */
public class MenuUsuarios {
    
    private ProductorController productorController;
    private AsistenteTecnicoController asistenteTecnicoController;
    private UsuarioController usuarioController;
    private Scanner scanner;
    
    public MenuUsuarios(ProductorController productorController, 
                       AsistenteTecnicoController asistenteTecnicoController,
                       UsuarioController usuarioController) {
        this.productorController = productorController;
        this.asistenteTecnicoController = asistenteTecnicoController;
        this.usuarioController = usuarioController;
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║    MENÚ - MICROSERVICIO DE USUARIOS        ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1. Gestión de Productores                 ║");
            System.out.println("║  2. Gestión de Asistentes Técnicos         ║");
            System.out.println("║  3. Consultar todos los usuarios           ║");
            System.out.println("║  0. Salir                                  ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    menuProductores();
                    break;
                case 2:
                    menuAsistentesTecnicos();
                    break;
                case 3:
                    consultarTodosUsuarios();
                    break;
                case 0:
                    System.out.println("\n¡Hasta luego!");
                    break;
                default:
                    System.out.println("\n❌ Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);
        
        scanner.close();
    }
    
    private void menuProductores() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PRODUCTORES ---");
            System.out.println("1. Crear productor");
            System.out.println("2. Buscar productor por ID");
            System.out.println("3. Listar todos los productores");
            System.out.println("4. Actualizar productor");
            System.out.println("5. Eliminar productor");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    crearProductor();
                    break;
                case 2:
                    buscarProductor();
                    break;
                case 3:
                    listarProductores();
                    break;
                case 4:
                    actualizarProductor();
                    break;
                case 5:
                    eliminarProductor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
    }
    
    private void menuAsistentesTecnicos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE ASISTENTES TÉCNICOS ---");
            System.out.println("1. Crear asistente técnico");
            System.out.println("2. Buscar asistente técnico por ID");
            System.out.println("3. Listar todos los asistentes técnicos");
            System.out.println("4. Actualizar asistente técnico");
            System.out.println("5. Eliminar asistente técnico");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    crearAsistenteTecnico();
                    break;
                case 2:
                    buscarAsistenteTecnico();
                    break;
                case 3:
                    listarAsistentesTecnicos();
                    break;
                case 4:
                    actualizarAsistenteTecnico();
                    break;
                case 5:
                    eliminarAsistenteTecnico();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
    }
    
    // ===== MÉTODOS PARA PRODUCTORES =====
    
    private void crearProductor() {
        try {
            System.out.println("\n=== CREAR PRODUCTOR ===");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            System.out.print("Tipo de explotación: ");
            String tipoExplotacion = scanner.nextLine();
            System.out.print("Número de predios: ");
            int numeroPredios = scanner.nextInt();
            scanner.nextLine();
            
            Productor productor = productorController.crear(nombre, email, telefono, direccion, 
                                                           tipoExplotacion, numeroPredios);
            System.out.println("✅ Productor creado exitosamente");
            System.out.println("   ID: " + productor.getId());
            System.out.println("   Nombre: " + productor.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarProductor() {
        System.out.print("\nIngrese el ID del productor: ");
        String id = scanner.nextLine();
        try {
            Productor productor = productorController.buscarPorId(id);
            System.out.println("\n📋 INFORMACIÓN DEL PRODUCTOR:");
            System.out.println("   ID: " + productor.getId());
            System.out.println("   Nombre: " + productor.getNombre());
            System.out.println("   Email: " + productor.getEmail());
            System.out.println("   Teléfono: " + productor.getTelefono());
            System.out.println("   Tipo explotación: " + productor.getTipoExplotacion());
            System.out.println("   Número de predios: " + productor.getNumeroPredios());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarProductores() {
        try {
            List<Productor> productores = productorController.listarTodos();
            System.out.println("\n📋 LISTA DE PRODUCTORES (" + productores.size() + "):");
            System.out.println("─────────────────────────────────────────────────");
            for (Productor p : productores) {
                System.out.println("• " + p.getNombre() + " (ID: " + p.getId() + ")");
                System.out.println("  Email: " + p.getEmail() + " | Predios: " + p.getNumeroPredios());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void actualizarProductor() {
        System.out.print("\nIngrese el ID del productor a actualizar: ");
        String id = scanner.nextLine();
        try {
            System.out.println("Deje en blanco los campos que no desea modificar:");
            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevo email: ");
            String email = scanner.nextLine();
            System.out.print("Nuevo teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Nuevo tipo de explotación: ");
            String tipoExplotacion = scanner.nextLine();
            System.out.print("Nuevo número de predios (0 para no cambiar): ");
            Integer numeroPredios = scanner.nextInt();
            scanner.nextLine();
            
            if (numeroPredios == 0) numeroPredios = null;
            
            Productor productor = productorController.actualizar(id, 
                nombre.isEmpty() ? null : nombre,
                email.isEmpty() ? null : email,
                telefono.isEmpty() ? null : telefono,
                tipoExplotacion.isEmpty() ? null : tipoExplotacion,
                numeroPredios);
                
            System.out.println("✅ Productor actualizado exitosamente");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void eliminarProductor() {
        System.out.print("\nIngrese el ID del productor a eliminar: ");
        String id = scanner.nextLine();
        System.out.print("¿Está seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            try {
                productorController.eliminar(id);
                System.out.println("✅ Productor eliminado exitosamente");
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
    
    // ===== MÉTODOS PARA ASISTENTES TÉCNICOS =====
    
    private void crearAsistenteTecnico() {
        try {
            System.out.println("\n=== CREAR ASISTENTE TÉCNICO ===");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            System.out.print("Especialidad: ");
            String especialidad = scanner.nextLine();
            System.out.print("Zona asignada: ");
            String zonaAsignada = scanner.nextLine();
            
            AsistenteTecnico asistente = asistenteTecnicoController.crear(nombre, email, telefono, 
                                                                          direccion, especialidad, zonaAsignada);
            System.out.println("✅ Asistente técnico creado exitosamente");
            System.out.println("   ID: " + asistente.getId());
            System.out.println("   Nombre: " + asistente.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void buscarAsistenteTecnico() {
        System.out.print("\nIngrese el ID del asistente técnico: ");
        String id = scanner.nextLine();
        try {
            AsistenteTecnico asistente = asistenteTecnicoController.buscarPorId(id);
            System.out.println("\n📋 INFORMACIÓN DEL ASISTENTE TÉCNICO:");
            System.out.println("   ID: " + asistente.getId());
            System.out.println("   Nombre: " + asistente.getNombre());
            System.out.println("   Email: " + asistente.getEmail());
            System.out.println("   Especialidad: " + asistente.getEspecialidad());
            System.out.println("   Zona asignada: " + asistente.getZonaAsignada());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void listarAsistentesTecnicos() {
        try {
            List<AsistenteTecnico> asistentes = asistenteTecnicoController.listarTodos();
            System.out.println("\n📋 LISTA DE ASISTENTES TÉCNICOS (" + asistentes.size() + "):");
            System.out.println("─────────────────────────────────────────────────");
            for (AsistenteTecnico a : asistentes) {
                System.out.println("• " + a.getNombre() + " (ID: " + a.getId() + ")");
                System.out.println("  Especialidad: " + a.getEspecialidad() + " | Zona: " + a.getZonaAsignada());
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void actualizarAsistenteTecnico() {
        System.out.print("\nIngrese el ID del asistente técnico a actualizar: ");
        String id = scanner.nextLine();
        try {
            System.out.println("Deje en blanco los campos que no desea modificar:");
            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevo email: ");
            String email = scanner.nextLine();
            System.out.print("Nuevo teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Nueva especialidad: ");
            String especialidad = scanner.nextLine();
            System.out.print("Nueva zona asignada: ");
            String zonaAsignada = scanner.nextLine();
            
            AsistenteTecnico asistente = asistenteTecnicoController.actualizar(id,
                nombre.isEmpty() ? null : nombre,
                email.isEmpty() ? null : email,
                telefono.isEmpty() ? null : telefono,
                especialidad.isEmpty() ? null : especialidad,
                zonaAsignada.isEmpty() ? null : zonaAsignada);
                
            System.out.println("✅ Asistente técnico actualizado exitosamente");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void eliminarAsistenteTecnico() {
        System.out.print("\nIngrese el ID del asistente técnico a eliminar: ");
        String id = scanner.nextLine();
        System.out.print("¿Está seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            try {
                asistenteTecnicoController.eliminar(id);
                System.out.println("✅ Asistente técnico eliminado exitosamente");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Operación cancelada");
        }
    }
    
    private void consultarTodosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioController.listarTodos();
            System.out.println("\n📋 TODOS LOS USUARIOS DEL SISTEMA (" + usuarios.size() + "):");
            System.out.println("─────────────────────────────────────────────────");
            for (Usuario u : usuarios) {
                String tipo = u instanceof Productor ? "PRODUCTOR" : "ASISTENTE TÉCNICO";
                System.out.println("• [" + tipo + "] " + u.getNombre() + " (ID: " + u.getId() + ")");
                System.out.println("  Email: " + u.getEmail() + " | Tel: " + u.getTelefono());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
