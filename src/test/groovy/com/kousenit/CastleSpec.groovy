package com.kousenit

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CastleSpec extends Specification implements DomainUnitTest<Castle> {

    Castle camelot = new Castle(name: 'Camelot', city: 'Marlborough', state: 'CT')

    void "test valid"() {
        expect:"valid castle"
            camelot.validate()
    }
}
