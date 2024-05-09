package com.weather.backend.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Data
public class Daily {
    List<LocalDate> time;
    List<Integer> weather_code;
    List<Double> temperature_2m_max;
    List<Double> temperature_2m_min;
    List<Double> sunshine_duration;
}
