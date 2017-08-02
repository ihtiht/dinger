package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserInstagramPhoto private constructor(
        @Json(name = "link")
        private val link: String,
        @Json(name = "image")
        private val imageUrl: String,
        @Json(name = "thumbnail")
        private val thumbnailUrl: String,
        @Json(name = "ts")
        private val ts: String)
