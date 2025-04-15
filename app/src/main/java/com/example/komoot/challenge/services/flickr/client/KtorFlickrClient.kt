package com.example.komoot.challenge.services.flickr.client

import android.location.Location
import android.util.Log
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

                    // I deleted this API key since I decided to make the project public
                    // I know don't commit secrets to the repository, etc. but this was just a coding challenge
                    // It seems also nowadays that the flicker API needs a pro subscription. Which I don't have.
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

        return runCatching { response.body<FlickrDto>().photos.photo.randomOrNull() }
            .onFailure { error ->
                // API key is invalid therefore the request always fails
                Log.e("FlickrClient", "Error occurred while fetching picture: ${response.bodyAsText()}")
            }
            .getOrNull()
    }
}
