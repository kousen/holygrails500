package com.kousenit

import groovy.transform.ToString

import java.time.LocalDate

@ToString(includeNames = true, includes = ['name', 'priority'])
class Task {
    String name
    int priority = 3
    LocalDate startDate = LocalDate.now()
    LocalDate endDate = LocalDate.now()
    boolean completed

    static belongsTo = [quest: Quest]

    static transients = ['duration']

    int getDuration() { endDate - startDate + 1 }

    static constraints = {
        name blank: false
        priority range: 1..5
        startDate()
        endDate validator: { value, task ->
            value >= task.startDate
        }
        completed()
    }
}
