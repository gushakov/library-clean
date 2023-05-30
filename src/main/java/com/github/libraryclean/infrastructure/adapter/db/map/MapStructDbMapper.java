/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.infrastructure.adapter.db.map;

import com.github.libraryclean.core.model.catalog.CatalogEntry;
import com.github.libraryclean.core.model.patron.Hold;
import com.github.libraryclean.core.model.patron.Patron;
import com.github.libraryclean.infrastructure.adapter.db.jdbc.catalog.CatalogEntryDbEntity;
import com.github.libraryclean.infrastructure.adapter.db.jdbc.patron.HoldDbEntity;
import com.github.libraryclean.infrastructure.adapter.db.jdbc.patron.PatronDbEntity;
import com.github.libraryclean.infrastructure.map.IgnoreForMapping;
import com.github.libraryclean.infrastructure.map.MapStructConverters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MapStructConverters.class})
public abstract class MapStructDbMapper implements DbMapper {

    protected abstract CatalogEntry map(CatalogEntryDbEntity dbEntity);

    protected abstract Hold map(HoldDbEntity dbEntity);

    @Mapping(target = "checkOuts", ignore = true)
    protected abstract Patron map(PatronDbEntity patronDbEntity);

    @IgnoreForMapping
    @Override
    public CatalogEntry convert(CatalogEntryDbEntity dbEntity) {
        return map(dbEntity);
    }

    @IgnoreForMapping
    @Override
    public Hold convert(HoldDbEntity dbEntity) {
        return map(dbEntity);
    }

    @IgnoreForMapping
    @Override
    public Patron convert(PatronDbEntity patronDbEntity) {
        return map(patronDbEntity);
    }
}
