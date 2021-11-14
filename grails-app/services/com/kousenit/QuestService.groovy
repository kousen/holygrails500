package com.kousenit

import grails.events.annotation.gorm.Listener
import grails.gorm.services.Service
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEvent
import org.grails.datastore.mapping.engine.event.PreInsertEvent

@Service(Quest)
interface QuestService {

    Quest get(Serializable id)

    List<Quest> list(Map args)

    Long count()

    void delete(Serializable id)

    Quest save(Quest quest)
}