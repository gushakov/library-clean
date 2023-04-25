package com.github.libraryclean.infrastructure.adapter.db;

import com.github.libraryclean.core.model.MockModels;
import com.github.libraryclean.core.model.catalog.Book;
import com.github.libraryclean.core.model.catalog.BookInstance;
import com.github.libraryclean.core.model.catalog.Isbn;
import com.github.libraryclean.core.ports.db.PersistenceGatewayOutputPort;
import com.github.libraryclean.infrastucture.adapter.db.PersistenceGateway;
import com.github.libraryclean.infrastucture.adapter.db.map.MapStructDbMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@ComponentScan(basePackageClasses = {PersistenceGateway.class, MapStructDbMapper.class})
public class PersistenceGatewayTestIT {

    @Autowired
    private PersistenceGatewayOutputPort gatewayOps;

    @Test
    void obtain_book_from_db_by_isbn() {

        // given
        Isbn isbn = Isbn.of("0134494164");
        Book expectedBook = MockModels.aBook(isbn.getNumber());

        // when
        Book foundBook = gatewayOps.obtainBookByIsbn(isbn);

        // then
        sameBook(foundBook, expectedBook);

    }

    private void sameBook(Book book, Book anotherBook) {
        assertThat(book.getIsbn())
                .isEqualTo(anotherBook.getIsbn());
        assertThat(book.getTitle()).isEqualTo(anotherBook.getTitle());
        assertThat(book.getAuthor()).isEqualTo(anotherBook.getAuthor());
        assertThat(book.getVersion()).isEqualTo(anotherBook.getVersion());

        assertThat(book.getInstances()).hasSize(anotherBook.getInstances().size());
        book.getInstances().forEach(bookInstance ->
                assertThat(anotherBook.getInstances())
                        .extracting(BookInstance::getBookId, BookInstance::getIsbn, BookInstance::getType)
                        .contains(tuple(bookInstance.getBookId(), bookInstance.getIsbn(), bookInstance.getType()))
        );

    }

}
