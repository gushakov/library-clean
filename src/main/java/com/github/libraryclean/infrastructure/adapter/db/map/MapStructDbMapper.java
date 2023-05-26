package com.github.libraryclean.infrastructure.adapter.db.map;

import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.infrastructure.adapter.db.jdbc.catalog.CatalogEntryDbEntity;
import com.github.libraryclean.infrastructure.map.CommonConverters;
import com.github.libraryclean.infrastructure.map.IgnoreForMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CommonConverters.class})
public abstract class MapStructDbMapper implements DbMapper {

    protected abstract CatalogEntry map(CatalogEntryDbEntity dbEntity);

    @IgnoreForMapping
    @Override
    public CatalogEntry convert(CatalogEntryDbEntity dbEntity) {
        return map(dbEntity);
    }
}
