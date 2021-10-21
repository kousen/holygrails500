package com.kousenit

class Knight {
    String title = 'Sir'
    String name
    Quest quest
    Castle castle

    String toString() {
        "$title $name"
    }

    static constraints = {
        title inList: ['Sir', 'Lord', 'Lady', 'King', 'Queen']
        name blank: false
        quest nullable: true
        castle nullable: true
    }
}
