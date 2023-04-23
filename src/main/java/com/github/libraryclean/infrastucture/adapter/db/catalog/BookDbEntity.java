package com.github.libraryclean.infrastucture.adapter.db.catalog;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("books_catalog")
@Builder
public class BookDbEntity {

    @Id
    @Column
    private String isbn;

    @Column
    private String title;

    @Column
    private String  author;

    /*
        This is needed for Spring Data JDBC to track if an entity has already
        been persisted once.
     */

    @Version
    private Integer version;

}
