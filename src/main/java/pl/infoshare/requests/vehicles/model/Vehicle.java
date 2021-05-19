package pl.infoshare.requests.vehicles.model;

import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Value
public class Vehicle {
    private static AtomicInteger idGenerator = new AtomicInteger(1);

    Integer id = idGenerator.getAndIncrement();
    VehicleType type;
    BigDecimal mileage;
    String registrationNumber;
    @With
    LocalDate lastReviewDate;
    @With
    BigDecimal lastReviewMileage;
    String city;

    public static Vehicle bus(BigDecimal mileage, String registrationNumber, LocalDate lastReviewDate, BigDecimal lastReviewMileage, String city) {
        return new Vehicle(VehicleType.BUS, mileage, registrationNumber, lastReviewDate, lastReviewMileage, city);
    }

    public static Vehicle tram(BigDecimal mileage, String registrationNumber, LocalDate lastReviewDate, BigDecimal lastReviewMileage, String city) {
        return new Vehicle(VehicleType.TRAM, mileage, registrationNumber, lastReviewDate, lastReviewMileage, city);
    }

    public boolean needsReview() {
        var monthsSinceLastReview = ChronoUnit.MONTHS.between(lastReviewDate, LocalDate.now());
        var mileageSinceLastReview = mileage.subtract(lastReviewMileage);

        return monthsSinceLastReview > type.getMaxMonths() || mileageSinceLastReview.compareTo(type.getMaxMileage()) > 0;
    }
}
