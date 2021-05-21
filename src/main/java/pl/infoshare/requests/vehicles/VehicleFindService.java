package pl.infoshare.requests.vehicles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.infoshare.requests.vehicles.model.Vehicle;
import pl.infoshare.requests.vehicles.model.VehicleSearch;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VehicleFindService {

    private final VehicleRepository vehicleRepository;

    public List<Vehicle> findVehicles(VehicleSearch vehicleSearch) {
        return vehicleRepository.findAll()
                .stream()
                .filter(vehicleSearch::hasCorrectCity)
                .filter(vehicleSearch::hasCorrectType)
                .filter(vehicleSearch::hasCorrectMileage)
                .collect(Collectors.toList());
    }

    public List<Vehicle> findVehiclesNeedingReview() {
        return vehicleRepository.findAll()
                .stream()
                .filter(Vehicle::needsReview)
                .collect(Collectors.toList());
    }
}
