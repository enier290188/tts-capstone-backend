package com.tts.capstone.transit.controller;

import com.tts.capstone.transit.model.Bus;
import com.tts.capstone.transit.model.BusRequest;
import com.tts.capstone.transit.model.Location;
import com.tts.capstone.transit.service.TransitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "${TTS_CAPSTONE_FRONTEND_URL}")
@RequestMapping("/api/buses")
@RestController
public class TransitController {
    @Autowired
    private TransitService transitService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public BusRequest getRoutes() {
        return new BusRequest();
    }

    @GetMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Object> postRoutes(@RequestParam("address") String address, @RequestParam("city") String city) {
        try {
            BusRequest request = new BusRequest(address, city);
            List<Bus> buses = transitService.getNearbyBuses(request);
            Location personLocation = transitService.getPersonLocation(request);
            return new ArrayList<Object>(
                    Arrays.asList(
                            personLocation,
                            buses,
                            request
                    )
            );
        } catch (Exception e) {
            return new ArrayList<Object>(
                    Arrays.asList(
                            new BusRequest()
                    )
            );
        }
    }
}
