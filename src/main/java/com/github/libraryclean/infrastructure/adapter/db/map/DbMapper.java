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
import com.github.libraryclean.infrastructure.adapter.db.jdbc.catalog.CatalogEntryDbEntity;
import com.github.libraryclean.infrastructure.adapter.db.jdbc.patron.HoldDbEntity;

public interface DbMapper {

    CatalogEntry convert(CatalogEntryDbEntity dbEntity);

    Hold convert(HoldDbEntity dbEntity);

}
