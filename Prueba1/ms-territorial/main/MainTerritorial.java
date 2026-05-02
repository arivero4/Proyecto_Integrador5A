package main;

import presentacionA.*;
import java.util.Scanner;

/**
 * Clase principal del Microservicio Territorial.
 * Puerto: 8082
 * Gestiona la estructura territorial y lugares de producción agrícola.
 */
public class MainTerritorial {
    
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  MICROSERVICIO TERRITORIAL - Puerto 8082");
        System.out.println("==============================================");
        System.out.println();
        
        // Inicializar controladores
        DepartamentoController departamentoController = new DepartamentoController();
        MunicipioController municipioController = new MunicipioController();
        VeredaController veredaController = new VeredaController();
        PredioController predioController = new PredioController();
        LugarProduccionController lugarProduccionController = new LugarProduccionController();
        LoteController loteController = new LoteController();
        CultivoController cultivoController = new CultivoController();
        
        // Mostrar menú
        MenuTerritorial menu = new MenuTerritorial(
            departamentoController,
            municipioController,
            veredaController,
            predioController,
            lugarProduccionController,
            loteController,
            cultivoController
        );
        menu.mostrarMenu();
    }
}
