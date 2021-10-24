package com.kousenit

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class KnightSpec extends Specification implements DomainUnitTest<Knight> {
    Knight knight = new Knight(title: 'King', name: 'Arthur')

    void "test valid"() {
        expect:"valid knight"
            knight.validate()
    }
}
