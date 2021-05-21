package pl.infoshare.requests.vehicles;

import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.requests.vehicles.model.PageRequest;
import pl.infoshare.requests.vehicles.model.Vehicle;
import pl.infoshare.requests.vehicles.model.VehicleSearch;
import pl.infoshare.requests.vehicles.model.VehicleUpdateRequest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class VehicleController {

    private static final String CITY_HEADER = "X-CITY";
    private static final String TOTAL_COUNT_HEADER = "X-TOTAL-COUNT";

    private final VehicleRepository vehicleRepository;
    private final VehicleFindService vehicleFindService;

    @GetMapping(value = "/vehicles")
    public ResponseEntity<List<Vehicle>> findAllVehicles(VehicleSearch search,
                                                         PageRequest pageRequest,
                                                         @RequestHeader(CITY_HEADER) @Nullable String city) {
        var foundVehicles = vehicleFindService.findVehicles(search.withCity(city));

        var pagedVehicles = foundVehicles.stream()
                .skip(pageRequest.getPage() * pageRequest.getPageSize())
                .limit(pageRequest.getPageSize())
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .header(TOTAL_COUNT_HEADER, String.valueOf(foundVehicles.size()))
                .body(pagedVehicles);
    }

    @GetMapping(value = "/vehicles", params = "needsReview")
    public ResponseEntity<List<Vehicle>> findAllVehicles() {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(Duration.of(1, ChronoUnit.MINUTES)).noTransform().mustRevalidate())
                .body(vehicleFindService.findVehiclesNeedingReview());
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
