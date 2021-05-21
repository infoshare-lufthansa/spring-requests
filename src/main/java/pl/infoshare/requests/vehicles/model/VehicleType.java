package pl.infoshare.requests.vehicles.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public enum VehicleType {
    BUS(12, BigDecimal.valueOf(30000)),
    TRAM(6, BigDecimal.valueOf(15000));

    private final Integer maxMonths;
    private final BigDecimal maxMileage;
}
