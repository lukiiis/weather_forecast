package com.weather.backend.controller;

import com.weather.backend.dto.WeatherDTO;
import com.weather.backend.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
@Validated
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/weather-forecast")
    public ResponseEntity<List<WeatherDTO>> getWeeklyWeather(@RequestParam Double latitude, @RequestParam Double longitude){
        return ResponseEntity.ok(weatherService.getWeeklyWeather(latitude, longitude));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String error = "Bad value for parameter: " + ex.getName();
        return ResponseEntity.badRequest().body(error);
    }
}
