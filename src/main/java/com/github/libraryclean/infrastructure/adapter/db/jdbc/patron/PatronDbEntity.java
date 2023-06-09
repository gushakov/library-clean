/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.infrastructure.adapter.db.jdbc.patron;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/*
    References:
    ----------

    1. Spring Data JDBC, "MappedCollection" annotation: https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#jdbc.entity-persistence.custom-column-name
 */

@Data
@Table("patron")
public class PatronDbEntity {

    @Id
    @Column("patron_id")
    private String patronId;

    @Column("full_name")
    private String fullName;

    @Column("level")
    private String level;

    @MappedCollection(idColumn = "patron_id")
    private Set<HoldDbEntity> holds;

    @Version
    private Integer version;
}
