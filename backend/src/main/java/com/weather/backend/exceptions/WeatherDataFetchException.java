package com.weather.backend.exceptions;

public class WeatherDataFetchException extends RuntimeException {
    public WeatherDataFetchException(String message) {
        super(message);
    }

    public WeatherDataFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}