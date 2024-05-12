import { Marker, useMapEvents } from "react-leaflet";
import { LocationProps } from "../hooks/weatherApi";
import { useState } from "react";
import { LatLng } from "leaflet";
import { Icon } from "leaflet";

const LocationMarker: React.FC<LocationProps> = ({ location, setLocation }) => {
    const [marker, setMarker] = useState(new LatLng(50.049683, 19.944544));
    const markerIcon = new Icon ({
        iconUrl : require('../img/marker.png'),
        iconSize : [24,24],
        iconAnchor : [18,20],
      })
    
    useMapEvents({
      click(e) {
        setLocation({
          latitude: e.latlng.lat,
          longitude: e.latlng.lng
        })
        setMarker(e.latlng);
      }
    });
  
    return (
        <Marker icon={markerIcon} position={marker} ></Marker>
    );
  }

  export default LocationMarker;