package pl.infoshare.requests.vehicles.model;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class VehicleUpdateRequest {
    LocalDate lastReview;
    BigDecimal lastReviewMileage;
}
