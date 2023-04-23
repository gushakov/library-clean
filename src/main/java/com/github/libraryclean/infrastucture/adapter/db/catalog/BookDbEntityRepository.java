package com.github.libraryclean.infrastucture.adapter.db.catalog;

import org.springframework.data.repository.CrudRepository;

public interface BookDbEntityRepository extends CrudRepository<BookDbEntity, String> {

}
