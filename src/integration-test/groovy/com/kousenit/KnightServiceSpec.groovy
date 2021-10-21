package com.kousenit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class KnightServiceSpec extends Specification {

    KnightService knightService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Knight(...).save(flush: true, failOnError: true)
        //new Knight(...).save(flush: true, failOnError: true)
        //Knight knight = new Knight(...).save(flush: true, failOnError: true)
        //new Knight(...).save(flush: true, failOnError: true)
        //new Knight(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //knight.id
    }

    void "test get"() {
        setupData()

        expect:
        knightService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Knight> knightList = knightService.list(max: 2, offset: 2)

        then:
        knightList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        knightService.count() == 5
    }

    void "test delete"() {
        Long knightId = setupData()

        expect:
        knightService.count() == 5

        when:
        knightService.delete(knightId)
        sessionFactory.currentSession.flush()

        then:
        knightService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Knight knight = new Knight()
        knightService.save(knight)

        then:
        knight.id != null
    }
}
