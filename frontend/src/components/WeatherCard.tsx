import { WeatherForecast, formatDate } from "../hooks/weatherApi"

interface WeatherCardProps {
    forecast: WeatherForecast;
    map: Map<number, string>
}

const WeatherCard: React.FC<WeatherCardProps> = ({ forecast, map }) => {
    return (
        <div className="text-black w-3/4 h-48 bg-slate-200 flex justify-around items-center sm:w-5/6 rounded-3xl dark:bg-gray-400 dark:text-white">
            <div className="flex justify-between items-center w-64">
                <div className="flex flex-col gap-4">
                    <span className="text-2xl font-semibold">Date:</span>
                    <span className="text-lg">{formatDate(forecast.date)}</span>
                </div>
                <div className="icon">
                    <img src={map.get(forecast.weatherCode)} alt={forecast.weatherCode.toString()}></img>
                </div>
            </div>
            <div className=" flex flex-col gap-5">
                <div className="flex flex-col">
                    <span className="text-xl font-semibold">Min. temperarue:</span>
                    <span className="text-lg">{forecast.minTemp} &deg; C</span>
                </div>
                <div className="flex flex-col">
                    <span className="text-xl font-semibold">Max. temperarue:</span>
                    <span className="text-lg">{forecast.maxTemp} &deg; C</span>
                </div>
            </div>
            <div className="flex flex-col">
                <span className="text-xl font-semibold">Est. energy:</span>
                <span className="text-lg">{forecast.energy} kWh</span>
            </div>
        </div>
    )
}

export default WeatherCard