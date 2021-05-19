package pl.infoshare.requests.vehicles;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import pl.infoshare.requests.vehicles.model.Vehicle;
import pl.infoshare.requests.vehicles.model.VehicleUpdateRequest;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static pl.infoshare.requests.vehicles.model.Vehicle.bus;
import static pl.infoshare.requests.vehicles.model.Vehicle.tram;

@Repository
public class VehicleRepository {

    private final List<Vehicle> vehicles = new ArrayList<>();

    @PostConstruct
    void init() {
        vehicles.add(bus(BigDecimal.valueOf(100000), "GDA001", LocalDate.of(2021, Month.MARCH, 1), BigDecimal.valueOf(90000), "Gdańsk"));
        vehicles.add(bus(BigDecimal.valueOf(15000), "GDA002", LocalDate.of(2020, Month.JANUARY, 20), BigDecimal.valueOf(10000), "Gdańsk"));
        vehicles.add(bus(BigDecimal.valueOf(150000), "GDA003", LocalDate.of(2021, Month.MARCH, 1), BigDecimal.valueOf(110000), "Gdańsk"));
        vehicles.add(tram(BigDecimal.valueOf(200000.23), "GDA004", LocalDate.of(2021, Month.FEBRUARY, 28), BigDecimal.valueOf(190000.11), "Gdańsk"));
        vehicles.add(tram(BigDecimal.valueOf(210000), "GDA005", LocalDate.of(2021, Month.MARCH, 1), BigDecimal.valueOf(195000), "Gdańsk"));
        vehicles.add(tram(BigDecimal.valueOf(11000), "GDA006", LocalDate.of(2021, Month.MAY, 1), BigDecimal.valueOf(1000), "Gdańsk"));
        vehicles.add(bus(BigDecimal.valueOf(100000), "WAW001", LocalDate.of(2021, Month.MARCH, 1), BigDecimal.valueOf(90000), "Warszawa"));
        vehicles.add(tram(BigDecimal.valueOf(100000), "WAW002", LocalDate.of(2021, Month.MARCH, 1), BigDecimal.valueOf(90000), "Warszawa"));
    }

    public List<Vehicle> findAll() {
        return new ArrayList<>(vehicles);
    }

    public void save(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void update(Integer id, VehicleUpdateRequest updateRequest) {
        var updatedVehicle = vehicles.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .map(v -> v.withLastReviewMileage(updateRequest.getLastReviewMileage()).withLastReviewDate(updateRequest.getLastReview()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        vehicles.replaceAll(v -> v.getId().equals(updatedVehicle.getId()) ? updatedVehicle : v);
    }

    public void delete(Integer id) {
        vehicles.removeIf(v -> v.getId().equals(id));
    }
}
