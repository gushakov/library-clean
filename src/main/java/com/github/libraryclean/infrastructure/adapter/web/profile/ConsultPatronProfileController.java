package com.github.libraryclean.infrastructure.adapter.web.profile;

import com.github.libraryclean.core.usecase.profile.ConsultPatronProfileInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for "consult patron's profile" use case.
 */
@Controller
@RequiredArgsConstructor
public class ConsultPatronProfileController {

    private final ApplicationContext applicationContext;

    @GetMapping("/patronHolds")
    @ResponseBody
    public void patronHolds(@RequestParam("patronId") String patronId) {
        consultPatronProfileUseCase().listCurrentHolds(patronId);
    }

    private ConsultPatronProfileInputPort consultPatronProfileUseCase() {
        return applicationContext.getBean(ConsultPatronProfileInputPort.class);
    }
}
