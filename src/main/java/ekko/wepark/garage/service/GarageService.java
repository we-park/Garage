package ekko.wepark.garage.service;

import ekko.wepark.garage.domain.Garage;
import ekko.wepark.garage.domain.dto.Location;

import java.util.List;

public interface GarageService {

    List<Garage> findGarageByAddress(String address);

    List<Garage> findGarageByCurrentLocation(Location currentLocation, char distanceUnit, double distance);
}
