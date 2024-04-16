package com.github.libraryclean.infrastructure.adapter.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Utility (internal) controller used to display any errors.
 *
 * @see AbstractWebPresenter
 */
@Controller
public class ErrorController {

    @RequestMapping("/error")
    public String onError(@SessionAttribute(required = false) String errorMessage, Model model,
                          HttpServletRequest request) {

        model.addAttribute("errorMessage",
                Objects.requireNonNullElse(errorMessage, "Unknown error"));

        request.getSession().removeAttribute("errorMessage");

        return "error";
    }

}