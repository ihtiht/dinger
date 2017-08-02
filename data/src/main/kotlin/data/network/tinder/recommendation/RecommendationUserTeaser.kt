package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserTeaser private constructor(
        @Json(name = "string")
        private val description: String,
        @Json(name = "type")
        private val type: String)
