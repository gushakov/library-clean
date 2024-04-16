package com.github.libraryclean.infrastructure.config;

import com.github.libraryclean.core.ports.config.ConfigurationOutputPort;
import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import com.github.libraryclean.core.usecase.browse.BrowseCatalogInputPort;
import com.github.libraryclean.core.usecase.browse.BrowseCatalogPresenterOutputPort;
import com.github.libraryclean.core.usecase.browse.BrowseCatalogUseCase;
import com.github.libraryclean.core.usecase.hold.HoldBookInputPort;
import com.github.libraryclean.core.usecase.hold.HoldBookPresenterOutputPort;
import com.github.libraryclean.core.usecase.hold.HoldBookUseCase;
import com.github.libraryclean.core.usecase.profile.ConsultPatronProfileInputPort;
import com.github.libraryclean.core.usecase.profile.ConsultPatronProfilePresenterOutputPort;
import com.github.libraryclean.core.usecase.profile.ConsultPatronProfileUseCase;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Configures prototype beans for all use cases. Each use case is injected it with a presenter,
 * the persistence gateway, and the instances of any required secondary adapters.
 */
@Configuration
public class UseCaseConfig {

    // declares bean for "hold book" use case

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public HoldBookInputPort holdBookUseCase(HoldBookPresenterOutputPort presenter,
                                             PersistenceGatewayOutputPort gatewayOps) {

        return new HoldBookUseCase(presenter, gatewayOps, new ConfigurationOutputPort() {
        });

    }

    // additional use cases just for the completeness of the reference implementation

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public BrowseCatalogInputPort browseCatalogUseCase(BrowseCatalogPresenterOutputPort presenter,
                                                       PersistenceGatewayOutputPort gatewayOps) {
        return new BrowseCatalogUseCase(presenter, gatewayOps);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public ConsultPatronProfileInputPort consultPatronProfileUseCase(ConsultPatronProfilePresenterOutputPort presenter,
                                                                     PersistenceGatewayOutputPort gatewayOps) {
        return new ConsultPatronProfileUseCase(presenter, gatewayOps);
    }

}
