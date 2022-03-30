package pl.infoshare.requests.vehicles;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import pl.infoshare.requests.vehicles.model.Vehicle;
import pl.infoshare.requests.vehicles.model.VehicleType;
import pl.infoshare.requests.vehicles.model.VehicleUpdateRequest;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class VehicleRepository {
    private final static AtomicInteger idGenerator = new AtomicInteger(1);
    private final List<Vehicle> vehicles = new ArrayList<>();

    @PostConstruct
    void init() {
        vehicles.add(new Vehicle(idGenerator.getAndIncrement(), VehicleType.BUS, BigDecimal.valueOf(100000), "GTC001", LocalDate.of(2021, Month.MARCH, 1), BigDecimal.valueOf(90000), "Tczew"));
        vehicles.add(new Vehicle(idGenerator.getAndIncrement(), VehicleType.BUS, BigDecimal.valueOf(15000), "GTC002", LocalDate.of(2020, Month.JANUARY, 20), BigDecimal.valueOf(10000), "Tczew"));
        vehicles.add(new Vehicle(idGenerator.getAndIncrement(), VehicleType.BUS, BigDecimal.valueOf(150000), "GTC003", LocalDate.of(2021, Month.MARCH, 1), BigDecimal.valueOf(110000), "Tczew"));
        vehicles.add(new Vehicle(idGenerator.getAndIncrement(), VehicleType.TRAM, BigDecimal.valueOf(200000.23), "GTC004", LocalDate.of(2021, Month.FEBRUARY, 28), BigDecimal.valueOf(190000.11), "Tczew"));
        vehicles.add(new Vehicle(idGenerator.getAndIncrement(), VehicleType.TRAM, BigDecimal.valueOf(210000), "GTC005", LocalDate.of(2020, Month.OCTOBER, 1), BigDecimal.valueOf(195000), "Tczew"));
        vehicles.add(new Vehicle(idGenerator.getAndIncrement(), VehicleType.TRAM, BigDecimal.valueOf(11000), "GTC006", LocalDate.of(2021, Month.MAY, 1), BigDecimal.valueOf(1000), "Tczew"));
        vehicles.add(new Vehicle(idGenerator.getAndIncrement(), VehicleType.BUS, BigDecimal.valueOf(100000), "WAW001", LocalDate.of(2021, Month.MARCH, 1), BigDecimal.valueOf(90000), "Warszawa"));
        vehicles.add(new Vehicle(idGenerator.getAndIncrement(), VehicleType.TRAM, BigDecimal.valueOf(100000), "WAW002", LocalDate.of(2021, Month.MARCH, 1), BigDecimal.valueOf(60000), "Warszawa"));
    }

    public List<Vehicle> findAll() {
        return new ArrayList<>(vehicles);
    }

    public void save(Vehicle vehicle) {
        vehicles.add(vehicle.withId(idGenerator.getAndIncrement()));
    }

    public void update(Integer id, VehicleUpdateRequest updateRequest) {
        var updatedVehicle = vehicles.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .map(v -> v.withLastReviewMileage(updateRequest.getLastReviewMileage()).withLastReviewDate(updateRequest.getLastReview()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        vehicles.replaceAll(v -> v.getId().equals(updatedVehicle.getId()) ? updatedVehicle : v);
    }

    public boolean delete(Integer id) {
        return vehicles.removeIf(v -> v.getId().equals(id));
    }
}
