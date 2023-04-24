package com.github.libraryclean.infrastucture.adapter.db.catalog;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Data
@Table("book")
@Builder
public class BookDbEntity {

    @Id
    @Column
    private String isbn;

    @Column
    private String title;

    @Column
    private String author;

    @MappedCollection(idColumn = "isbn")
    private Set<BookInstanceDbEntity> instances;

    /*
        This is needed for Spring Data JDBC to track if an entity has already
        been persisted once.
     */

    @Version
    private Integer version;

}
