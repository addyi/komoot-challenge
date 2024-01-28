package com.example.komoot.challenge.services.flickr

import kotlinx.serialization.SerialName

/*
{
  "photos": {
    "page": 1,
    "pages": 1,
    "perpage": 250,
    "total": 3,
    "photo": [
      {
        "id": "50120214311",
        "owner": "155565074@N05",
        "secret": "fe902b2865",
        "server": "65535",
        "farm": 66,
        "title": "Odernheim am Glan....",
        "ispublic": 1,
        "isfriend": 0,
        "isfamily": 0
      },
      {
        "id": "27118426273",
        "owner": "101542541@N05",
        "secret": "3fc87d7dd9",
        "server": "7620",
        "farm": 8,
        "title": "HFF-wooden fence",
        "ispublic": 1,
        "isfriend": 0,
        "isfamily": 0
      },
      {
        "id": "17678635179",
        "owner": "101542541@N05",
        "secret": "a641cc4e26",
        "server": "7672",
        "farm": 8,
        "title": "it's spring - explore 2015-05-19",
        "ispublic": 1,
        "isfriend": 0,
        "isfamily": 0
      }
    ]
  },
  "stat": "ok"
}
 */

@kotlinx.serialization.Serializable
data class FlickrDto(
    @SerialName("photos")
    val photos: PhotosDto,
    @SerialName("stat")
    val stat: String
)

@kotlinx.serialization.Serializable
data class PhotosDto(
    @SerialName("page")
    val page: Int,
    @SerialName("pages")
    val pages: Int,
    @SerialName("perpage")
    val numOfElementsPerPage: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("photo")
    val photo: List<PhotoDto>
)

@kotlinx.serialization.Serializable
data class PhotoDto(
    @SerialName("id")
    val id: String,
    @SerialName("owner")
    val owner: String,
    @SerialName("secret")
    val secret: String,
    @SerialName("server")
    val server: String,
    @SerialName("title")
    val title: String
)
