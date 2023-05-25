package com.github.libraryclean.infrastructure.adapter.db;

import com.github.libraryclean.infrastructure.LibraryCleanApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {LibraryCleanApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PersistenceGatewayTestIT {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    @Test
    void process_flyway_scripts() {

        List<Map<String, Object>> maps = jdbcOperations.queryForList("select * from catalog", Map.of());
        System.out.println(maps.size());

        maps.get(0).values().forEach(System.out::println);

    }
}
