package com.kousenit

import grails.gorm.services.Query
import grails.gorm.services.Service

@Service(Castle)
interface CastleService {

    Castle get(Serializable id)

    List<Castle> list(Map args)

    Long count()

    void delete(Serializable id)

    Castle save(Castle castle)

    @Query("update ${Castle castle} set ${castle.latitude} = $latitude where $castle.id = $id")
    void updateLatitude(Serializable id, Double latitude)

    @Query("update ${Castle castle} set ${castle.longitude} = $longitude where $castle.id = $id")
    void updateLongitude(Serializable id, Double longitude)
}