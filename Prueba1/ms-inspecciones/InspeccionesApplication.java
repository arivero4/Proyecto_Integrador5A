import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Aplicación principal del Microservicio de Inspecciones.
 * Puerto: 8083
 * Expone APIs REST para gestión de inspecciones fitosanitarias, resultados técnicos y plagas.
 */
@SpringBootApplication(scanBasePackages = {"rest", "presentacionB", "negocioB", "persistenciaB", "modeloB"})
public class InspeccionesApplication {
    
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  Iniciando Microservicio de INSPECCIONES");
        System.out.println("  Puerto: 8083");
        System.out.println("  APIs REST disponibles en /api/*");
        System.out.println("==============================================");
        
        SpringApplication.run(InspeccionesApplication.class, args);
    }
    
    /**
     * Bean para realizar llamadas HTTP a otros microservicios
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
