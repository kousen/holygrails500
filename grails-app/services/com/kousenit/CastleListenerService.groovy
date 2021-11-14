//file:noinspection GrMethodMayBeStatic
package com.kousenit

import grails.events.annotation.gorm.Listener
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEvent
import org.grails.datastore.mapping.engine.event.PreInsertEvent
import org.grails.datastore.mapping.engine.event.PreUpdateEvent

import java.nio.charset.StandardCharsets

@Transactional
class CastleListenerService {
//    private static final String BASE = 'https://maps.googleapis.com/maps/api/geocode/json?'
//    private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno"

     GeocoderService geocoderService

    @Listener(Castle)
    void onCastlePreInsert(PreInsertEvent event) {
        updateCastle(event)
    }

    @Listener(Castle)
    void onCastlePreUpdate(PreUpdateEvent event) {
        updateCastle(event)
    }

    private void updateCastle(AbstractPersistenceEvent event) {
        if (event.entityObject instanceof Castle) {
            String city = event.entityAccess.getProperty('city')
            String state = event.entityAccess.getProperty('state')
            def loc = geocoderService.fillInLatLng(city, state)
            event.entityAccess.setProperty('latitude', loc.lat.toDouble())
            event.entityAccess.setProperty('longitude', loc.lng.toDouble())
        }
    }

}
