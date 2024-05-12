package com.weather.backend;

import com.weather.backend.controller.WeatherController;
import com.weather.backend.dto.WeatherDTO;
import com.weather.backend.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private WeatherController weatherController;

	@MockBean
	private WeatherService weatherService;

	@Test
	void contextLoads() {

	}

	@Test
	void getWeeklyWeather_ValidInput_ReturnsOk() {
		double latitude = 50.0;
		double longitude = 10.0;
		List<WeatherDTO> weatherDTOList = createSampleWeatherDTOList();
		when(weatherService.getWeeklyWeather(latitude, longitude)).thenReturn(weatherDTOList);

		ResponseEntity<List<WeatherDTO>> responseEntity = weatherController.getWeeklyWeather(latitude, longitude);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(weatherDTOList, responseEntity.getBody());
		verify(weatherService, times(1)).getWeeklyWeather(latitude, longitude);
	}

	@Test
	void getWeeklyWeather_InvalidInput_ReturnsBadRequest() {
		double invalidLatitude = -100.0;
		double invalidLongitude = 200.0;

		ResponseEntity<List<WeatherDTO>> responseEntity = weatherController.getWeeklyWeather(invalidLatitude, invalidLongitude);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		verify(weatherService, times(1)).getWeeklyWeather(invalidLatitude, invalidLongitude);
	}

	@Test
	void handleMethodArgumentTypeMismatch_Exception_ReturnsBadRequest() {
		MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException("", null, "", null, null);

		ResponseEntity<Object> responseEntity = weatherController.handleMethodArgumentTypeMismatch(exception);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	private List<WeatherDTO> createSampleWeatherDTOList() {
		List<WeatherDTO> sampleWeatherDTOList = new ArrayList<>();

		WeatherDTO weatherDTO1 = new WeatherDTO();
		weatherDTO1.setDate(LocalDate.of(2024, 5, 13));
		weatherDTO1.setWeatherCode(3);
		weatherDTO1.setMinTemp(15.0);
		weatherDTO1.setMaxTemp(25.0);
		weatherDTO1.setEnergy(50.0);
		sampleWeatherDTOList.add(weatherDTO1);

		WeatherDTO weatherDTO2 = new WeatherDTO();
		weatherDTO2.setDate(LocalDate.of(2024, 5, 14));
		weatherDTO2.setWeatherCode(5);
		weatherDTO2.setMinTemp(12.0);
		weatherDTO2.setMaxTemp(20.0);
		weatherDTO2.setEnergy(40.0);
		sampleWeatherDTOList.add(weatherDTO2);

		return sampleWeatherDTOList;
	}

}
