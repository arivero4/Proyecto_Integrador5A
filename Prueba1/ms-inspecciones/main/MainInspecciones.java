package main;

import presentacionB.*;
import java.util.Scanner;

/**
 * Clase principal del Microservicio de Inspecciones.
 * Puerto: 8083
 * Gestiona inspecciones fitosanitarias, resultados técnicos y plagas.
 */
public class MainInspecciones {
    
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  MICROSERVICIO DE INSPECCIONES - Puerto 8083");
        System.out.println("==================================================");
        System.out.println();
        
        // Inicializar controladores
        InspeccionFitosanitariaController inspeccionController = new InspeccionFitosanitariaController();
        ResultadoTecnicoController resultadoController = new ResultadoTecnicoController();
        PlagaController plagaController = new PlagaController();
        
        // Mostrar menú
        MenuInspecciones menu = new MenuInspecciones(
            inspeccionController,
            resultadoController,
            plagaController
        );
        menu.mostrarMenu();
    }
}
