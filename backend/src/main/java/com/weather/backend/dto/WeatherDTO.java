package com.weather.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherDTO {
    private LocalDate date;
    private Integer weatherCode;
    private Double minTemp;
    private Double maxTemp;
    private Double energy;
}
