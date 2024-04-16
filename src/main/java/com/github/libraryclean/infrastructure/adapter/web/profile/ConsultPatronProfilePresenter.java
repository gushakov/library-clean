package com.github.libraryclean.infrastructure.adapter.web.profile;

import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.core.model.patron.PatronId;
import com.github.libraryclean.core.usecase.profile.ConsultPatronProfilePresenterOutputPort;
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
 * Presenter for "consult patron's catalog" use case.
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST)
public class ConsultPatronProfilePresenter extends AbstractWebPresenter implements ConsultPatronProfilePresenterOutputPort {

    public ConsultPatronProfilePresenter(LocalDispatcherServlet dispatcher, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        super(dispatcher, httpRequest, httpResponse);
    }

    @Override
    public void presentCurrentHoldsOfPatron(PatronId patronId, Set<CatalogEntry> holdsEntries) {
        presentModelAndView(Map.of("patronId", patronId.getId(),
                "holdsEntries", holdsEntries), "patron-profile");
    }

}
