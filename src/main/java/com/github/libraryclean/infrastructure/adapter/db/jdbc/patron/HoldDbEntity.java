package com.github.libraryclean.infrastructure.adapter.db.jdbc.patron;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

/*
    References:
    ----------

    1. Supported types in Spring Data JDBC: https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#jdbc.entity-persistence.types

 */

@Data
@Table("hold")
public class HoldDbEntity {

    @Column("isbn")
    private String isbn;

    @Column("patron_id")
    private String patronId;

    @Column("start_date")
    private LocalDate startDate;

    @Column("duration")
    private Integer duration;

    @Column("date_completed")
    private LocalDate dateCompleted;

    @Column("date_canceled")
    private LocalDate dateCanceled;
}
