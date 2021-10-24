package com.kousenit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TaskServiceSpec extends Specification {

    TaskService taskService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        Quest quest = new Quest(name: 'Seek the grail').save(flush: true)
        new Task(name: 'Defeat the Black Knight', quest: quest).save(flush: true, failOnError: true)
        new Task(name: 'Bring out your dead', quest: quest).save(flush: true, failOnError: true)
        Task task = new Task(name: 'Answer the bridgekeeper', quest: quest).save(flush: true, failOnError: true)
        new Task(name: 'Run away', quest: quest).save(flush: true, failOnError: true)
        new Task(name: 'Flee from killer rabbit', quest: quest).save(flush: true, failOnError: true)
        // assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        task.id
    }

    void "test get"() {
        setupData()

        expect:
        taskService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Task> taskList = taskService.list(max: 2, offset: 2)

        then:
        taskList.size() == 2
        // assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        taskService.count() == 5
    }

    void "test delete"() {
        Long taskId = setupData()

        expect:
        taskService.count() == 5

        when:
        taskService.delete(taskId)
        sessionFactory.currentSession.flush()

        then:
        taskService.count() == 4
    }

    void "test save"() {
        when:
        // assert false, "TODO: Provide a valid instance to save"
        Quest quest = new Quest(name: 'Seek the grail').save(flush: true)
        Task task = new Task(name: 'Defeat the Black Knight', quest: quest)
        taskService.save(task)

        then:
        task.id != null
    }
}
