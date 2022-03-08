package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;


public class FilterImplTest {
    List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());

    Filter filter = new FilterImpl(flights);

    List<Flight> beforeDepartureCurrent = filter.beforeCurrentTime();
    List<Flight> arrivalBeforeDeparture = filter.arrivalBeforeDeparture();
    List<Flight> differentLandTime = filter.differentLandTime();

    @Test
    public void beforeCurrentTime() {
        Assert.assertNotNull(flights);
        Assert.assertNotNull(beforeDepartureCurrent);
        Assertions.assertNotSame(flights, beforeDepartureCurrent);
        Assert.assertThat(beforeDepartureCurrent.size(),is(5));
    }

    @Test
    public void arrivalBeforeDeparture() {
        Assert.assertNotNull(flights);
        Assert.assertNotNull(arrivalBeforeDeparture);
        Assertions.assertNotSame(flights, arrivalBeforeDeparture);
        Assert.assertThat(arrivalBeforeDeparture.size(),is(5));
    }

    @Test
    public void differentLandTime() {
        Assert.assertNotNull(flights);
        Assert.assertNotNull(differentLandTime);
        Assertions.assertNotSame(flights, differentLandTime);
        Assert.assertThat(differentLandTime.size(),is(4));
    }
}