package com.kousenit

import grails.gorm.services.Service

@Service(Knight)
interface KnightService {

    Knight get(Serializable id)

    List<Knight> list(Map args)

    Long count()

    void delete(Serializable id)

    Knight save(Knight knight)

}