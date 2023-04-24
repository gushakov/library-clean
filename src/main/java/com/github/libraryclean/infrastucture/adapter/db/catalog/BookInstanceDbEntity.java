package com.github.libraryclean.infrastucture.adapter.db.catalog;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("book_instance")
@Data
@Builder
public class BookInstanceDbEntity {

    @Id
    private String id;

    private String isbn;

    @Column
    private BookType type;

}
