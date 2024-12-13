package com.github.libraryclean.infrastructure.adapter.web.hold;

import com.github.libraryclean.core.usecase.hold.HoldBookInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

/**
 * This is the controller executing the main use case we are interested in -- "hold book" use case.
 * For this example we simply inject the authenticated user so that we can use the username as the
 * corresponding patron's ID sent to the use case.
 *
 * @see HoldBookPresenter
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class HoldBookController {

    private final ApplicationContext appContext;

    @PostMapping("/holdBook")
    @ResponseBody
    public void holdBook(@RequestParam("isbn") String isbn, Authentication authentication) {
        log.debug("Executing POST for book hold with isbn: {}, authentication: {}", isbn, authentication);

        /*
            UPDATE, 13.12.2024
            Moved security related operation to secondary adapter behind
            "com.github.libraryclean.core.ports.security.SecurityOperationsOutputPort"
            used from the use case.
        */

        // execute the use case to place a close-ended hold on the corresponding
        // catalog entry starting now
        holdBookUseCase().holdBook(isbn, LocalDate.now(), false);

    }

    /**
     * Return a fully-wired instance of {@link HoldBookInputPort} use case (prototype bean).
     *
     * @return use case bean
     */
    public HoldBookInputPort holdBookUseCase() {
        return appContext.getBean(HoldBookInputPort.class);
    }

}
