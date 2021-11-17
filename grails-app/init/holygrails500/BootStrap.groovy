package holygrails500

import com.kousenit.Castle
import com.kousenit.CastleService
import com.kousenit.Quest
import com.kousenit.QuestService
import grails.util.Environment

class BootStrap {
    QuestService questService
    CastleService castleService
    def init = { servletContext ->
        if (Environment.current != Environment.TEST && questService.count() == 0) {
            Quest quest = questService.save(new Quest(name: 'Seek the grail')
                    .addToTasks(name: 'Answer the bridgekeeper', priority: 4)
                    .addToTasks(name: 'Bring out your dead')
                    .addToTasks(name: 'Defeat the Black Knight'))
            for (Castle castle : [
                    new Castle(name: 'Camelot', city: 'Marlborough', state: 'CT')
                            .addToKnights(title: 'Sir', name: 'Robin', quest: quest)
                            .addToKnights(title: 'King', name: 'Arthur', quest: quest)
                            .addToKnights(title: 'Sir', name: 'Lancelot', quest: quest),
                    new Castle(name: 'ESPN', city: 'Bristol', state: 'CT'),
                    new Castle(name: 'Yale', city: 'New Haven', state: 'CT')
            ]) {
                castleService.save(castle)
            }
        }
    }

    def destroy = {
    }
}
