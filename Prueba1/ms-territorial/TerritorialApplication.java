import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Aplicación principal del Microservicio Territorial.
 * Puerto: 8082
 * Expone APIs REST para gestión de estructura territorial y lugares de producción.
 */
@SpringBootApplication(scanBasePackages = {"rest", "presentacionA", "negocioA", "persistenciaA", "modeloA"})
public class TerritorialApplication {
    
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  Iniciando Microservicio TERRITORIAL");
        System.out.println("  Puerto: 8082");
        System.out.println("  APIs REST disponibles en /api/*");
        System.out.println("==============================================");
        
        SpringApplication.run(TerritorialApplication.class, args);
    }
    
    /**
     * Bean para realizar llamadas HTTP a otros microservicios
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
