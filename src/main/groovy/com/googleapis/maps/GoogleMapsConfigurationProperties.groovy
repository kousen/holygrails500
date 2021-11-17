package com.googleapis.maps

import groovy.transform.CompileStatic
import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("googlemaps")
@CompileStatic
class GoogleMapsConfigurationProperties implements GoogleMapsConfiguration {
    String key
}
