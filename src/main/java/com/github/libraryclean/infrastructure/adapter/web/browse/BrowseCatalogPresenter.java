package com.github.libraryclean.infrastructure.adapter.web.browse;

import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.core.usecase.browse.BrowseCatalogPresenterOutputPort;
import com.github.libraryclean.infrastructure.adapter.web.AbstractWebPresenter;
import com.github.libraryclean.infrastructure.adapter.web.LocalDispatcherServlet;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * Presenter for "browse catalog" use case.
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST)

public class BrowseCatalogPresenter extends AbstractWebPresenter implements BrowseCatalogPresenterOutputPort {
    public BrowseCatalogPresenter(LocalDispatcherServlet dispatcher, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        super(dispatcher, httpRequest, httpResponse);
    }

    @Override
    public void presentListOfCatalogEntriesAvailableForHolding(Set<CatalogEntry> entries) {
        presentModelAndView(Map.of("entries", entries), "catalog");
    }
}
