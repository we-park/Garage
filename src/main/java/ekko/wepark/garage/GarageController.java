package ekko.wepark.garage;

import ekko.wepark.garage.domain.Garage;
import ekko.wepark.garage.domain.dto.Location;
import ekko.wepark.garage.message.garage.commands.CreateGarage;
import ekko.wepark.garage.message.garage.commands.DeleteGarage;
import ekko.wepark.garage.message.garage.commands.UpdateGarage;
import ekko.wepark.garage.message.size.commands.UpdateSize;
import ekko.wepark.garage.repository.GarageRepository;
import ekko.wepark.garage.service.impl.GarageServiceImpl;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/garage")
public class GarageController {
    @Autowired
    private GarageServiceImpl garageService;

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void createGarage(@RequestBody CreateGarage createGarage) {
        commandGateway.sendAndWait(createGarage);
    }

    @PutMapping
    @Transactional
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateGarage(@RequestBody UpdateGarage updateGarage) {
        commandGateway.sendAndWait(updateGarage);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteGarage(@RequestBody DeleteGarage deleteGarage) {
        commandGateway.send(deleteGarage);
    }

    @PutMapping("/size")
    @Transactional
    public void updateSize(@RequestBody UpdateSize updateSize) {
        commandGateway.sendAndWait(updateSize);
    }

    @GetMapping("/zip/{zip}")
    public Mono<List<Garage>> findByZip(@PathVariable(value = "zip") String zip) {
        List<Garage> garages = garageRepository.findByZip(zip);
        return Mono.just(garages);
    }

    @GetMapping("/address")
    public Mono<List<Garage>> findGaragesByAddress(@RequestBody String address) {
        List<Garage> garages = garageService.findGarageByAddress(address);
        return Mono.just(garages);
    }

    @GetMapping("/current")
    public Mono<List<Garage>> findGarageByCurrentLocation(@RequestBody Location location,
                                                    @RequestParam(value = "distanceUnit") char distanceUnit,
                                                    @RequestParam(value = "distance") double distance) {
        List<Garage> garages = this.garageService.findGarageByCurrentLocation(location, distanceUnit, distance);
        return Mono.just(garages);
    }

    @GetMapping("/{userId}")
    public Mono<List<Garage>> findAllGaragesByUserId(@PathVariable(value = "userId") String userId) {
        List<Garage> garages = this.garageRepository.findAllByUserId(userId);
        return Mono.just(garages);
    }

    @GetMapping("/{garageId}")
    public Mono<Garage> findGarageById(@PathVariable(value = "garageId") String garageId) {
        Optional<Garage> garage = this.garageRepository.findById(garageId);
        return Mono.just(garage.get());
    }

    @ExceptionHandler(AggregateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
    }
}
