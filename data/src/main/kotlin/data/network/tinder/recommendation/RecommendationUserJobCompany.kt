package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserJobCompany private constructor(
        @field:Json(name = "name")
        private val name: String)
