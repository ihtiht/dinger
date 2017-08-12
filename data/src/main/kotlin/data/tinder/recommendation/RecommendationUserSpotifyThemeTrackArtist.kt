package data.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserSpotifyThemeTrackArtist private constructor(
        @field:Json(name = "name")
        private val name: String,
        @field:Json(name = "id")
        private val id: String)
