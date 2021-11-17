package com.googleapis.maps

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("https://maps.googleapis.com")
interface GeocoderClient {

    @Get("/maps/api/geocode/json")
    GeocodeResponse fetchGeocodeResponse(@QueryValue String address, @QueryValue String key)
}
