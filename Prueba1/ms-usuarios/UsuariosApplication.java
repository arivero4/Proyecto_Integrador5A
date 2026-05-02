import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Aplicación principal del Microservicio de Usuarios.
 * Puerto: 8081
 * Expone APIs REST para gestión de Productores y Asistentes Técnicos.
 */
@SpringBootApplication(scanBasePackages = {"rest", "presentacionC", "negocioC", "persistenciaC", "modeloC"})
public class UsuariosApplication {
    
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  Iniciando Microservicio de USUARIOS");
        System.out.println("  Puerto: 8081");
        System.out.println("  APIs REST disponibles en /api/*");
        System.out.println("==============================================");
        
        SpringApplication.run(UsuariosApplication.class, args);
    }
    
    /**
     * Bean para realizar llamadas HTTP a otros microservicios
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
