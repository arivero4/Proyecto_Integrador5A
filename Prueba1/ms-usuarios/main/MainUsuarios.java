package main;

import presentacionC.*;
import java.util.Scanner;

/**
 * Clase principal del Microservicio de Usuarios.
 * Puerto: 8081
 * Gestiona usuarios del sistema (Productores y Asistentes Técnicos).
 */
public class MainUsuarios {
    
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  MICROSERVICIO DE USUARIOS - Puerto 8081");
        System.out.println("==============================================");
        System.out.println();
        
        // Inicializar controladores
        ProductorController productorController = new ProductorController();
        AsistenteTecnicoController asistenteTecnicoController = new AsistenteTecnicoController();
        UsuarioController usuarioController = new UsuarioController();
        
        // Mostrar menú
        MenuUsuarios menu = new MenuUsuarios(productorController, asistenteTecnicoController, usuarioController);
        menu.mostrarMenu();
    }
}
