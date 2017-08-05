package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class Recommendation private constructor(
        @field:Json(name = "type")
        val type: String,
        @field:Json(name = "group_matched")
        val groupMatched: Boolean,
        @field:Json(name = "user")
        val user: RecommendationUser)
