import axios from 'axios';

export default axios.create({
    baseURL: 'https://weather-forecast-mkjb.onrender.com/api',
    headers: {"ngrok-skip-browser-warning": "true"}
});