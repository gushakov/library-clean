package com.github.libraryclean.infrastucture.adapter.db;

import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PersistenceGateway implements PersistenceGatewayOutputPort {


}
