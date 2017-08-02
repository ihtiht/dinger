package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserSpotifyThemeTrackArtist private constructor(
        @Json(name = "name")
        private val name: String,
        @Json(name = "id")
        private val id: String)
