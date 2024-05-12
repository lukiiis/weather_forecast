import { useState, useEffect } from 'react';
import { WeatherForecast, fetchWeatherData, initiateMap } from './hooks/weatherApi';
import './App.css';
import WeatherCard from './components/WeatherCard';
import 'leaflet/dist/leaflet.css';
import WorldMap from './components/WorldMap';

const App = () => {
  const [location, setLocation] = useState({ latitude: 91, longitude: 181 });
  const [weatherData, setWeatherData] = useState<WeatherForecast[]>([]);

  const weatherMap = initiateMap();

  // getting location
  useEffect(() => {
    const getLocation = () => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          position => {
            setLocation({
              latitude: position.coords.latitude,
              longitude: position.coords.longitude
            });
          },
          error => {
            console.error("Błąd pobierania lokalizacji:", error);
          }
        );
      } else {
        console.error("Twoja przeglądarka nie obsługuje geolokalizacji.");
      }
    };
    getLocation();
  }, []);

  useEffect(() => {
    if (location.latitude !== 91 && location.longitude !== 181) {
      fetchWeatherData(location)
        .then(data => {
          setWeatherData(data);
        })
        .catch(error => {
          console.error('error fetching data: ', error);
        })
    }
  }, [location])

  //dark mode
  const [dark, setDark] = useState<boolean>(false);
  const darkModeHandler = () => {
    setDark(!dark);
    document.body.classList.toggle("dark");
  }

  return (
    <div className="h-full w-full mode-transition dark:bg-black">
      <button className='flex relative top-8 left-9' onClick={darkModeHandler}>{dark ? <img src={require("./img/light.png")} alt='Light'></img> : <img src={require("./img/dark.png")} alt='Dark'></img>}</button>
      <h1 className='flex items-center justify-center text-6xl py-6 text-center dark:text-white'>Weather Forecast</h1>
      <span className='flex items-center justify-center text-lg text-center mb-8 dark:text-white'>Next 7 days:</span>
      <div className='h-full w-full'>
        <div className='max-w-7xl mx-auto my-0 flex justify-between items-center flex-wrap flex-col gap-10 last:mb-10'>
          {weatherData ? (
            <>
              {weatherData.map((forecast, index) => (
                <WeatherCard key={index} forecast={forecast} map={weatherMap}></WeatherCard>
              ))}
            </>
          ) : (
            <div className='dark:text-white'>Fetching weather data...</div>
          )}
        </div>
      </div>
      <div className='w-full mx-auto my-0'>
        <h1 className='flex items-center justify-center text-4xl text-center pt-16 dark:text-white'>Interactive map</h1>
        <span className='flex items-center justify-center text-lg text-center mt-6 dark:text-white'>Click anywhere on the map to change the location</span>
        <div className='max-w-7xl mx-auto mb-16'>
          <WorldMap location={location} setLocation={setLocation} />
        </div>
      </div>
    </div>
  );
}

export default App;
