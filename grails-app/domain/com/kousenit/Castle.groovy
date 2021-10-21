package com.kousenit

class Castle {
    String name
    String city
    String state
    double latitude
    double longitude

    String toString() { "Castle $name" }

    static hasMany = [knights: Knight]

    static constraints = {
        name blank: false
        city blank: false
        state blank: false
        latitude min: -90d, max: 90d
        longitude()
    }
}
