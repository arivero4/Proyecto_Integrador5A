package gestionpracticas.util;

/**
 * Script de prueba para verificar conectividad a Oracle 10g
 * Uso: java TestOracleConnection
 */
public class TestOracleConnection {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   Test de Conexión a Oracle 10g            ║");
        System.out.println("║   Puerto: 1522 (XE)                        ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("");

        // Test 1: Verificar driver
        System.out.println("[1/4] Verificando Oracle JDBC Driver...");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("  ✓ Driver Oracle cargado exitosamente");
        } catch (ClassNotFoundException e) {
            System.out.println("  ✗ ERROR: Driver no encontrado!");
            System.out.println("  Solución: Agrega ojdbc8.jar al CLASSPATH");
            return;
        }

        System.out.println("");

        // Test 2: Intentar conexión
        System.out.println("[2/4] Conectando a Oracle...");
        java.sql.Connection connection = null;
        try {
            String url = "jdbc:oracle:thin:@localhost:1522:XE";
            String user = "PROYECTO5A";
            String password = "PROYECTO5A";

            System.out.println("  URL: " + url);
            System.out.println("  Usuario: " + user);

            connection = java.sql.DriverManager.getConnection(url, user, password);

            System.out.println("  ✓ Conexión establecida!");
        } catch (java.sql.SQLException e) {
            System.out.println("  ✗ ERROR de conexión:");
            System.out.println("     " + e.getMessage());
            System.out.println("");
            System.out.println("  Verificar:");
            System.out.println("  - Oracle está corriendo: net start OracleServiceXE");
            System.out.println("  - Puerto 1522 está abierto");
            System.out.println("  - Credenciales: system/oracle");
            return;
        }

        System.out.println("");

        // Test 3: Obtener metadatos
        System.out.println("[3/4] Obteniendo información de la BD...");
        try {
            java.sql.DatabaseMetaData meta = connection.getMetaData();
            System.out.println("  ✓ Driver: " + meta.getDriverName());
            System.out.println("  ✓ Versión: " + meta.getDriverVersion());
            System.out.println("  ✓ BD: " + meta.getDatabaseProductName());
            System.out.println("  ✓ Versión BD: " + meta.getDatabaseProductVersion());
        } catch (java.sql.SQLException e) {
            System.out.println("  ✗ ERROR: " + e.getMessage());
            return;
        }

        System.out.println("");

        // Test 4: Ejecutar query
        System.out.println("[4/4] Ejecutando query de prueba...");
        try {
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT 1 FROM DUAL");
            if (rs.next()) {
                System.out.println("  ✓ Query ejecutada correctamente");
                System.out.println("  ✓ Resultado: " + rs.getInt(1));
            }
            rs.close();
            stmt.close();
        } catch (java.sql.SQLException e) {
            System.out.println("  ✗ ERROR: " + e.getMessage());
            return;
        }

        System.out.println("");

        // Test 5: Verificar tablas
        System.out.println("[5/5] Verificando tablas de Flyway...");
        try {
            java.sql.Statement stmt = connection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM user_tables WHERE table_name='FLYWAY_SCHEMA_HISTORY'");
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("  ✓ Tabla flyway_schema_history existe");
            } else {
                System.out.println("  ⚠ Tabla flyway_schema_history no existe (se creará en la primera ejecución)");
            }
            rs.close();
            stmt.close();
        } catch (java.sql.SQLException e) {
            System.out.println("  ⚠ No se pudo verificar: " + e.getMessage());
        }

        System.out.println("");

        // Cerrar conexión
        try {
            connection.close();
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║  ✓ TODO CORRECTO - Listo para usar!       ║");
            System.out.println("╚════════════════════════════════════════════╝");
        } catch (java.sql.SQLException e) {
            System.out.println("  ✗ ERROR al cerrar: " + e.getMessage());
        }
    }
}
