package com.tts.capstone.weather.service;

import com.tts.capstone.weather.entity.Response;
import com.tts.capstone.weather.entity.ZipCode;
import com.tts.capstone.weather.repository.ZipCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class WeatherService {
    @Value("${TTS_CAPSTONE_WEATHER_KEY}")
    private String apiKey;

    @Autowired
    private ZipCodeRepository zipCodeRepository;

    public Response getForecast(String zipCode) {
        String url = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + "&units=imperial&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        try {
            Response response = restTemplate.getForObject(url, Response.class);
            zipCodeRepository.save(new ZipCode(zipCode));
            return response;
        } catch (HttpClientErrorException ex) {
            Response response = new Response();
            response.setName("error");
            return response;
        }
    }

    public List<ZipCode> getUp10MostRecentSearches() {
        List<ZipCode> zipCodes = (List<ZipCode>) zipCodeRepository.findAll();
        int fromIndex = Math.max(zipCodes.size() - 10, 0);
        int toIndex = zipCodes.size();
        zipCodes = zipCodes.subList(fromIndex, toIndex);
        Collections.reverse(zipCodes);
        return zipCodes;
    }
}
