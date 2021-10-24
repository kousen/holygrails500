package holygrails500

import com.kousenit.Castle
import com.kousenit.GeocoderService
import com.kousenit.Quest

class BootStrap {
    GeocoderService geocoderService

    def init = { servletContext ->
//        if (Quest.count() == 0 && Castle.count() == 0) {
//            Quest quest = new Quest(name: 'Seek the grail')
//                    .addToTasks(name: 'Answer the bridgekeeper', priority: 4)
//                    .addToTasks(name: 'Bring out your dead')
//                    .addToTasks(name: 'Defeat the Black Knight')
//                    .save(failOnError: true)
//            Castle camelot = new Castle(name: 'Camelot', city: 'Marlborough', state: 'CT')
//                    .addToKnights(title: 'Sir', name: 'Robin', quest: quest)
//                    .addToKnights(title: 'King', name: 'Arthur', quest: quest)
//                    .addToKnights(title: 'Sir', name: 'Lancelot', quest: quest)
//            Castle espn = new Castle(name: 'ESPN', city: 'Bristol', state: 'CT')
//            Castle yale = new Castle(name: 'Yale', city: 'New Haven', state: 'CT')
//
//            [camelot, espn, yale].collect {
//                geocoderService.fillInLatLng(it).save(failOnError: true)
//            }
//        }
    }

    def destroy = {
    }
}
