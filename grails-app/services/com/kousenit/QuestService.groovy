package com.kousenit

import grails.gorm.services.Service

@Service(Quest)
interface QuestService {

    Quest get(Serializable id)

    List<Quest> list(Map args)

    Long count()

    void delete(Serializable id)

    Quest save(Quest quest)

}