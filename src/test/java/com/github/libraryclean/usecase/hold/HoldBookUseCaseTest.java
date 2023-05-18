package com.github.libraryclean.usecase.hold;

import com.github.libraryclean.core.model.book.Book;
import com.github.libraryclean.core.model.book.BookType;
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.model.patron.Days;
import com.github.libraryclean.core.model.patron.Patron;
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

import java.time.LocalDate;
import java.util.Set;

import static com.github.libraryclean.core.model.LibraryDsl.anyDate;
import static com.github.libraryclean.core.model.book.BookDsl.anyBook;
import static com.github.libraryclean.core.model.catalog.CatalogDsl.anyIsbn;
import static com.github.libraryclean.core.model.patron.PatronDsl.anyPatronId;
import static com.github.libraryclean.core.model.patron.PatronDsl.anyRegularPatron;
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
        String patronId = anyPatronId().toString();
        String isbnNotInCatalog = anIsbnNotInCatalog();

        // when

        useCase.holdBook(patronId, isbnNotInCatalog, anyDate(), false);

        // then

        presentErrorOnAbsentCatalogEntryWithIsbn(isbnNotInCatalog);

        // and

        useCaseDidNotSucceed();
    }

    @Test
    void present_error_on_trying_to_put_hold_on_available_book() {

        // given
        HoldBookUseCase useCase = useCase();
        Patron existingRegularPatron = existingRegularPatron();
        String patronId = existingRegularPatron.getPatronId().getId();
        LocalDate holdStartDate = anyDate();
        Isbn isbn = isbnInCatalogWithAvailableCirculatingBookInstances(holdStartDate);

        // when

        useCase.holdBook(patronId, isbn.getNumber(), holdStartDate, false);

        // then

        presentErrorOnTryingToPutHoldOnAvailableBookWithIsbn(isbn);

        // and

        useCaseDidNotSucceed();

    }

    @Test
    void present_successful_put_on_hold_of_book_for_patron() {

        // given

        HoldBookUseCase useCase = useCase();
        Patron existingRegularPatron = existingRegularPatron();
        String patronId = existingRegularPatron.getPatronId().getId();
        LocalDate holdStartDate = anyDate();
        Isbn isbn = isbnInCatalogWithoutAnyAvailableCirculatingBookInstances(holdStartDate);
        maximumOverdueCheckOutsSetTo(2);
        durationForClosedEndedHoldSetToDays(30);

        // when

    }

    private void durationForClosedEndedHoldSetToDays(int days) {
        when(configOps.closedEndedHoldDuration())
                .thenReturn(Days.of(days));
    }

    private void maximumOverdueCheckOutsSetTo(int maxNumber) {
        when(configOps.maxNumberOverdueCheckoutsForHold())
                .thenReturn(maxNumber);
    }

    private void presentErrorOnTryingToPutHoldOnAvailableBookWithIsbn(Isbn isbn) {
        ArgumentCaptor<Isbn> isbnArg = ArgumentCaptor.forClass(Isbn.class);
        verify(presenter, times(1))
                .presentErrorOnTryingToPutHoldOnAvailableBook(isbnArg.capture());
        Assertions.assertThat(isbnArg.getValue()).isEqualTo(isbn);
    }

    private Isbn isbnInCatalogWithAvailableCirculatingBookInstances(LocalDate date) {
        Book book = anyBook();
        Isbn isbn = book.getIsbn();
        when(gatewayOps.existsInCatalog(isbn)).thenReturn(true);
        when(gatewayOps.findAvailableBooks(isbn, BookType.CIRCULATING, date))
                .thenReturn(Set.of(book));
        return isbn;
    }

    private Isbn isbnInCatalogWithoutAnyAvailableCirculatingBookInstances(LocalDate date) {
        Book book = anyBook();
        Isbn isbn = book.getIsbn();
        when(gatewayOps.existsInCatalog(isbn)).thenReturn(true);
        when(gatewayOps.findAvailableBooks(isbn, BookType.CIRCULATING, date))
                .thenReturn(Set.of());
        return isbn;
    }

    private Patron existingRegularPatron() {
        Patron patron = anyRegularPatron();
        when(gatewayOps.loadPatron(patron.getPatronId()))
                .thenReturn(patron);
        return patron;
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
