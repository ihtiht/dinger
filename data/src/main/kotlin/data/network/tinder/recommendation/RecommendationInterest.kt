package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationInterest private constructor(
        @field:Json(name = "id")
        val id: String,
        @field:Json(name = "name")
        val name: String)
