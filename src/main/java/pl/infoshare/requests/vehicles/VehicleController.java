package pl.infoshare.requests.vehicles;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.requests.vehicles.model.Vehicle;
import pl.infoshare.requests.vehicles.model.VehicleUpdateRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleRepository vehicleRepository;
    private final VehicleFindService vehicleFindService;

    @GetMapping("/vehicles")
    public List<Vehicle> findAllVehicles() {
        return vehicleFindService.findVehicles();
    }

    @PostMapping("/vehicles")
    @ResponseStatus(HttpStatus.CREATED)
    public void createVehicle(@RequestBody Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @PutMapping("/vehicles/{reg:[A-Z0-9]+}-{cit:[A-Za-z]+}-{id}")
    public void updateVehicle(@PathVariable int id, @RequestBody VehicleUpdateRequest vehicleUpdate) {
        vehicleRepository.update(id, vehicleUpdate);
    }

    @DeleteMapping("/vehicles/{id}")
    public void deleteVehicle(@PathVariable int id) {
        vehicleRepository.delete(id);
    }
}
