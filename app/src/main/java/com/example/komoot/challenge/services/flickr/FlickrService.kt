package com.example.komoot.challenge.services.flickr

import android.location.Location
import com.example.komoot.challenge.services.flickr.dto.FlickrPhoto

interface FlickrService {

    suspend fun getPicture(location: Location): FlickrPhoto?
}
