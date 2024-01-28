package com.example.komoot.challenge.services.flickr

import android.location.Location

interface FlickrService {

    suspend fun getPicture(location: Location): FlickrPhoto?
}
