package com.github.libraryclean.infrastructure.adapter.db;

import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import com.github.libraryclean.infrastucture.adapter.db.PersistenceGateway;
import com.github.libraryclean.infrastucture.adapter.db.map.MapStructDbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@ComponentScan(basePackageClasses = {PersistenceGateway.class, MapStructDbMapper.class})
public class PersistenceGatewayTestIT {

    @Autowired
    private PersistenceGatewayOutputPort gatewayOps;


}
