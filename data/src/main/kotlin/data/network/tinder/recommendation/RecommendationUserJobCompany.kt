package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserJobCompany private constructor(
        @Json(name = "name")
        private val name: String)
