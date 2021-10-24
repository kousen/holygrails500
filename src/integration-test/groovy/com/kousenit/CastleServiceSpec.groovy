package com.kousenit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CastleServiceSpec extends Specification {

    CastleService castleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        new Castle(name: 'Camelot', city: 'Marlborough', state: 'CT').save(flush: true, failOnError: true)
        new Castle(name: 'Fenway', city: 'Boston', state: 'MA').save(flush: true, failOnError: true)
        Castle castle = new Castle(name: 'ESPN', city: 'Bristol', state: 'CT').save(flush: true, failOnError: true)
        new Castle(name: 'Yale', city: 'New Haven', state: 'CT').save(flush: true, failOnError: true)
        new Castle(name: 'Bradley', city: 'Windsor', state: 'CT').save(flush: true, failOnError: true)
        // assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        castle.id
    }

    void "test get"() {
        setupData()

        expect:
        castleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Castle> castleList = castleService.list(max: 2, offset: 2)

        then:
        castleList.size() == 2
        // assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        castleService.count() == 5
    }

    void "test delete"() {
        Long castleId = setupData()

        expect:
        castleService.count() == 5

        when:
        castleService.delete(castleId)
        sessionFactory.currentSession.flush()

        then:
        castleService.count() == 4
    }

    void "test save"() {
        when:
        // assert false, "TODO: Provide a valid instance to save"
        Castle castle = new Castle(name: 'Camelot', city: 'Marlborough', state: 'CT')
        castleService.save(castle)

        then:
        castle.id != null
    }
}
