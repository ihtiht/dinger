package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserTeaser private constructor(
        @field:Json(name = "string")
        private val description: String,
        @field:Json(name = "type")
        private val type: String)
