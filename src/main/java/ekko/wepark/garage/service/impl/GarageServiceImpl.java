package ekko.wepark.garage.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import ekko.wepark.garage.domain.Garage;
import ekko.wepark.garage.domain.dto.Location;
import ekko.wepark.garage.repository.GarageRepository;
import ekko.wepark.garage.service.GarageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GarageServiceImpl implements GarageService {
    @Autowired private GarageRepository garageRepository;
    private Logger logger = LoggerFactory.getLogger(GarageServiceImpl.class);

    GeoApiContext context = new GeoApiContext.Builder()
            .apiKey("AIzaSyDwpy-oLehekZ6AL8UfDCFzUu4aB7hPzvE")
            .build();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    GeocodingResult[] results = new GeocodingResult[0];

    private Location getGarageInLatlon(String address) {
        try {
            results = GeocodingApi.geocode(context, address).await();
        } catch (ApiException e) {
            logger.warn("findByAddress API Error: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            logger.warn("findByAddress interrupted error: " + e.getMessage());
        } catch (IOException e) {
            logger.warn("findByAddress io error: " + e.getMessage());
        }
        return new Location(results[0].geometry.location.lat, results[0].geometry.location.lng);
    }

    /**
     * @param address
     * @return a list of garages that matches with the given address
     */
    public List<Garage> findGarageByAddress(String address) {
        return garageRepository.findAll()
                .stream()
                .filter(garage -> {
                    String garageAddress = garage.getAddress().getAddress();
                    return containsAddress(address, garageAddress);
                })
                .collect(Collectors.toList());
    }

    public boolean containsAddress(String address, String garageAddress) {
        return garageAddress.contains(address);
    }

    @Override
    public List<Garage> findGarageByCurrentLocation(Location currentLocation, char distanceUnit, double distance) {
        return this.garageRepository.findAll()
                .stream()
                .filter(garage -> {
                    Location garageLatlon = garage.getAddress().getLatlon();
                    return isWithinDistance(distance, distanceUnit, currentLocation, garageLatlon);
                })
                .collect(Collectors.toList());
    }

    private boolean isWithinDistance(double kilometer, char distanceUnit, Location location1, Location location2) {
        double theta = location1.getLongitude() - location2.getLongitude();
        double dist = Math.sin(deg2rad(location1.getLatitude())) * Math.sin(deg2rad(location2.getLatitude())) + Math.cos(deg2rad(location1.getLatitude())) * Math.cos(deg2rad(location2.getLatitude())) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (distanceUnit == 'K') {
            dist = dist * 1.609344;
        } else if (distanceUnit == 'N') {
            dist = dist * 0.8684;
        } else {
            logger.warn("Not an available distance unit");
        }
        logger.info("Distance between two locations are: ", dist);
        return dist < kilometer;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
