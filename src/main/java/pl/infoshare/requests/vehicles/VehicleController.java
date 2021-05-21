package pl.infoshare.requests.vehicles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.infoshare.requests.vehicles.model.Vehicle;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleRepository vehicleRepository;
    private final VehicleFindService vehicleFindService;

    @RequestMapping("/vehicles")
    public List<Vehicle> findAllVehicles() {
        return vehicleFindService.findVehicles();
    }

    @PostMapping("/vehicles")
    public void createVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @DeleteMapping("/vehicles/{identifier}")
    public void deleteVehicle(@PathVariable int id) {
        vehicleRepository.delete(id);
    }
}
