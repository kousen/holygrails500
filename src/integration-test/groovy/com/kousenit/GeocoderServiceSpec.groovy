package com.kousenit

import com.googleapis.maps.GoogleMapsConfiguration
import com.googleapis.maps.Location
import grails.testing.mixin.integration.Integration
import grails.testing.services.ServiceUnitTest
import org.junit.Assume
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
class GeocoderServiceSpec extends Specification implements ServiceUnitTest<GeocoderService>{

    @Autowired
    GeocoderService geocoderService

    @Autowired
    GoogleMapsConfiguration googleMapsConfiguration

    void "verify lat,lng for Mountain View, CA"() {
        expect:
        if (googleMapsConfiguration.getKey() as boolean) {
            Optional<Location> locationOptional = service.fillInLatLng(new Castle(name: 'Google', city: 'Mountain View', state: 'CA'))
            assert locationOptional.isPresent()
            Location location = locationOptional.get()
            assert (location.lat - 37.4).abs() < 0.1
            assert (location.lng - -122.1).abs() < 0.1
        }
    }
}
