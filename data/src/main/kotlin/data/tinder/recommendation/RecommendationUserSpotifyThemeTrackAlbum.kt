package data.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserSpotifyThemeTrackAlbum private constructor(
        @field:Json(name = "images")
        private val images: Array<RecommendationUserSpotifyThemeTrackAlbumImage>,
        @field:Json(name = "name")
        private val name: String,
        @field:Json(name = "id")
        private val id: String)
