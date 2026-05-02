package com.example.usuarios.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSchemaInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseSchemaInitializer.class);
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) {
        addColumnaPasswordSiFalta();
    }

    private void addColumnaPasswordSiFalta() {
        String check = "SELECT COUNT(*) FROM USER_TAB_COLUMNS " +
                       "WHERE TABLE_NAME = 'T_USUARIOS' AND COLUMN_NAME = 'PASSWORD'";
        Integer count = jdbcTemplate.queryForObject(check, Integer.class);
        if (count != null && count == 0) {
            log.info("Agregando columna PASSWORD a T_USUARIOS...");
            jdbcTemplate.execute("ALTER TABLE T_USUARIOS ADD (PASSWORD VARCHAR2(255))");
            log.info("Columna PASSWORD agregada correctamente.");
        } else {
            log.info("Columna PASSWORD ya existe en T_USUARIOS.");
        }
    }
}
