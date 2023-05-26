package com.github.libraryclean.infrastructure.adapter.db.map;

import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.infrastructure.adapter.db.jdbc.catalog.CatalogEntryDbEntity;

public interface DbMapper {

    CatalogEntry convert(CatalogEntryDbEntity dbEntity);

}
