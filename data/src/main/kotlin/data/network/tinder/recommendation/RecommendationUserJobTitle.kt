package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserJobTitle private constructor(
        @Json(name = "name")
        private val name: String)
