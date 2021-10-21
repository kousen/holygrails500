package com.kousenit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class QuestServiceSpec extends Specification {

    QuestService questService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Quest(...).save(flush: true, failOnError: true)
        //new Quest(...).save(flush: true, failOnError: true)
        //Quest quest = new Quest(...).save(flush: true, failOnError: true)
        //new Quest(...).save(flush: true, failOnError: true)
        //new Quest(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //quest.id
    }

    void "test get"() {
        setupData()

        expect:
        questService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Quest> questList = questService.list(max: 2, offset: 2)

        then:
        questList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        questService.count() == 5
    }

    void "test delete"() {
        Long questId = setupData()

        expect:
        questService.count() == 5

        when:
        questService.delete(questId)
        sessionFactory.currentSession.flush()

        then:
        questService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Quest quest = new Quest()
        questService.save(quest)

        then:
        quest.id != null
    }
}
