package com.example.komoot.challenge.services.flickr

import android.location.Location
import android.util.Log
import com.example.komoot.challenge.services.flickr.client.FlickrClient
import com.example.komoot.challenge.services.flickr.dto.FlickrPhoto

class FlickrServiceImpl(
    private val flickrClient: FlickrClient
) : FlickrService {
    override suspend fun getPicture(location: Location): FlickrPhoto? {
        val photoDto = try {
            flickrClient.getPicture(location) ?: return null
        } catch (e: Exception) {
            Log.e(TAG, "Error while fetching picture: ${e.message}", e)
            return null
        }
        val pictureUrl = "https://live.staticflickr.com/${photoDto.server}/${photoDto.id}_${photoDto.secret}.jpg"

        Log.d(TAG, "New picture [${photoDto.title}]: $pictureUrl")

        return FlickrPhoto(pictureUrl, photoDto.title)
    }

    companion object {
        private const val TAG = "FlickrServiceImpl"
    }
}
