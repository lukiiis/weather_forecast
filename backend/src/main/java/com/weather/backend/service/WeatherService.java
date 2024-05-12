package com.weather.backend.service;

import com.weather.backend.api.WeatherApiData;
import com.weather.backend.dto.WeatherDTO;
import com.weather.backend.exceptions.WeatherDataFetchException;
import com.weather.backend.mapper.WeatherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherMapper weatherMapper;
    public List<WeatherDTO> getWeeklyWeather(Double latitude, Double longitude) {
        try {
            latitude = clampLatitude(latitude);
            longitude = clampLongitude(longitude);

            WeatherApiData data = getWeatherDataFromApi(latitude, longitude);
            return weatherMapper.convertToWeatherDTOList(data);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private double clampLatitude(double latitude) {
        return Math.max(-90.0, Math.min(90.0, latitude));
    }

    private double clampLongitude(double longitude) {
        while (longitude <= -180.0) {
            longitude += 360.0;
        }
        while (longitude > 180.0) {
            longitude -= 360.0;
        }
        return longitude;
    }

    private WeatherApiData getWeatherDataFromApi(Double latitude, Double longitude){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+"&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration";
            ResponseEntity<WeatherApiData> response = restTemplate.getForEntity(url, WeatherApiData.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new WeatherDataFetchException("Error while fetching data. Response code: " + response.getStatusCodeValue());
            }
        } catch (RestClientException e) {
            throw new WeatherDataFetchException("Error while fetching data from external API: " + e.getMessage(), e);
        }
    }
}
