package com.github.libraryclean.infrastructure.adapter.web;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Overrides {@linkplain DispatcherServlet} which allows us to call Spring MVC view resolution from presenters
 * (instead of controllers).
 *
 * @see AbstractWebPresenter
 */
public class LocalDispatcherServlet extends DispatcherServlet {

    @Override
    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.render(mv, request, response);
    }

}