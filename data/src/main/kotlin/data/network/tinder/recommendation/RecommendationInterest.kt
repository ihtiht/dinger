package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationInterest private constructor(
        @Json(name = "id")
        private val id: String,
        @Json(name = "name")
        private val name: String)
