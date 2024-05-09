package com.weather.backend.controller;

import com.weather.backend.dto.WeatherDTO;
import com.weather.backend.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather-7")
    public ResponseEntity<List<WeatherDTO>> getWeeklyWeather(@RequestParam Double latitude, @RequestParam Double longitude){
        return ResponseEntity.ok(weatherService.getWeeklyWeather(latitude, longitude));
    }
}
