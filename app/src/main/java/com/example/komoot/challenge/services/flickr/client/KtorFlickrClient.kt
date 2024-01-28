package com.example.komoot.challenge.services.flickr.client

import android.location.Location
import com.example.komoot.challenge.services.flickr.dto.FlickrDto
import com.example.komoot.challenge.services.flickr.dto.PhotoDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol
import io.ktor.http.path

class KtorFlickrClient(
    private val httpClient: HttpClient
) : FlickrClient {

    override suspend fun getPicture(location: Location): PhotoDto? {
        val response = httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "www.flickr.com"
                path("services/rest")
                parameters.apply {
                    append("method", "flickr.photos.search")
                    append("api_key", "0c9bbe06a524353d108319c156568fab")
                    append("format", "json")
                    append("nojsoncallback", "1")
                    append("lat", "${location.latitude}")
                    append("lon", "${location.longitude}")
                    append("radius", "5")
                    append("tags", "outdoor")
                }
            }
        }

        if (response.status.value != 200) {
            error("Some error occurred ${response.status} ${response.bodyAsText()}")
        }

        return response
            .body<FlickrDto>()
            .photos
            .photo
            .randomOrNull()
    }
}
