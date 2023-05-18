package com.github.libraryclean.usecase.hold;

import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.ports.config.ConfigurationOutputPort;
import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import com.github.libraryclean.core.usecase.hold.HoldBookPresenterOutputPort;
import com.github.libraryclean.core.usecase.hold.HoldBookUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.libraryclean.core.model.LibraryDsl.anyDate;
import static com.github.libraryclean.core.model.catalog.CatalogDsl.anyIsbn;
import static com.github.libraryclean.core.model.patron.PatronDsl.anyPatronId;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HoldBookUseCaseTest {

    @Mock
    private HoldBookPresenterOutputPort presenter;

    @Mock
    private PersistenceGatewayOutputPort gatewayOps;

    @Mock
    private ConfigurationOutputPort configOps;

    @Test
    void present_error_on_absent_catalog_entry() {

        // given

        HoldBookUseCase useCase = useCase();
        String patronId = anyPatronIdString();
        String isbn = anIsbnNotInCatalog();

        // when

        useCase.holdBook(patronId, isbn, anyDate(), false);

        // then

        presentErrorOnAbsentCatalogEntryWithIsbn(isbn);

        // and

        useCaseDidNotSucceed();
    }

    private static String anyPatronIdString() {
        return anyPatronId().getId();
    }

    private void useCaseDidNotSucceed() {
        verify(presenter, times(0))
                .presentSuccessfulPutOnHoldOfBookForPatron(any());
    }

    private void presentErrorOnAbsentCatalogEntryWithIsbn(String isbn) {
        ArgumentCaptor<Isbn> isbnArg = ArgumentCaptor.forClass(Isbn.class);
        verify(presenter, times(1))
                .presentErrorOnAbsentCatalogEntry(isbnArg.capture());
        Assertions.assertThat(isbnArg.getValue().getNumber()).isEqualTo(isbn);
    }

    private HoldBookUseCase useCase() {
        return new HoldBookUseCase(presenter, gatewayOps, configOps);
    }

    private String anIsbnNotInCatalog() {
        Isbn isbn = anyIsbn();
        when(gatewayOps.existsInCatalog(isbn)).thenReturn(false);
        return isbn.getNumber();
    }
}
