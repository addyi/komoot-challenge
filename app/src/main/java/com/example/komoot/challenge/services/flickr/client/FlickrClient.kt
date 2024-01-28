package com.example.komoot.challenge.services.flickr.client

import android.location.Location
import com.example.komoot.challenge.services.flickr.dto.PhotoDto

interface FlickrClient {

    suspend fun getPicture(location: Location): PhotoDto?
}
