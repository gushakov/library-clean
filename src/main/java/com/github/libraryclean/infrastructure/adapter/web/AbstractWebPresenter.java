package com.github.libraryclean.infrastructure.adapter.web;

import com.github.libraryclean.core.ports.ErrorHandlingOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Implements operations common to all Spring MVC presenters.
 *
 * @see LocalDispatcherServlet
 */
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractWebPresenter implements ErrorHandlingOutputPort {

    private final LocalDispatcherServlet dispatcher;
    private final HttpServletRequest httpRequest;
    private final HttpServletResponse httpResponse;

    @Override
    public void presentError(Throwable e) {

        // logs any unexpected errors
        log.error(e.getMessage(), e);

        // redirect to special error handling controller
        redirectError(e.getMessage());

    }

    protected void presentModelAndView(Map<String, Object> responseModel, String viewName) {
        final ModelAndView mav = new ModelAndView(viewName, responseModel);
        try {
            dispatcher.render(mav, httpRequest, httpResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void redirect(String path, Map<String, String> params) {
        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(path);
        params.forEach(uriBuilder::queryParam);
        final String uri = uriBuilder.toUriString();

        try {
            httpResponse.sendRedirect(httpResponse.encodeRedirectURL(uri));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void redirectError(String errorMessage) {
        try {
            httpRequest.getSession().setAttribute("errorMessage", errorMessage);
            httpResponse.sendRedirect("/error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}