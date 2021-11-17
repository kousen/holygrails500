package holygrails500

import com.kousenit.Castle
import com.kousenit.CastleService
import com.kousenit.GeocoderService
import grails.events.annotation.Subscriber
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEvent
import org.grails.datastore.mapping.engine.event.PreInsertEvent
import org.grails.datastore.mapping.engine.event.PreUpdateEvent
import org.springframework.beans.factory.annotation.Autowired

@CompileStatic
@Slf4j
class CastleListener {
    @Autowired
    GeocoderService geocoderService

    @Autowired
    CastleService castleService

    @Subscriber
    void beforeInsert(PreInsertEvent event) {
        log.info("on pre insert event")
        updateCastle(event)
    }

    @Subscriber
    void beforeUpdate(PreUpdateEvent event) {
        log.info("on pre update event")
        updateCastle(event)
    }

    private void updateCastle(AbstractPersistenceEvent event) {
        if (event.entityObject instanceof Castle) {
            String city = event.entityAccess.getProperty('city')
            String state = event.entityAccess.getProperty('state')
            geocoderService.fillInLatLng(city, state).ifPresent(loc -> {
                Serializable id = ((Castle)event.entityObject).id
                castleService.updateLatitude(id, loc.lat)
                castleService.updateLongitude(id, loc.lng)
            })
        }
    }
}
