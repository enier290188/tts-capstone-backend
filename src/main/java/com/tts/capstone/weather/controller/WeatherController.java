package com.tts.capstone.weather.controller;

import com.tts.capstone.weather.entity.Response;
import com.tts.capstone.weather.entity.ZipCode;
import com.tts.capstone.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "${TTS_CAPSTONE_FRONTEND_URL}")
@RequestMapping("/api/weather")
@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ZipCode>> getList(Model model) {
        return ResponseEntity.ok().body(weatherService.getUp10MostRecentSearches());
    }

    @GetMapping("/{zipCode}")
    @ResponseStatus(HttpStatus.OK)
    public Response getByZipCode(@PathVariable String zipCode) {
        return weatherService.getForecast(zipCode);
    }
}
