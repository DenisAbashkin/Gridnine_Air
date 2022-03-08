package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class FilterImpl implements Filter {
    private List<Flight> flights;

    public FilterImpl(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public List<Flight> beforeCurrentTime() {
        return flights.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> !segment.getDepartureDate().isBefore(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> arrivalBeforeDeparture() {
        return flights.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> !segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> differentLandTime() {
        return flights.stream().filter(
                flight -> !(flight.getSegments().size() > 1 && flight.getSegments().stream().reduce(ChronoUnit.HOURS.between(
                                flight.getSegments().get(0).getDepartureDate(),
                                flight.getSegments().get(flight.getSegments().size() - 1).getArrivalDate()
                        ),
                        (period, s) -> {
                            long temp = Math.abs(ChronoUnit.HOURS.between(s.getArrivalDate(), s.getDepartureDate()));
                            return period - temp;
                        },
                        Long::sum
                        )>2)).collect(Collectors.toList());
    }
}
