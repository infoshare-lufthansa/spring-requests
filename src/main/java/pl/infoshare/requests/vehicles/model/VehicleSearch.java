package pl.infoshare.requests.vehicles.model;

import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.util.Objects;

@Value
public class VehicleSearch {
    VehicleType type;
    BigDecimal minMileage;
    @With
    String city;

    public boolean hasCorrectType(Vehicle vehicle) {
        if (Objects.isNull(type)) {
            return true;
        }

        return vehicle.getType() == type;
    }

    public boolean hasCorrectMileage(Vehicle vehicle) {
        if (Objects.isNull(minMileage)) {
            return true;
        }

        return vehicle.getMileage().compareTo(minMileage) >= 0;
    }

    public boolean hasCorrectCity(Vehicle vehicle) {
        if (Objects.isNull(city)) {
            return true;
        }

        return vehicle.getCity().equals(city);
    }
}
