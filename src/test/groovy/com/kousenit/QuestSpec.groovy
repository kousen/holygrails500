package com.kousenit

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class QuestSpec extends Specification implements DomainUnitTest<Quest> {

    Quest quest = new Quest(name: 'Seek the grail')

    void "test valid"() {
        expect:"valid quest"
            quest.validate()
    }
}
