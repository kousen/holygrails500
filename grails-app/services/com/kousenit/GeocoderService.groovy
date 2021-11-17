package com.kousenit

import com.googleapis.maps.GeocoderClient
import com.googleapis.maps.GoogleMapsConfiguration
import com.googleapis.maps.Location
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.client.exceptions.HttpClientResponseException
import org.springframework.beans.factory.annotation.Autowired

@CompileStatic
@Slf4j
class GeocoderService {

    @Autowired
    GeocoderClient geocoderClient

    @Autowired
    GoogleMapsConfiguration googleMapsConfiguration

    Optional<Location> fillInLatLng(Castle castle) {
        fillInLatLng(castle.city, castle.state)
    }

    Optional<Location> fillInLatLng(String city, String state) {
        try {
            if (googleMapsConfiguration.getKey()) {
                String address = [city, state]
                        .collect {URLEncoder.encode(it, "UTF-8") }
                        .join(",")
                return Optional.of(geocoderClient.fetchGeocodeResponse(address, googleMapsConfiguration.getKey()).results.first().geometry.location)
            } else {
                log.warn("set googlemaps.key to fetch lat/long")
            }
        } catch (NoSuchElementException e) {
            log.error("empty result fetching location for {} {}", city, state)
        } catch (HttpClientResponseException e) {
            log.error("HTTP Client response exception {} fetching location for {} {}", e.status.code, city, state)
        }
        Optional.empty()
    }
}
