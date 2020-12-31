package com.tts.capstone.transit.service;

import com.tts.capstone.transit.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TransitService {
    @Value("${TTS_CAPSTONE_TRANSIT_ITSMARTA_URL}")
    public String transitUrl;

    @Value("${TTS_CAPSTONE_TRANSIT_GEOCODE_URL}")
    public String geocodingUrl;

    @Value("${TTS_CAPSTONE_TRANSIT_DISTANCE_URL}")
    public String distanceUrl;

    @Value("${TTS_CAPSTONE_GOOGLE_KEY}")
    public String googleApiKey;

    private List<Bus> getBuses() {
        RestTemplate restTemplate = new RestTemplate();
        Bus[] buses = restTemplate.getForObject(transitUrl, Bus[].class);
        return Arrays.asList(buses);
    }

    private Location getCoordinates(String description) {
        description = description.replace(" ", "+");
        String url = geocodingUrl + description + "+GA&key=" + googleApiKey;
        RestTemplate restTemplate = new RestTemplate();
        GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class);
        return response.results.get(0).geometry.location;
    }

    private double getDistance(Location origin, Location destination) {
        String url = distanceUrl + "origins=" + origin.lat + "," + origin.lng + "&destinations=" + destination.lat + "," + destination.lng + "&key=" + googleApiKey;
        RestTemplate restTemplate = new RestTemplate();
        DistanceResponse response = restTemplate.getForObject(url, DistanceResponse.class);
        return response.rows.get(0).elements.get(0).distance.value * 0.000621371;
    }

    public List<Bus> getNearbyBuses(BusRequest request) {
        List<Bus> allBuses = this.getBuses();
        Location personLocation = this.getCoordinates(request.address + " " + request.city);
        List<Bus> nearbyBuses = new ArrayList<>();
        for (Bus bus : allBuses) {
            Location busLocation = new Location();
            busLocation.lat = bus.LATITUDE;
            busLocation.lng = bus.LONGITUDE;
            double latDistance = Double.parseDouble(busLocation.lat) - Double.parseDouble(personLocation.lat);
            double lngDistance = Double.parseDouble(busLocation.lng) - Double.parseDouble(personLocation.lng);
            if (Math.abs(latDistance) <= 0.02 && Math.abs(lngDistance) <= 0.02) {
                double distance = getDistance(busLocation, personLocation);
                if (distance <= 10) {
                    bus.distance = (double) Math.round(distance * 100) / 100;
                    nearbyBuses.add(bus);
                }
            }
        }
        Collections.sort(nearbyBuses, new BusComparator());
        return nearbyBuses;
    }

    public Location getPersonLocation(BusRequest request) {
        return this.getCoordinates(request.address + " " + request.city);
    }
}
