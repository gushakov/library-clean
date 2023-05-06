/*
    COPYRIGHT DISCLAIMER
    --------------------

    The code in this file may be based on the original work from
    [ddd-by-examples/library](https://github.com/ddd-by-examples/library).

    Please see the original licence at
    https://github.com/ddd-by-examples/library/blob/master/LICENSE

    and the copyright disclaimer notice in "README.md" (in this repository).
 */

package com.github.libraryclean.core.model.patron;

import com.github.libraryclean.core.model.InvalidDomainObjectError;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

public class DaysTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void must_not_create_invalid_duration_in_days(int days) {
        // given
        // when
        Exception error = catchException(() -> Days.of(days));
        // then
        assertThat(error)
                .isInstanceOf(InvalidDomainObjectError.class);
    }
}
