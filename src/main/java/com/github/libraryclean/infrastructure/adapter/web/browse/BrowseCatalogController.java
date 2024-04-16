package com.github.libraryclean.infrastructure.adapter.web.browse;

import com.github.libraryclean.core.usecase.browse.BrowseCatalogInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for "browse catalog" use case.
 */
@Controller
@RequiredArgsConstructor
public class BrowseCatalogController {

    private final ApplicationContext appContext;

    @GetMapping
    @ResponseBody
    public void catalog() {
        browseCatalogUseCase().browseCatalogEntries();
    }

    public BrowseCatalogInputPort browseCatalogUseCase() {
        return appContext.getBean(BrowseCatalogInputPort.class);
    }

}
