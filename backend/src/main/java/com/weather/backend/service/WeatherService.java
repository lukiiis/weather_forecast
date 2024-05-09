package com.weather.backend.service;

import com.weather.backend.api.WeatherApiData;
import com.weather.backend.dto.WeatherDTO;
import com.weather.backend.mapper.WeatherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherMapper weatherMapper;
    public List<WeatherDTO> getWeeklyWeather(Double latitude, Double longitude) {
        WeatherApiData data = getWeatherDataFromApi(latitude, longitude);
        return weatherMapper.convertToWeatherDTOList(data);
    }

    private WeatherApiData getWeatherDataFromApi(Double latitude, Double longitude){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+"&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration";
        return restTemplate.getForObject(url, WeatherApiData.class);
    }
}
