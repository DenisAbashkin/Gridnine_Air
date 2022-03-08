package com.gridnine.testing;

import java.util.List;

public interface Filter {
    List<Flight> beforeCurrentTime();

    List<Flight> arrivalBeforeDeparture();

    List<Flight> differentLandTime();
}
