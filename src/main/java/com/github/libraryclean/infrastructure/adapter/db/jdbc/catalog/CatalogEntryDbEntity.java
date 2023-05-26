package com.github.libraryclean.infrastructure.adapter.db.jdbc.catalog;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "catalog")
public class CatalogEntryDbEntity {

    @Id
    @Column("isbn")
    private String isbn;

    @Column("title")
    private String title;

    @Column("author")
    private String author;

    @Version
    private Integer version;

}
