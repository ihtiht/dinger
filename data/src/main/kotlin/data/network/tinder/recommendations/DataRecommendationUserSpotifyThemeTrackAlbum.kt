package data.network.tinder.recommendations

import com.squareup.moshi.Json

internal class DataRecommendationUserSpotifyThemeTrackAlbum private constructor(
        @Json(name = "images")
        private val images: Array<DataRecommendationUserSpotifyThemeTrackAlbumImage>,
        @Json(name = "name")
        private val name: String,
        @Json(name = "id")
        private val id: String)
