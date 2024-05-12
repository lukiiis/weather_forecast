import { MapContainer, TileLayer } from "react-leaflet";
import LocationMarker from "./LocationMarker";
import { LocationProps } from "../functions/weatherApi";

const WorldMap: React.FC<LocationProps> = ({ location, setLocation }) => {

  return (
    <MapContainer center={[50.049683, 19.944544]} zoom={8} scrollWheelZoom={false}
      style={{
        height: "700px",
        marginTop: "80px",
        borderRadius: "10px"
      }}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <LocationMarker location={location} setLocation={setLocation}/>
    </MapContainer>
  )
}

export default WorldMap;