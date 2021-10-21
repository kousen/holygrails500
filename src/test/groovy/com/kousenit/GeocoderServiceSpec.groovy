package com.kousenit

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class GeocoderServiceSpec extends Specification implements ServiceUnitTest<GeocoderService>{

    void "verify lat,lng for Mountain View, CA"() {
        given:
        Castle google = new Castle(name: 'Google', city: 'Mountain View', state: 'CA')

        when:
        service.fillInLatLng(google)

        then:
        (google.latitude - 37.4).abs() < 0.1
        (google.longitude - -122.1).abs() < 0.1
    }

    void 'service does not throw an exception on bad responses'() {
        given:
        Castle black = new Castle(name: 'Black', city: 'The Wall', state: 'Westeros')

        when:
        service.fillInLatLng(black)

        then:
        black.latitude < 0.01
        black.longitude < 0.01
    }
}
