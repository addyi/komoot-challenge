package com.example.komoot.challenge.services.flickr

import android.location.Location

interface FlickrClient {

    suspend fun getPicture(location: Location): PhotoDto?
}
