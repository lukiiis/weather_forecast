package com.weather.backend.mapper;

import com.weather.backend.api.WeatherApiData;
import com.weather.backend.dto.WeatherDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WeatherMapper {

    private final ModelMapper modelMapper;

    public List<WeatherDTO> convertToWeatherDTOList(WeatherApiData weatherApiData) {
        return weatherApiData.getDaily().getTime().stream()
                .map(date -> {
                    int index = weatherApiData.getDaily().getTime().indexOf(date);
                    return mapToWeatherDTO(weatherApiData, index);
                })
                .collect(Collectors.toList());
    }

    private WeatherDTO mapToWeatherDTO(WeatherApiData weatherApiData, int index) {
        WeatherDTO weatherDTO = modelMapper.map(weatherApiData, WeatherDTO.class);
        weatherDTO.setDate(weatherApiData.getDaily().getTime().get(index));
        weatherDTO.setWeatherCode(weatherApiData.getDaily().getWeather_code().get(index));
        weatherDTO.setMinTemp(weatherApiData.getDaily().getTemperature_2m_min().get(index));
        weatherDTO.setMaxTemp(weatherApiData.getDaily().getTemperature_2m_max().get(index));
        //todo round to 2 decimal places
        weatherDTO.setEnergy(weatherApiData.getDaily().getSunshine_duration().get(index) / 60 * 2.5 * 0.2);
        return weatherDTO;
    }
}