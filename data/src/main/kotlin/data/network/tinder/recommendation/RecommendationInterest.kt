package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationInterest private constructor(
        @field:Json(name = "id")
        private val id: String,
        @field:Json(name = "name")
        private val name: String)
