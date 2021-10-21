package com.kousenit

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

import java.nio.charset.StandardCharsets

@Transactional
class GeocoderService {
    private static final String BASE = 'https://maps.googleapis.com/maps/api/geocode/json?'
    private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno"

    Castle fillInLatLng(Castle castle) {
        String encoded = [castle.city, castle.state].collect {
            URLEncoder.encode(it, StandardCharsets.UTF_8)
        }.join(',')
        String qs = "address=$encoded&key=$KEY"
        def root = new JsonSlurper().parse("$BASE$qs".toURL())
        def loc = root.results[0].geometry.location
        if (loc.lat) {
            castle.latitude = loc.lat.toDouble()
            castle.longitude = loc.lng.toDouble()
        }
        return castle
    }
}
