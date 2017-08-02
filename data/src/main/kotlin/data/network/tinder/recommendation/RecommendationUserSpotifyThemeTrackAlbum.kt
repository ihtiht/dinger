package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserSpotifyThemeTrackAlbum private constructor(
        @Json(name = "images")
        private val images: Array<RecommendationUserSpotifyThemeTrackAlbumImage>,
        @Json(name = "name")
        private val name: String,
        @Json(name = "id")
        private val id: String)
