package data.tinder.recommendations

import com.squareup.moshi.Json

internal class DataRecommendationUserTeaser private constructor(
        @Json(name = "string")
        private val description: String,
        @Json(name = "type")
        private val type: String)
