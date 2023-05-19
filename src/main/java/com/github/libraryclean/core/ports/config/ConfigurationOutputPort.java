/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.ports.config;

import com.github.libraryclean.core.model.patron.Days;

public interface ConfigurationOutputPort {

    default Days closedEndedHoldDuration() {
        return Days.of(30);
    }

    default int maxNumberOverdueCheckoutsForHold() {
        return 2;
    }
}
