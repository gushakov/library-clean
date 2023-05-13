package com.github.libraryclean.core.ports.config;

import com.github.libraryclean.core.model.patron.Days;

public interface ConfigurationOutputPort {

   default Days closedEndedHoldDuration(){
        return Days.of(30);
    }

    default int maxNumberOverdueCheckoutsForHold() {
       return 2;
    }
}
