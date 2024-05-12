import axios from "axios"
import { AxiosResponse } from "axios";

export interface WeatherForecast {
    date: Date,
    weatherCode: number,
    minTemp: number,
    maxTemp: number,
    energy: number,
}

export const fetchWeatherData = async (params: { latitude: number; longitude: number }): Promise<WeatherForecast[]> => {
    try {
        const response: AxiosResponse<WeatherForecast[]> = await axios.get('https://weather-forecast-mkjb.onrender.com/api/v1/weather-forecast', { params });
        return response.data;
      } catch (error) {
        console.error('Error fetching data:', error);
        throw new Error('Error fetching data');
      }
}

export const formatDate = (dateString: Date) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${day}-${month}-${year}`;
};

export const initiateMap = (): Map<number, string> => {
    const weatherMap = new Map<number, string>();

    for (let i = 0; i < 100; i++) {
        if (i <= 2)
            weatherMap.set(i, require("../img/sun.png"));
        if (i > 2 && i <= 3)
            weatherMap.set(i, require("../img/cloudy.png"));
        if (i > 3 && i <= 9)
            weatherMap.set(i, require("../img/sandstorm.png"));
        if (i > 9 && i <= 12)
            weatherMap.set(i, require("../img/fog.png"));
        if (i > 12 && i <= 19)
            weatherMap.set(i, require("../img/thunderstorm.png"));
        if (i === 20 || i === 22 || i === 23 || i === 26)
            weatherMap.set(i, require("../img/snowy.png"));
        if (i === 21 || i === 24 || i === 25 || i === 27)
            weatherMap.set(i, require("../img/rainy.png"));
        if (i === 28)
            weatherMap.set(i, require("../img/fog.png"));
        if (i === 29)
            weatherMap.set(i, require("../img/thunderstorm.png"));
        if (i > 29 && i <= 35)
            weatherMap.set(i, require("../img/sandstorm.png"));
        if (i > 35 && i <= 39)
            weatherMap.set(i, require("../img/snowy.png"));
        if (i > 39 && i <= 49)
            weatherMap.set(i, require("../img/fog.png"));
        if (i > 49 && i <= 59)
            weatherMap.set(i, require("../img/rain-sun.png"));
        if (i > 59 && i <= 69)
            weatherMap.set(i, require("../img/rainy.png"));
        if (i > 69 && i <= 79)
            weatherMap.set(i, require("../img/snowy.png"));
        if (i > 79 && i <= 84)
            weatherMap.set(i, require("../img/rainy.png"));
        if (i > 84 && i <= 90)
            weatherMap.set(i, require("../img/snowy.png"));
        if (i > 90 && i <= 94)
            weatherMap.set(i, require("../img/rainy.png"));
        if (i > 94)
            weatherMap.set(i, require("../img/thunderstorm.png"));
    }

    return weatherMap;
}

export interface LocationProps {
    setLocation: React.Dispatch<React.SetStateAction<{
        latitude: number;
        longitude: number;
    }>>
    location: {
        latitude:number,
        longitude:number
    }
}