package pl.infoshare.requests.vehicles.model;

import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Value
public class Vehicle {
    @With
    Integer id;
    VehicleType type;
    BigDecimal mileage;
    String registrationNumber;
    @With
    LocalDate lastReviewDate;
    @With
    BigDecimal lastReviewMileage;
    String city;

    public boolean needsReview() {
        var monthsSinceLastReview = ChronoUnit.MONTHS.between(lastReviewDate, LocalDate.now());
        var mileageSinceLastReview = mileage.subtract(lastReviewMileage);

        return monthsSinceLastReview > type.getMaxMonths() || mileageSinceLastReview.compareTo(type.getMaxMileage()) > 0;
    }
}
