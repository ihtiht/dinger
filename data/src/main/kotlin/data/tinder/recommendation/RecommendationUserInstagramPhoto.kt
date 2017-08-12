package data.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserInstagramPhoto private constructor(
        @field:Json(name = "link")
        private val link: String,
        @field:Json(name = "image")
        private val imageUrl: String,
        @field:Json(name = "thumbnail")
        private val thumbnailUrl: String,
        @field:Json(name = "ts")
        private val ts: String)
