package pl.infoshare.requests.vehicles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.infoshare.requests.vehicles.model.Vehicle;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VehicleFindService {

    private final VehicleRepository vehicleRepository;

    public List<Vehicle> findVehicles() {
        return vehicleRepository.findAll();
    }
}
