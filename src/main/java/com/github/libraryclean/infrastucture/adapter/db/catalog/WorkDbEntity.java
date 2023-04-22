package com.github.libraryclean.infrastucture.adapter.db.catalog;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("works_catalog")
@Builder
public class WorkDbEntity {

    @Id
    @Column
    private String isbn;

    @Column
    private String title;

    @Column
    private String  author;

    @Column("copies")
    private int numOfCopies;

    @Version
    private Integer version;

}
