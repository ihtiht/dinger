package data.tinder.recommendation

import com.squareup.moshi.Json

internal class Recommendation private constructor(
        @field:Json(name = "group_matched")
        val groupMatched: Boolean,
        @field:Json(name = "user")
        val user: RecommendationUser)
